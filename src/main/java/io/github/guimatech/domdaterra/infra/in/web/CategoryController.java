package io.github.guimatech.domdaterra.infra.in.web;

import io.github.guimatech.domdaterra.application.service.kanban.CategoryService;
import io.github.guimatech.domdaterra.domain.kanban.Category;
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
@RequestMapping(path = "/categories")
public class CategoryController {

    private static final String MESSAGE = "message";
    private static final String CATEGORIES = "categories";
    private static final int DEFAULT_PAGE_SIZE = 6;
    public static final String CATEGORY_DETAIL = "category-detail";

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String findAllCategorys(@RequestParam(required = false) Optional<Integer> page,
                               @RequestParam(required = false) Optional<Integer> size,
                               Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<Category> categoryPage = categoryService.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("categoryPage", categoryPage);

        int totalPages = categoryPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return CATEGORIES;
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        model.addAttribute("category", new Category());
        ControllerHelper.setEditMode(model, true);

        return CATEGORY_DETAIL;
    }

    @GetMapping("/edit/{id}")
    public String editCategory(Model model, @PathVariable Long id) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        ControllerHelper.setEditMode(model, true);

        return CATEGORY_DETAIL;
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);

        return "redirect:/categories";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") @Valid Category category,
                              Errors errors,
                              Model model) {

        if (!errors.hasErrors()) {
            try {
                categoryService.save(category);
                model.addAttribute(MESSAGE, "Bloco gravado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", "001", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, true);

        return CATEGORY_DETAIL;
    }
}
