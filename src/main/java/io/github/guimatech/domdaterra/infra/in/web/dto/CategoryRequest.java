package io.github.guimatech.domdaterra.infra.in.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CategoryRequest {

    private Long id;

    @NotBlank(message = "A nome não pode ser vázio")
    private String name;

    @NotBlank(message = "O descrição do bloco não pode ser vázio")
    private String description;
}
