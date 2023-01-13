package io.github.guimatech.domdaterra.infra.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

import static io.github.guimatech.domdaterra.shared.util.GlobalVariables.NOTICE_LOG;

@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {
        if (Objects.isNull(modelAndView))
            return;

        setGlobalVariables(modelAndView.getModel());
    }

    void setGlobalVariables(Map<String, Object> model) {
        model.put("NOTICE_LOG", NOTICE_LOG);
    }
}
