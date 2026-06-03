package com.montaniamargarita.config;

import com.montaniamargarita.model.CategoriaEntity;
import com.montaniamargarita.model.MesaEntity;
import com.montaniamargarita.model.ProductoEntity;
import com.montaniamargarita.model.UsuarioEntity;
import com.montaniamargarita.model.enums.EstadoMesa;
import com.montaniamargarita.model.enums.Rol;
import com.montaniamargarita.repository.CategoriaRepository;
import com.montaniamargarita.repository.MesaRepository;
import com.montaniamargarita.repository.ProductoRepository;
import com.montaniamargarita.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Carga datos iniciales en la base si está vacía.
 * - 12 mesas
 * - 1 administrador y 1 mesero por defecto
 * - Categorías y productos de ejemplo
 *
 * Se ejecuta automáticamente al iniciar la aplicación.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final MesaRepository mesaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UsuarioRepository usuarioRepository,
                      MesaRepository mesaRepository,
                      CategoriaRepository categoriaRepository,
                      ProductoRepository productoRepository,
                      PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.mesaRepository = mesaRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        sembrarUsuarios();
        sembrarMesas();
        sembrarCategoriasYProductos();
    }

    private void sembrarUsuarios() {
        if (usuarioRepository.count() == 0) {
            UsuarioEntity admin = new UsuarioEntity(
                    "Administrador Principal", "admin",
                    passwordEncoder.encode("1234"), Rol.ADMINISTRADOR);
            UsuarioEntity mesero = new UsuarioEntity(
                    "Mesero Demo", "mesero1",
                    passwordEncoder.encode("0000"), Rol.MESERO);
            usuarioRepository.save(admin);
            usuarioRepository.save(mesero);
        }
    }

    private void sembrarMesas() {
        if (mesaRepository.count() == 0) {
            for (int i = 1; i <= 12; i++) {
                mesaRepository.save(new MesaEntity(i, EstadoMesa.LIBRE));
            }
        }
    }

    private void sembrarCategoriasYProductos() {
        if (categoriaRepository.count() == 0) {
            CategoriaEntity platos = categoriaRepository.save(
                    new CategoriaEntity("Platos Fuertes", "Platos típicos regionales"));
            CategoriaEntity bebidas = categoriaRepository.save(
                    new CategoriaEntity("Bebidas", "Jugos naturales y refrescos"));

            productoRepository.save(
                    new ProductoEntity("Sancocho Trifásico", platos.getId(), new BigDecimal("25000")));
            productoRepository.save(
                    new ProductoEntity("Trucha al ajillo", platos.getId(), new BigDecimal("30000")));
            productoRepository.save(
                    new ProductoEntity("Limonada de coco", bebidas.getId(), new BigDecimal("8000")));
            productoRepository.save(
                    new ProductoEntity("Jugo de mora", bebidas.getId(), new BigDecimal("6000")));
        }
    }
}
