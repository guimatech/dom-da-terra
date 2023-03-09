package io.github.guimatech.domdaterra.infra.in.web;

import io.github.guimatech.domdaterra.application.service.billing.BillingService;
import io.github.guimatech.domdaterra.domain.billing.Billing;
import io.github.guimatech.domdaterra.infra.in.web.dto.BillingRequest;
import io.github.guimatech.domdaterra.infra.in.web.mapper.BillingMapper;
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
@RequestMapping(path = "/billings")
public class BillingController {

    private static final String MESSAGE = "message";
    private static final String BILLINGS = "billings";
    private static final int DEFAULT_PAGE_SIZE = 6;
    public static final String BILLING_DETAIL = "billing-detail";

    @Autowired
    private BillingService service;

    @GetMapping
    public String findAll(@RequestParam(required = false) Optional<Integer> page,
                          @RequestParam(required = false) Optional<Integer> size,
                          Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<Billing> billingPage = service.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("billingPage", billingPage);

        int totalPages = billingPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return BILLINGS;
    }

    @GetMapping("/new")
    public String newBilling(Model model) {
        model.addAttribute("billing", new Billing());
        ControllerHelper.setEditMode(model, true);

        return BILLING_DETAIL;
    }

    @GetMapping("/edit/{id}")
    public String editBilling(Model model, @PathVariable Long id) {
        Billing billing = service.findById(id);
        model.addAttribute("billing", billing);
        ControllerHelper.setEditMode(model, true);

        return BILLING_DETAIL;
    }

    @GetMapping("/delete/{id}")
    public String deleteBilling(@PathVariable Long id) {
        service.deleteById(id);

        return "redirect:/billings";
    }

    @PostMapping("/save")
    public String saveBilling(@ModelAttribute("billing") @Valid BillingRequest billingRequest,
                              Errors errors,
                              Model model) {

        if (!errors.hasErrors()) {
            try {
                var billing = BillingMapper.INSTANCE.requestToDomain(billingRequest);
                service.save(billing);
                model.addAttribute(MESSAGE, "Cobrança gravada com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("email", "001", e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, true);

        return BILLING_DETAIL;
    }
}