package io.github.guimatech.domdaterra.infra.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeLogController {

    @GetMapping("/notice-logs")
    public String register(Model model) {
        return "notice-logs";
    }
}