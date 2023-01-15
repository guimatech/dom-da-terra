package io.github.guimatech.domdaterra.domain.billing;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Button implements Serializable {

    private String id;
    private String label;
}