package com.montaniamargarita.controller;

import com.montaniamargarita.dto.request.CrearCategoriaRequest;
import com.montaniamargarita.dto.response.CategoriaResponse;
import com.montaniamargarita.model.CategoriaEntity;
import com.montaniamargarita.service.ICategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Endpoints de gestión de categorías del catálogo.
 * Listar: todos los autenticados. Crear: solo administrador.
 */
@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name="Categorías", description="Catálogo de categorías de productos")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(summary = "Listar todas las categorías")
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listar() {
        List<CategoriaResponse> respuesta = new ArrayList<>();
        for (CategoriaEntity categoria : categoriaService.listar()) {
            respuesta.add(new CategoriaResponse(
                    categoria.getId(),
                    categoria.getNombre(),
                    categoria.getDescripcion(),
                    categoria.isActivo()));
        }
        return ResponseEntity.ok(respuesta);
    }

    /**
	 * 
	 * @param request
	 */
	@Operation(summary="Crear una nueva categoría")
	@PostMapping
	@PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CrearCategoriaRequest request) {
        CategoriaEntity creada = categoriaService.crear(request);
        CategoriaResponse respuesta = new CategoriaResponse(
                creada.getId(),
                creada.getNombre(),
                creada.getDescripcion(),
                creada.isActivo());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
