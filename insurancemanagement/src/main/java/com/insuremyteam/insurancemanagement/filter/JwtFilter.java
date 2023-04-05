package com.insuremyteam.insurancemanagement.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new ServletException("Missing or Invalid Token");
        } else {
            String jwtToken = authHeader.substring(7);

            try {
                Claims claims = Jwts.parserBuilder().setSigningKey("DoNotShareSuperSecretKeyWithAnyone".getBytes()).build().parseClaimsJws(jwtToken).getBody();
                request.setAttribute("claims", claims.getSubject());

                filterChain.doFilter(servletRequest, servletResponse);  //some more filters , controller
            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token Mismatch");
            }
        }
    }
}