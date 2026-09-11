package com.example.board.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        long start = System.nanoTime();
        log.info("BEFORE {} {}", request.getMethod(), request.getRequestURI());
        try {
            chain.doFilter(request, response);
        } finally {
            long tookMs = (System.nanoTime() - start) / 1_000_000;
            log.info("AFTER {} {} -> {} ({}ms)", request.getMethod(),
                    request.getRequestURI(), response.getStatus(), tookMs);
        }
    }
}
