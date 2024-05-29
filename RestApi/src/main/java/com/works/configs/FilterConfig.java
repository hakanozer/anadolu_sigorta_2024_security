package com.works.configs;

import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    private final InfoRepository infoRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String sessionId = req.getSession().getId();
        String name = "";
        String role = "";
        Long longDate = new Date().getTime();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            name = auth.getName();
            role = auth.getAuthorities().toString();
        }

        Info i = new Info();
        i.setUrl(url);
        i.setSessionId(sessionId);
        i.setName(name);
        i.setRole(role);
        i.setLongDate(longDate);
        infoRepository.save(i);

        filterChain.doFilter(req, resp);
    }

}
