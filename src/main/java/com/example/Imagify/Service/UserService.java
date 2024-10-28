package com.example.Imagify.Service;

import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Servicio para manejar operaciones relacionadas con la entidad User.
 * 
 * Extiende UserDetailsService para proporcionar detalles adicionales de los usuarios
 * necesarios para la autenticación y autorización.
 */
public interface UserService extends UserDetailsService {/**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param registerDTO El objeto UserRegisterDTO que contiene los datos de registro del usuario.
     * @return El usuario guardado.
     */
    public User save(UserRegisterDTO registerDTO);
}
