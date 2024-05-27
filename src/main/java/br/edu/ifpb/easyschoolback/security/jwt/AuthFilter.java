package br.edu.ifpb.easyschoolback.security.jwt;

import br.edu.ifpb.easyschoolback.security.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {

            final var webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();
            final var token = tokenUtils.getTokenFromCookie(request);
            log.info("Token from cookie: {}", token); // Adicione esta linha

            if (Objects.nonNull(token) && tokenUtils.validateToken(token)) {
                final var email = tokenUtils.getUsername(token);
                final var loadedUser = userDetailsServiceImpl.loadUserByUsername(email);

                if (!loadedUser.isEnabled()) {
                    throw new AccessDeniedException("User disabled!");
                }
                final var authentication = new UsernamePasswordAuthenticationToken(
                        loadedUser,
                        null,
                        loadedUser.getAuthorities()
                );
                authentication.setDetails(webAuthenticationDetailsSource.buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (final Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
