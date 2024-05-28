package com.works.configs;

import com.works.entities.Customer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Server UP");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String sessionId = req.getSession().getId();
        String agent = req.getHeader("User-Agent");
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }

        String[] freeUrls = {"/", "/login", "/singup"};
        boolean loginStatus = true;
        for (String freeUrl : freeUrls) {
            if (url.equals(freeUrl)) {
                loginStatus = false;
                break;
            }
        }

        if (loginStatus) {
            // session control
            boolean status = req.getSession().getAttribute("customer") == null;
            if (status) {
                res.sendRedirect("/");
            }else {
                Customer customer = (Customer) req.getSession().getAttribute("customer");
                req.setAttribute("customer", customer);
                filterChain.doFilter(req, res);
            }
        }else {
            filterChain.doFilter(req, res);
        }

        System.out.println(url + " - " + ipAddress + " - " + sessionId + " - " + agent);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Server DOWN");
    }

}
