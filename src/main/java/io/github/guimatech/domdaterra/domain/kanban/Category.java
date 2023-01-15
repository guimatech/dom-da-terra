package io.github.guimatech.domdaterra.domain.kanban;

import io.github.guimatech.domdaterra.domain.PersistentObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table
@Entity
public class Category extends PersistentObject {

    @NotBlank(message = "O referência do bloco não pode ser vázio")
    @Size(max = 60, message = "O referência do bloco está muito grande")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "O descrição do bloco não pode ser vázio")
    @Size(max = 280, message = "O descrição do bloco está muito grande")
    @Column(nullable = false)
    private String description;
}
