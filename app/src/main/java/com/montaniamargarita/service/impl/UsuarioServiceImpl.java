package com.montaniamargarita.service.impl;

import com.montaniamargarita.dto.request.CrearUsuarioRequest;
import com.montaniamargarita.exception.RecursoNoEncontradoException;
import com.montaniamargarita.exception.UsuarioYaExisteException;
import com.montaniamargarita.model.UsuarioEntity;
import com.montaniamargarita.repository.UsuarioRepository;
import com.montaniamargarita.service.IUsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de gestión de usuarios.
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioEntity crear(CrearUsuarioRequest request) {
        // Verificar unicidad de usuario
        if (usuarioRepository.findByUsuario(request.usuario()).isPresent()) {
            throw new UsuarioYaExisteException(request.usuario());
        }
        String pinHash = passwordEncoder.encode(request.pin());
        UsuarioEntity nuevo = new UsuarioEntity(
                request.nombreCompleto(),
                request.usuario(),
                pinHash,
                request.rol());
        return usuarioRepository.save(nuevo);
    }

    @Override
    public UsuarioEntity obtenerPorId(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", id));
    }

    @Override
    public List<UsuarioEntity> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public void actualizarPin(String id, String nuevoPin) {
        UsuarioEntity usuario = obtenerPorId(id);
        usuario.setPinHash(passwordEncoder.encode(nuevoPin));
        usuarioRepository.save(usuario);
    }
}
