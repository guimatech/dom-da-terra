package io.github.guimatech.domdaterra.domain.kanban;

import io.github.guimatech.domdaterra.domain.Customer;
import io.github.guimatech.domdaterra.domain.PersistentObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table
@Entity
public class Project extends PersistentObject {

    @NotBlank(message = "O nome do projeto não pode ser vázio")
    @Size(max = 60, message = "O nome do projeto está muito grande")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @NotNull(message = "O Cliente não pode ser nulo")
    private Customer customer;
}
