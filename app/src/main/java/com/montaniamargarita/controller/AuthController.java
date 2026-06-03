package com.montaniamargarita.controller;

import com.montaniamargarita.dto.request.LoginRequest;
import com.montaniamargarita.dto.response.AuthResponse;
import com.montaniamargarita.dto.response.UsuarioResponse;
import com.montaniamargarita.mapper.UsuarioMapper;
import com.montaniamargarita.model.UsuarioEntity;
import com.montaniamargarita.service.IAuthService;
import com.montaniamargarita.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints públicos de autenticación (login) y consulta del perfil propio.
 */
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Autenticación y perfil del usuario")
public class AuthController {

    private final IAuthService authService;
    private final IUsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public AuthController(IAuthService authService,
                          IUsuarioService usuarioService,
                          UsuarioMapper usuarioMapper) {
        this.authService = authService;
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @Operation(summary = "Iniciar sesión con usuario y PIN. Devuelve un JWT.")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.autenticar(request));
    }

    @Operation(summary = "Devuelve los datos del usuario autenticado")
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> me(Authentication authentication) {
        String idUsuario = (String) authentication.getPrincipal();
        UsuarioEntity usuario = usuarioService.obtenerPorId(idUsuario);
        return ResponseEntity.ok(usuarioMapper.toResponse(usuario));
    }
}
