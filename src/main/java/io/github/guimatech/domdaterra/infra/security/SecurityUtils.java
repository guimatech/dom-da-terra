package io.github.guimatech.domdaterra.infra.security;

import io.github.guimatech.domdaterra.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    public static LoggedUser loggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (LoggedUser) authentication.getPrincipal();
    }

    public static User loggedCliente() {
        var loggedUser = loggedUser();
        if (Objects.isNull(loggedUser))
            throw new RuntimeException("Não existe usuário logado!");

        var user = loggedUser.getUser();
        if (Objects.isNull(user))
            throw new RuntimeException("Usuário logado não registrado!");

        return user;
    }
}
