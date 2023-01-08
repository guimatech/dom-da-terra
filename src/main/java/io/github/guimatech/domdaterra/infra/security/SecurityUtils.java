package io.github.guimatech.domdaterra.infra.security;

import io.github.guimatech.domdaterra.domain.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static LoggedUser loggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (LoggedUser) authentication.getPrincipal();
    }

    public static User loggedCliente() {
        return loggedUser().getUser();
    }
}
