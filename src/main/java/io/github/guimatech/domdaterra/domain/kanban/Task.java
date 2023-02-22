package io.github.guimatech.domdaterra.domain.kanban;

import io.github.guimatech.domdaterra.domain.Customer;
import io.github.guimatech.domdaterra.domain.PersistentObject;
import io.github.guimatech.domdaterra.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Table
@Entity
public class Task extends PersistentObject {

    @NotBlank(message = "O resumo da atividade não pode ser vázio")
    @Size(max = 60, message = "O resumo da atividade está muito grande")
    @Column(nullable = false)
    private String resume;

    @NotBlank(message = "O descrição da atividade não pode ser vázio")
    @Size(max = 280, message = "O descrição da atividade está muito grande")
    @Column(nullable = false)
    private String description;

    private TaskType taskType;

    private Priority priority;

    private LocalDate deadline;

    @OneToOne
    private User responsible;

    @OneToOne
    private User reporter;

    @OneToOne
    private Project project;

    @OneToOne
    private Customer customer;

    private TaskStatus status;

    @OneToMany
    private List<Category> categories;

    @ManyToMany
    private List<User> observers;

    @OneToMany
    private List<Comment> comments;
}
