package io.github.guimatech.domdaterra.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Button {

    private String id;
    private String label;
}