package io.github.guimatech.domdaterra.infra.in.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.ui.Model;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerHelper {

    public static void setEditMode(Model model, boolean isEdit) {
        model.addAttribute("editMode", isEdit);
    }
}
