package com.montaniamargarita.controller;

import com.montaniamargarita.dto.request.CrearProductoRequest;
import com.montaniamargarita.dto.response.ProductoResponse;
import com.montaniamargarita.model.ProductoEntity;
import com.montaniamargarita.service.IProductoService;
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
 * Endpoints de gestión del catálogo de productos.
 * Listar: todos los autenticados. Crear: solo administrador.
 */
@RestController
@RequestMapping("/api/v1/productos")
@Tag(name="Productos", description="Catálogo de productos vendibles")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listar() {
        List<ProductoResponse> respuesta = new ArrayList<>();
        for (ProductoEntity producto : productoService.listar()) {
            respuesta.add(new ProductoResponse(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getCategoriaId(),
                    producto.getPrecio(),
                    producto.isActivo()));
        }
        return ResponseEntity.ok(respuesta);
    }

    /**
	 * 
	 * @param request
	 */
	@Operation(summary="Crear un nuevo producto")
	@PostMapping
	@PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody CrearProductoRequest request) {
        ProductoEntity creado = productoService.crear(request);
        ProductoResponse respuesta = new ProductoResponse(
                creado.getId(),
                creado.getNombre(),
                creado.getCategoriaId(),
                creado.getPrecio(),
                creado.isActivo());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
