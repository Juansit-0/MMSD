package com.montaniamargarita.mapper;

import com.montaniamargarita.dto.response.UsuarioResponse;
import com.montaniamargarita.model.UsuarioEntity;
import org.springframework.stereotype.Component;

/**
 * Convierte entidad de usuario a DTO de respuesta.
 * Nunca expone el pinHash.
 */
@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(UsuarioEntity usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombreCompleto(),
                usuario.getUsuario(),
                usuario.getRol(),
                usuario.isActivo(),
                usuario.getFechaCreacion());
    }
}
