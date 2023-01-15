package io.github.guimatech.domdaterra.domain.kanban;

import io.github.guimatech.domdaterra.domain.PersistentObject;
import io.github.guimatech.domdaterra.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table
@Entity
public class Historic extends PersistentObject {

    @NotBlank(message = "O descrição do histórico não pode ser vázio")
    @Size(max = 280, message = "O descrição do histórico está muito grande")
    @Column(nullable = false)
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Task task;
}
