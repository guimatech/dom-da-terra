package io.github.guimatech.domdaterra.shared.util;

import org.springframework.ui.Model;

public class Constant {

    public static void setGlobalVariables(Model model) {
        model.addAttribute("NOTICE_LOG", "Aviso urgente: Loren impsu é...!!!");
    }
}
