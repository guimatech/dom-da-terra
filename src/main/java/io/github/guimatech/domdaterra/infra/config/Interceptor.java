package io.github.guimatech.domdaterra.infra.config;

import io.github.guimatech.domdaterra.application.service.billing.NoticeLogService;
import io.github.guimatech.domdaterra.shared.util.GlobalVariables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private NoticeLogService noticeLogService;

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
        GlobalVariables.getNoticeLogs = noticeLogService.findAllByCurrentDate();
        model.put("NOTICE_LOGS", GlobalVariables.getNoticeLogs);
    }
}
