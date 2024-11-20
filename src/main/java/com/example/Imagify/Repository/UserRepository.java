package com.example.Imagify.Repository;

import com.example.Imagify.Model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manejar operaciones CRUD y consultas personalizadas relacionadas con la entidad User.
 * @author Dev
 * @version 1.0
 * @since 1.1
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * Encuentra un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario.
     * @return El usuario con el correo electrónico especificado, o null si no se encuentra.
     */
    public User findByEmail(String email);
    Optional<User> findByUsername(String username);
}
