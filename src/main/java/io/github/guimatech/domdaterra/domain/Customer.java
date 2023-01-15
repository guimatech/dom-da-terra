package io.github.guimatech.domdaterra.domain;

import io.github.guimatech.domdaterra.domain.kanban.Project;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table
@Entity
public class Customer extends PersistentObject {

    @NotBlank(message = "O nome do cliente não pode ser vázio")
    @Size(max = 60, message = "O nome do cliente está muito grande")
    @Column(nullable = false)
    private String name;

    @OneToOne
    @NotNull(message = "O Colaborador reponsável não pode ser nulo")
    private Collaborator collaborator;

    @OneToMany
    private List<Project> projects;
}
