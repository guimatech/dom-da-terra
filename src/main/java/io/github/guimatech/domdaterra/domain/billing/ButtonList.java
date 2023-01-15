package io.github.guimatech.domdaterra.domain.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ButtonList implements Serializable {

    private List<Button> buttons;
}
