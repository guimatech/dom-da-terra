package io.github.guimatech.domdaterra.domain.kanban;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {
    IN_PROGRESS("Em andamento"),
    STAGNANT("Estagnado");

    private final String description;
}
