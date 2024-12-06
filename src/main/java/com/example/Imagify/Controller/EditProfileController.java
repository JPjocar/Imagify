
package com.example.Imagify.Controller;

import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Service.UserService;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j

@Controller
@RequestMapping("/profile")
public class EditProfileController {

    private final UserService userService;

    public EditProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String showEditProfileForm(Model model) {

        log.info("Mostrando formulario de editar perfil");

        UserRegisterDTO currentUserDTO = userService.getCurrentUserDTO();
        model.addAttribute("user", currentUserDTO);
        return "View/Images/categories"; 
    }

    @PostMapping("/edit")

    public String updateProfile(@Valid @ModelAttribute("user") UserRegisterDTO registerDTO, BindingResult bindingResult, Model model) {
        log.info("Actualizando perfil de usuario: {}", registerDTO);
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors: {}", bindingResult.getAllErrors());
            return "View/Images/categories"; 
        }
        try {
            log.info("Perfil de usuario actualizado exitosamente");
            userService.update(registerDTO);
            return "redirect:/profile?success"; // exitosa
        } catch (IllegalArgumentException e) {
            log.error("Invalid input data: {}", e.getMessage());
            bindingResult.reject("error.input", e.getMessage()); // Error en los datos de entrada
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            bindingResult.reject("error.general", "Ha ocurrido un error inesperado"); // Error general
        }
        
        return "View/Images/categories";
    }
}

