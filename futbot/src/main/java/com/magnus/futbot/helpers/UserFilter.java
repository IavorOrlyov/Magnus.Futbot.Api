package com.magnus.futbot.helpers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserFilter implements Filter {

    @Autowired
    private AppSettings appSettings;

    @Value("${MagnusSSO}")
    private String ssoUrl;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        CustomRequestWrapper wrappedRequest = new CustomRequestWrapper(request);
        try {
            initUser(wrappedRequest);
            chain.doFilter(wrappedRequest, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void initUser(CustomRequestWrapper wrappedRequest) throws IOException {

        if (wrappedRequest.getDispatcherType() != DispatcherType.REQUEST) {
            return;
        }

        String accessToken = "";
        if ("POST".equalsIgnoreCase(wrappedRequest.getMethod())
                || "PUT".equalsIgnoreCase(wrappedRequest.getMethod())
                || "PATCH".equalsIgnoreCase(wrappedRequest.getMethod())) {
            byte[] buf = wrappedRequest.getRequestData();
            if (buf.length > 0) {
                try {
                    String requestBody = new String(buf, 0, buf.length, wrappedRequest.getRequest().getCharacterEncoding());
                    accessToken = new JSONObject(requestBody).getString("accessToken");

                } catch (Exception e) {
                    System.out.println("error in reading request body");
                }
            }
        } else if ("GET".equalsIgnoreCase(wrappedRequest.getMethod())) {
            accessToken = wrappedRequest.getParameterValues("accessToken")[0];
        }

        final String uri = ssoUrl + "/users/validate-token?accessToken=" + accessToken;

        String userId = new RestTemplate().getForObject(uri, String.class);
        appSettings.setUserId(userId);
    }
}
