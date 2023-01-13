package io.github.guimatech.domdaterra.infra.in.web;

import io.github.guimatech.domdaterra.application.service.NoticeLogService;
import io.github.guimatech.domdaterra.domain.NoticeLog;
import io.github.guimatech.domdaterra.infra.in.web.dto.NoticeLogRequest;
import io.github.guimatech.domdaterra.infra.in.web.mapper.NoticeLogMapper;
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

import static io.github.guimatech.domdaterra.shared.util.Constant.setGlobalVariables;
import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@Controller
@RequestMapping(path = "/notice-logs")
public class NoticeLogController {

    private static final String MESSAGE = "message";

    private static final String NOTICE_LOGS = "notice-logs";

    @Autowired
    private NoticeLogService service;

    @GetMapping
    public String findAll(@RequestParam(required = false) Optional<Integer> page,
                          @RequestParam(required = false) Optional<Integer> size,
                          Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Page<NoticeLog> noticeLogsPage = service.findAll(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("noticeLogsPage", noticeLogsPage);

        int totalPages = noticeLogsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("noticeLogRequest", new NoticeLogRequest());

        return NOTICE_LOGS;
    }

    @PostMapping("/save/new")
    public String saveNewNoticeLog(
            @ModelAttribute("noticeLogRequest") @Valid NoticeLogRequest noticeLogRequest,
            Errors errors,
            Model model) {
        return save(noticeLogRequest, errors, model);
    }

    @PostMapping("/save/edit/{id}")
    public String saveEditNoticeLog(
            @PathVariable Long id,
            @ModelAttribute("noticeLogRequest") @Valid NoticeLogRequest noticeLogRequest,
            Errors errors,
            Model model) {
        noticeLogRequest.setId(id);
        return save(noticeLogRequest, errors, model);
    }

    private String save(NoticeLogRequest noticeLogRequest, Errors errors, Model model) {
        setGlobalVariables(model);

        if (!errors.hasErrors()) {
            try {
                var noticeLog = NoticeLogMapper.INSTANCE.RequestToDomain(noticeLogRequest);
                service.save(noticeLog);
                model.addAttribute(MESSAGE, "Aviso gravado com sucesso!");
            } catch (ValidationException e) {
                errors.rejectValue("description", null, e.getMessage());
            }
        }

        ControllerHelper.setEditMode(model, true);

        return "redirect:/" + NOTICE_LOGS;
    }
}