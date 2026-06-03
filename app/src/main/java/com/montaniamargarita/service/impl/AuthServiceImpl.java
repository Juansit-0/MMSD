package com.montaniamargarita.service.impl;

import com.montaniamargarita.dto.request.LoginRequest;
import com.montaniamargarita.dto.response.AuthResponse;
import com.montaniamargarita.exception.PinInvalidoException;
import com.montaniamargarita.model.UsuarioEntity;
import com.montaniamargarita.repository.UsuarioRepository;
import com.montaniamargarita.security.JwtService;
import com.montaniamargarita.service.IAuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación del servicio de autenticación.
 * Verifica usuario+PIN con BCrypt y emite JWT.
 */
@Service
public class AuthServiceImpl implements IAuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse autenticar(LoginRequest request) {
        // Buscar usuario activo
        Optional<UsuarioEntity> opt = usuarioRepository.findByUsuario(request.usuario());
        if (opt.isEmpty() || !opt.get().isActivo()) {
            throw new PinInvalidoException();
        }
        UsuarioEntity usuario = opt.get();

        // Verificar PIN
        if (!passwordEncoder.matches(request.pin(), usuario.getPinHash())) {
            throw new PinInvalidoException();
        }

        // Generar JWT
        String token = jwtService.generarToken(usuario);
        return new AuthResponse(token, usuario.getRol(), jwtService.getExpiracionSegundos());
    }
}
