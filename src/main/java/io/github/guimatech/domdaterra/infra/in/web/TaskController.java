package io.github.guimatech.domdaterra.infra.in.web;

import io.github.guimatech.domdaterra.application.service.CustomerService;
import io.github.guimatech.domdaterra.application.service.UserService;
import io.github.guimatech.domdaterra.application.service.kanban.CategoryService;
import io.github.guimatech.domdaterra.application.service.kanban.ProjectService;
import io.github.guimatech.domdaterra.application.service.kanban.TaskService;
import io.github.guimatech.domdaterra.domain.Customer;
import io.github.guimatech.domdaterra.domain.User;
import io.github.guimatech.domdaterra.domain.kanban.Category;
import io.github.guimatech.domdaterra.domain.kanban.Priority;
import io.github.guimatech.domdaterra.domain.kanban.Project;
import io.github.guimatech.domdaterra.domain.kanban.Task;
import io.github.guimatech.domdaterra.domain.kanban.TaskStatus;
import io.github.guimatech.domdaterra.domain.kanban.TaskType;
import io.github.guimatech.domdaterra.infra.out.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping(path = "/tasks")
public class TaskController {

    private static final String MESSAGE = "message";
    private static final String TASKS = "tasks";
    private static final String KANBAN = "kanban";
    private static final int DEFAULT_PAGE_SIZE = 6;
    public static final String TASK_DETAIL = "task-detail";

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String findAllTasks(@RequestParam(required = false) Optional<Integer> page,
                               @RequestParam(required = false) Optional<Integer> size,
                               Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<Task> taskPage = taskService.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("taskPage", taskPage);

        int totalPages = taskPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return TASKS;
    }

    @GetMapping("/kanban")
    public String findAllKanban(Model model) {
        List<Task> tasks = taskService.findAllByActiveTrue();

        model.addAttribute("tasks", tasks);

        return KANBAN;
    }

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        setOptionsOnFields(model);
        ControllerHelper.setEditMode(model, true);

        return TASK_DETAIL;
    }

    @GetMapping("/edit/{id}")
    public String editTask(Model model, @PathVariable Long id) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        setOptionsOnFields(model);
        ControllerHelper.setEditMode(model, true);

        return TASK_DETAIL;
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);

        return "redirect:/tasks";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") @Valid Task task,
                              Errors errors,
                              Model model) {

        if (!errors.hasErrors()) {
            try {
                taskService.save(task);
                model.addAttribute(MESSAGE, "Cobrança gravada com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", "001", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, true);

        return TASK_DETAIL;
    }

    private void setOptionsOnFields(Model model) {
        setConstantOptionsOnFields(model);
        setVariablesOptionsOnFields(model);
    }

    private void setConstantOptionsOnFields(Model model) {
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("taskStatus", TaskStatus.values());
        model.addAttribute("taskTypes", TaskType.values());
    }

    private void setVariablesOptionsOnFields(Model model) {
        List<Project> projects = projectService.findAllByActiveTrue();
        List<Category> categories = categoryService.findAllByActiveTrue();
        List<User> users = userService.findAllByActiveTrue();
        List<Customer> customers = customerService.findAllByActiveTrue();

        model.addAttribute("projects", projects);
        model.addAttribute("categories", categories);
        model.addAttribute("responsibles", users);
        model.addAttribute("reporters", users);
        model.addAttribute("customers", customers);
        model.addAttribute("observers", users);
    }
}
