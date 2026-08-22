package com.saqlain.SmartTaskManagerAPI.security;

import com.saqlain.SmartTaskManagerAPI.service.CustomUserDetailsService;
import com.saqlain.SmartTaskManagerAPI.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("\n========== JWT FILTER ==========");
        System.out.println("Request URI: " + request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization Header: " + authHeader);

        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            System.out.println("Token found: YES");

            try {
                email = jwtService.extractEmail(token);
                System.out.println("Email extracted from token: " + email);

            } catch (Exception e) {
                System.out.println("ERROR extracting email: " + e.getMessage());
            }
        } else {
            System.out.println("Token found: NO");
        }

        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            try {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(email);

                System.out.println("User found: " + userDetails.getUsername());

                boolean valid =
                        jwtService.validateToken(token, userDetails);

                System.out.println("Token valid: " + valid);

                if (valid) {

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authToken);

                    System.out.println("Authentication SUCCESS");
                    System.out.println("Authenticated User: "
                            + SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getName());

                }

            } catch (Exception e) {

                System.out.println("JWT Authentication ERROR: "
                        + e.getMessage());

            }
        }

        System.out.println("Authentication before filter chain: "
                + SecurityContextHolder.getContext()
                .getAuthentication());

        System.out.println("================================\n");

        filterChain.doFilter(request, response);
    }
}