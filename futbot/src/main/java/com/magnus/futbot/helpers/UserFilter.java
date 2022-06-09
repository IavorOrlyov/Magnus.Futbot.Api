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

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        try {
            initUser(request);
            chain.doFilter(request, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void initUser(HttpServletRequest request) throws IOException {

        if (request.getDispatcherType() != DispatcherType.REQUEST) {
            return;
        }

        String accessToken = request.getHeader("Access-Token");
        final String uri = System.getenv("MagnusSSO") + "/users/validate-token?accessToken=" + accessToken;

        String userId = new RestTemplate().getForObject(uri, String.class);
        appSettings.setUserId(userId);
    }
}
