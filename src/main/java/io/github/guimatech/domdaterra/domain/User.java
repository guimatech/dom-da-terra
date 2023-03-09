package io.github.guimatech.domdaterra.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class User extends PersistentObject {

    @NotBlank(message = "O nome do usuário não pode ser vázio")
    @Size(max = 60, message = "O nome do usuário está muito grande")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "O e-mail não pode ser vázio")
    @Size(max = 60, message = "O nome do usuário é muito grande")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "A senha não pode ser vázia")
    @Size(max = 80, message = "A senha é muito grande")
    @Column(nullable = false)
    private String password;

    @Column
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserGroup> userGroups;

    public String userGroupNames() {
        return userGroups.stream().map(UserGroup::getName)
                .collect(Collectors.joining(", "));
    }
}
