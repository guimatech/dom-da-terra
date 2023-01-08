package io.github.guimatech.domdaterra.infra.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KanbanController {

    @GetMapping("/kanban")
    public String kanban(Model model) {
        return "kanban";
    }
}