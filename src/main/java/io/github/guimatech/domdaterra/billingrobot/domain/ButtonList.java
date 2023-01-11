package io.github.guimatech.domdaterra.billingrobot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ButtonList {

    private List<Button> buttons;
}
