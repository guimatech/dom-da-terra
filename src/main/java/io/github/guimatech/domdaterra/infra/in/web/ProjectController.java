package io.github.guimatech.domdaterra.infra.in.web;

import io.github.guimatech.domdaterra.application.service.CustomerService;
import io.github.guimatech.domdaterra.application.service.kanban.ProjectService;
import io.github.guimatech.domdaterra.domain.Customer;
import io.github.guimatech.domdaterra.domain.kanban.Project;
import io.github.guimatech.domdaterra.infra.in.web.dto.NoticeLogRequest;
import io.github.guimatech.domdaterra.infra.in.web.dto.ProjectRequest;
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
@RequestMapping(path = "/projects")
public class ProjectController {

    private static final String MESSAGE = "message";
    private static final String PROJECTS = "projects";
    private static final int DEFAULT_PAGE_SIZE = 6;
    public static final String PROJECT_DETAIL = "project-detail";

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String findAllProjects(@RequestParam(required = false) Optional<Integer> page,
                                   @RequestParam(required = false) Optional<Integer> size,
                                   Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<Project> projectPage = projectService.findAll(PageRequest.of(currentPage - 1, pageSize));
        List<Customer> customers = customerService.findAllByActiveTrue();

        model.addAttribute("projectPage", projectPage);

        int totalPages = projectPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("projectRequest", new ProjectRequest());
        model.addAttribute("customers", customers);

        return PROJECTS;
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        ControllerHelper.setEditMode(model, true);

        return PROJECT_DETAIL;
    }

    @GetMapping("/edit/{id}")
    public String editProject(Model model, @PathVariable Long id) {
        Project project = projectService.findById(id);
        model.addAttribute("project", project);
        ControllerHelper.setEditMode(model, true);

        return PROJECT_DETAIL;
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);

        return "redirect:/projects";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute("project") @Valid Project project,
                               Errors errors,
                               Model model) {

        if (!errors.hasErrors()) {
            try {
                projectService.save(project);
                model.addAttribute(MESSAGE, "Projeto gravado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", "001", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, true);

        return PROJECT_DETAIL;
    }
}
