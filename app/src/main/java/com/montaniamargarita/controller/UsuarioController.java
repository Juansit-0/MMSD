package com.montaniamargarita.controller;

import com.montaniamargarita.dto.request.ActualizarPinRequest;
import com.montaniamargarita.dto.request.CrearUsuarioRequest;
import com.montaniamargarita.dto.response.UsuarioResponse;
import com.montaniamargarita.mapper.UsuarioMapper;
import com.montaniamargarita.model.UsuarioEntity;
import com.montaniamargarita.service.IUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Endpoints de gestión de usuarios. Solo accesibles por ADMINISTRADOR.
 */
@RestController
@RequestMapping("/api/v1/usuarios")
@PreAuthorize("hasRole('ADMINISTRADOR')")
@Tag(name="Usuarios", description="Gestión de personal del restaurante")
public class UsuarioController {

    private final IUsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(IUsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        List<UsuarioResponse> respuesta = new ArrayList<>();
        for (UsuarioEntity usuario : usuarioService.listar()) {
            respuesta.add(usuarioMapper.toResponse(usuario));
        }
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Crear un nuevo usuario (mesero o administrador)")
    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@Valid @RequestBody CrearUsuarioRequest request) {
        UsuarioEntity creado = usuarioService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.toResponse(creado));
    }

    /**
	 * 
	 * @param id
	 * @param request
	 */
	@Operation(summary="Restablecer el PIN de un usuario")
	@PostMapping("/{id}/reset-pin")
    public ResponseEntity<Void> resetPin(@PathVariable String id,
                                         @Valid @RequestBody ActualizarPinRequest request) {
        usuarioService.actualizarPin(id, request.nuevoPin());
        return ResponseEntity.noContent().build();
    }
}
