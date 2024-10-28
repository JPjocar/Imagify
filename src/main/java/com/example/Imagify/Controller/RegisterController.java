package com.example.Imagify.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controlador que maneja las solicitudes de registro y navegación.
 * @author Jocar
 * @version 1.0
 * @since 1.1
 */
@Controller
public class RegisterController {
    
    /**
     * Maneja las solicitudes GET para mostrar la página de inicio de sesión.
     *
     * @return El nombre de la vista para la página de inicio de sesión.
     */
    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    /**
     * Maneja las solicitudes GET para mostrar la página principal.
     *
     * @return El nombre de la vista para la página principal.
     */
    @GetMapping("/")
    public String seeHomepage() {
        return "index";
    }
}
