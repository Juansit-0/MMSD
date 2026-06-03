package com.montaniamargarita.service;

import com.montaniamargarita.dto.request.CrearUsuarioRequest;
import com.montaniamargarita.model.UsuarioEntity;

import java.util.List;

/** Servicio de gestión de usuarios del sistema. */
public interface IUsuarioService {

    UsuarioEntity crear(CrearUsuarioRequest request);

    UsuarioEntity obtenerPorId(String id);

    List<UsuarioEntity> listar();

    void actualizarPin(String id, String nuevoPin);
}
