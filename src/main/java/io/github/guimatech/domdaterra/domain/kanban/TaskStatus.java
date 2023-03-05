package io.github.guimatech.domdaterra.domain.kanban;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {
    TO_DO("A fazer"),
    DOING("Em progresso"),
    REVIEW("Em revisão"),
    DONE("Concluído");

    private final String description;
}
