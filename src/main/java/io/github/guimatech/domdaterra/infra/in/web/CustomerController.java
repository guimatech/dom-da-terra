package io.github.guimatech.domdaterra.infra.in.web;

import io.github.guimatech.domdaterra.application.service.CustomerService;
import io.github.guimatech.domdaterra.domain.Customer;
import io.github.guimatech.domdaterra.infra.in.web.dto.CategoryRequest;
import io.github.guimatech.domdaterra.infra.in.web.dto.CustomerRequest;
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
@RequestMapping(path = "/customers")
public class CustomerController {

    private static final String MESSAGE = "message";
    private static final String CUSTOMERS = "customers";
    private static final int DEFAULT_PAGE_SIZE = 6;
    public static final String CUSTOMER_DETAIL = "customer-detail";

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String findAllCustomers(@RequestParam(required = false) Optional<Integer> page,
                                   @RequestParam(required = false) Optional<Integer> size,
                                   Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<Customer> customerPage = customerService.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("customerPage", customerPage);

        int totalPages = customerPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("customerRequest", new CustomerRequest());

        return CUSTOMERS;
    }

    @GetMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        ControllerHelper.setEditMode(model, true);

        return CUSTOMER_DETAIL;
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(Model model, @PathVariable Long id) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        ControllerHelper.setEditMode(model, true);

        return CUSTOMER_DETAIL;
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);

        return "redirect:/customers";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") @Valid Customer customer,
                               Errors errors,
                               Model model) {

        if (!errors.hasErrors()) {
            try {
                customerService.save(customer);
                model.addAttribute(MESSAGE, "Cliente gravado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", "001", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, true);

        return CUSTOMER_DETAIL;
    }
}