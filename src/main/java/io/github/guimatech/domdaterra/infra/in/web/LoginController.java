package io.github.guimatech.domdaterra.infra.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = { "/login", "/" })
    public String login() {
        return "login";
    }

    @GetMapping(path = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("message", "Credenciais inválidas");
        return "login";
    }
}