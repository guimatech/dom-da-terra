package io.github.guimatech.domdaterra.domain.billing;

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
