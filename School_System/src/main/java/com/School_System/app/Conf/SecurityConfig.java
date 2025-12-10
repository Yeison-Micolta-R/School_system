/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Conf;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author yesec
 */
@Configuration
public class SecurityConfig implements Filter {

    @Override
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        String path = http.getRequestURI();

        if (path.startsWith("/auth/login") || path.startsWith("error")||path.startsWith("/auth/recoverPassword")) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = http.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpRes.getWriter().write("Session no valida o expirada");
            return;
        }
        chain.doFilter(request, response);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
