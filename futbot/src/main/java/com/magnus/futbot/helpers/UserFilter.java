package com.magnus.futbot.helpers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserFilter implements Filter {

    @Autowired
    private AppSettings appSettings;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        try {
            initUser(wrappedRequest);
            chain.doFilter(wrappedRequest, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void initUser(ContentCachingRequestWrapper wrappedRequest) {

        if (wrappedRequest.getDispatcherType() != DispatcherType.REQUEST) {
            return;
        }

        if ("POST".equalsIgnoreCase(wrappedRequest.getMethod())
                || "PUT".equalsIgnoreCase(wrappedRequest.getMethod())
                || "PATCH".equalsIgnoreCase(wrappedRequest.getMethod())) {
            byte[] buf = wrappedRequest.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    String requestBody = new String(buf, 0, buf.length, wrappedRequest.getCharacterEncoding());
                    JSONObject jsonObject = new JSONObject(requestBody);
                    appSettings.setUserId(jsonObject.getString("userId"));
                } catch (Exception e) {
                    System.out.println("error in reading request body");
                }
            }
        } else if ("GET".equalsIgnoreCase(wrappedRequest.getMethod())) {
            appSettings.setUserId(wrappedRequest.getParameterValues("userId")[0]);
        }
    }
}
