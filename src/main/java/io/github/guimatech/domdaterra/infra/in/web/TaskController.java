package io.github.guimatech.domdaterra.infra.in.web;

import io.github.guimatech.domdaterra.application.service.kanban.TaskService;
import io.github.guimatech.domdaterra.domain.kanban.Task;
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
    private static final int DEFAULT_PAGE_SIZE = 6;
    public static final String TASK_DETAIL = "task-detail";

    @Autowired
    private TaskService service;

    @GetMapping
    public String findAll(@RequestParam(required = false) Optional<Integer> page,
                          @RequestParam(required = false) Optional<Integer> size,
                          Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<Task> taskPage = service.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("taskPage", taskPage);

        int totalPages = taskPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return TASKS;
    }

    @GetMapping("/kanban")
    public String findAllByStatusNotFiled(@RequestParam(required = false) Optional<Integer> page,
                                          @RequestParam(required = false) Optional<Integer> size,
                                          Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<Task> taskPage = service.findAllByStatusNotFiled(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("taskPage", taskPage);

        int totalPages = taskPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return TASKS;
    }

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        ControllerHelper.setEditMode(model, true);

        return TASK_DETAIL;
    }

    @GetMapping("/edit/{id}")
    public String editTask(Model model, @PathVariable Long id) {
        Task task = service.findById(id);
        model.addAttribute("task", task);
        ControllerHelper.setEditMode(model, true);

        return TASK_DETAIL;
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        service.deleteById(id);

        return "redirect:/tasks";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") @Valid Task task,
                              Errors errors,
                              Model model) {

        if (!errors.hasErrors()) {
            try {
                service.save(task);
                model.addAttribute(MESSAGE, "Cobrança gravada com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", "001", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, true);

        return TASK_DETAIL;
    }
}
