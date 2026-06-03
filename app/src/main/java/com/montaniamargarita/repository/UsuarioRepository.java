package com.montaniamargarita.repository;

import com.montaniamargarita.model.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio Spring Data Mongo para UsuarioEntity.
 * Spring genera la implementación en tiempo de ejecución.
 */
public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {

    /** Busca un usuario por su nombre de usuario. */
    Optional<UsuarioEntity> findByUsuario(String usuario);
}
