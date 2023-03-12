package io.github.guimatech.domdaterra.infra.in.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerRequest {

    private Long id;

    @NotBlank(message = "A nome não pode ser vázio")
    private String name;
}
