package com.montaniamargarita.security;

import com.montaniamargarita.model.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Servicio responsable de generar y validar tokens JWT.
 * Algoritmo: HMAC-SHA256.
 *
 * Usa la API de jjwt 0.12.x (parser(), verifyWith, parseSignedClaims).
 */
@Service
public class JwtService {

    private final SecretKey clave;
    private final long expiracionSegundos;
    private final String issuer;

    public JwtService(
            @Value("${app.jwt.secret}") String secreto,
            @Value("${app.jwt.expiracion-segundos}") long expiracionSegundos,
            @Value("${app.jwt.issuer}") String issuer) {
        this.clave = Keys.hmacShaKeyFor(secreto.getBytes(StandardCharsets.UTF_8));
        this.expiracionSegundos = expiracionSegundos;
        this.issuer = issuer;
    }

    /**
     * Genera un token JWT para un usuario autenticado.
     * Incluye subject (id), claim "usr" (nombre de usuario) y claim "rol".
     */
    public String generarToken(UsuarioEntity usuario) {
        long ahora = System.currentTimeMillis();
        return Jwts.builder()
                .issuer(issuer)
                .subject(usuario.getId())
                .claim("usr", usuario.getUsuario())
                .claim("rol", usuario.getRol().name())
                .issuedAt(new Date(ahora))
                .expiration(new Date(ahora + expiracionSegundos * 1000L))
                .signWith(clave, Jwts.SIG.HS256)
                .compact();
    }

    /**
     * Valida el token y devuelve sus claims.
     * Lanza JwtException si la firma o la expiración no son válidas.
     */
    public Claims validar(String token) {
        return Jwts.parser()
                .verifyWith(clave)
                .requireIssuer(issuer)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public long getExpiracionSegundos() {
        return expiracionSegundos;
    }
}
