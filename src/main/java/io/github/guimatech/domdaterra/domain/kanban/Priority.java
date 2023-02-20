package io.github.guimatech.domdaterra.domain.kanban;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {
    LOW("Baixa"),
    MEDIUM("Média"),
    HIGH("Alta");

    private final String description;
}
