package io.github.guimatech.domdaterra.domain.kanban;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskType {
    INTERNAL("Interno"),
    EXTERNAL("Externo");

    private final String description;
}
