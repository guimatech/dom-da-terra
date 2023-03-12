package io.github.guimatech.domdaterra.infra.in.web;

import io.github.guimatech.domdaterra.application.service.UserGroupService;
import io.github.guimatech.domdaterra.application.service.UserService;
import io.github.guimatech.domdaterra.domain.Customer;
import io.github.guimatech.domdaterra.domain.User;
import io.github.guimatech.domdaterra.domain.UserGroup;
import io.github.guimatech.domdaterra.infra.in.web.dto.CategoryRequest;
import io.github.guimatech.domdaterra.infra.in.web.dto.UserRequest;
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
@RequestMapping(path = "/users")
public class UserController {

    private static final String MESSAGE = "message";
    private static final String USERS = "users";
    private static final int DEFAULT_PAGE_SIZE = 6;
    public static final String USER_DETAIL = "user-detail";

    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }

    @GetMapping
    public String findAllUsers(@RequestParam(required = false) Optional<Integer> page,
                                   @RequestParam(required = false) Optional<Integer> size,
                                   Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<User> userPage = userService.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("userPage", userPage);
        List<UserGroup> userGroups = userGroupService.findAllByActiveTrue();

        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("userRequest", new UserRequest());
        model.addAttribute("userGroups", userGroups);

        return USERS;
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        ControllerHelper.setEditMode(model, true);

        return USER_DETAIL;
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        ControllerHelper.setEditMode(model, true);

        return USER_DETAIL;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);

        return "redirect:/users";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                               Errors errors,
                               Model model) {

        if (!errors.hasErrors()) {
            try {
                userService.save(user);
                model.addAttribute(MESSAGE, "Colaborador gravado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", "001", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, true);

        return USER_DETAIL;
    }
}
