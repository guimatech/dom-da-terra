package io.github.guimatech.domdaterra.infra.in.web.dto;

import io.github.guimatech.domdaterra.domain.UserGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRequest {

    private Long id;

    @NotBlank(message = "A nome não pode ser vázio")
    private String name;

    @NotBlank(message = "O e-mail não pode ser vázio")
    private String username;

    @NotBlank(message = "A senha não pode ser vázia")
    private String password;

    private String photo;

    private List<UserGroup> userGroups;
}
