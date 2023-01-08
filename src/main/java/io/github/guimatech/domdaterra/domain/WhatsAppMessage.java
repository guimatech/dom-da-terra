package io.github.guimatech.domdaterra.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class WhatsAppMessage implements Serializable {

    private String phone;
    private String message;
    private ButtonList buttonList;
}