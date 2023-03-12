package io.github.guimatech.domdaterra.infra.in.web.dto;

import io.github.guimatech.domdaterra.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProjectRequest {

    private Long id;

    @NotBlank(message = "A nome não pode ser vázio")
    private String name;

    @NotNull(message = "O Cliente não pode ser nulo")
    private Customer customer;
}
