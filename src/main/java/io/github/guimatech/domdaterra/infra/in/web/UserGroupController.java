package io.github.guimatech.domdaterra.infra.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user-groups")
public class UserGroupController {

    @GetMapping
    public String findAllUserGroups(Model model) {
        return "user-groups";
    }
}