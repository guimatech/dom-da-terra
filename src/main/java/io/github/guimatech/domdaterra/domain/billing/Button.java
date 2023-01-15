package io.github.guimatech.domdaterra.domain.billing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Button {

    private String id;
    private String label;
}