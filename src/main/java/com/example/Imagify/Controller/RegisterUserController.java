package com.example.Imagify.Controller;

import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador que maneja las solicitudes de registro de usuarios.
 * @author Jocar
 * @version 1.0
 * @since 1.1
 */

@Controller
@RequestMapping("/register")

public class RegisterUserController {
    private UserService userService;

    /**
     * Constructor para inyectar el servicio de usuarios.
     *
     * @param userService El servicio de usuarios que se va a inyectar.
     */
    public RegisterUserController(UserService userService) {
        super();
        this.userService = userService;
    }

    /**
     * Añade un objeto UserRegisterDTO al modelo.
     *
     * @return Un nuevo objeto UserRegisterDTO.
     */
    @ModelAttribute("user")
    public UserRegisterDTO returnNewUserRegisterDTO() {
        return new UserRegisterDTO();
    }

    /**
     * Maneja las solicitudes GET para mostrar el formulario de registro de usuarios.
     *
     * @return El nombre de la vista para el formulario de registro.
     */
    @GetMapping
    public String showRegisterForm() {
        return "register";
    }

    /**
     * Maneja las solicitudes POST para registrar una nueva cuenta de usuario.
     *
     * @param registerDTO El objeto UserRegisterDTO que contiene los datos de registro del usuario.
     * @return Una redirección a la página de registro con un indicador de éxito.
     */
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegisterDTO registerDTO) {
        userService.save(registerDTO);
        return "redirect:/register?success";
    }
}
