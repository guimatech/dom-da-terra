package io.github.guimatech.domdaterra.infra.security;

import io.github.guimatech.domdaterra.domain.NoticeLog;
import io.github.guimatech.domdaterra.infra.out.db.repository.NoticeLogRepository;
import io.github.guimatech.domdaterra.shared.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private NoticeLogRepository noticeLogRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                        Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        GlobalVariables.NOTICE_LOG = "Avisos: " + noticeLogRepository.findAllByCurrentDate().stream()
                .map(NoticeLog::getDescription)
                .collect(Collectors.joining(" | "));
        response.sendRedirect("dashboard");
    }
}
