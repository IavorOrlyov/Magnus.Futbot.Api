package com.magnus.futbot.helpers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@Configuration
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private AppSettings appSettings;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (request.getDispatcherType() != DispatcherType.REQUEST) {
            return true;
        }

        RequestWrapper wrappedRequest = new RequestWrapper(request);

        if ("POST".equalsIgnoreCase(wrappedRequest.getMethod())
                || "PUT".equalsIgnoreCase(wrappedRequest.getMethod())
                || "PATCH".equalsIgnoreCase(wrappedRequest.getMethod())) {
            var body = wrappedRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject jsonObject = new JSONObject(body);
            appSettings.setUserId(jsonObject.getString("userId"));
        } else if ("GET".equalsIgnoreCase(wrappedRequest.getMethod())) {
            appSettings.setUserId(wrappedRequest.getParameterValues("userId")[0]);
        }

        return true;
    }
}
