package io.github.guimatech.domdaterra.domain;

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
public class Collaborator extends PersistentObject {

    @NotBlank(message = "O nome do colaborador não pode ser vázio")
    @Size(max = 60, message = "O nome do colaborador está muito grande")
    @Column(nullable = false)
    private String name;
}
