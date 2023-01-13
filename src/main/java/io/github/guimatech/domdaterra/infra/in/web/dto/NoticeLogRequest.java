package io.github.guimatech.domdaterra.infra.in.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoticeLogRequest {

    private Long id;

    @NotEmpty(message = "A descrição não pode ser vázia")
    private String description;

    @NotEmpty(message = "O início do período não pode ser vázio")
    private String startDatePeriod;

    @NotEmpty(message = "O final do período não pode ser vázio")
    private String endDatePeriod;
}
