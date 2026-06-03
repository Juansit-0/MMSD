package com.montaniamargarita.security;

import com.montaniamargarita.model.UsuarioEntity;
import com.montaniamargarita.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Filtro que se ejecuta una sola vez por request.
 * Si la cabecera Authorization contiene un Bearer token válido,
 * coloca el usuario autenticado en el SecurityContext.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String PREFIJO_BEARER = "Bearer ";

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(PREFIJO_BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(PREFIJO_BEARER.length());
        try {
            Claims claims = jwtService.validar(token);
            String idUsuario = claims.getSubject();
            String rol = claims.get("rol", String.class);

            Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);
            if (usuario.isPresent() && usuario.get().isActivo()) {
                UsernamePasswordAuthenticationToken autenticacion =
                        new UsernamePasswordAuthenticationToken(
                                idUsuario,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + rol)));
                autenticacion.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        } catch (JwtException ex) {
            // Token inválido: dejamos pasar la petición sin autenticación.
            // Spring Security devolverá 401 o 403 si el endpoint lo requiere.
        }

        filterChain.doFilter(request, response);
    }
}
