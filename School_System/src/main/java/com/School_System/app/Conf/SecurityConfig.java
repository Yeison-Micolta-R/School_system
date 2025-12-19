/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.School_System.app.Conf;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

    /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/nivel-academico/**").hasRole("ADMIN") // solo admin
                .anyRequest().authenticated()
            )
            .formLogin()
            .and()
            .logout();
        return http.build();
    }*/


}
