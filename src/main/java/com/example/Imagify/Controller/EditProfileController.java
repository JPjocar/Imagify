package com.example.Imagify.Controller;

import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Service.UserService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class EditProfileController {

    private final UserService userService;

    public EditProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String showEditProfileForm(Model model) {
        UserRegisterDTO currentUserDTO = userService.getCurrentUserDTO();
        model.addAttribute("user", currentUserDTO);
        return "View/Images/categories"; 
    }

    @PostMapping("/edit")
    public String updateProfile(@Valid @ModelAttribute("user") UserRegisterDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "View/Images/categories"; 
        }
        try {
            userService.update(userDTO);
            return "redirect:/profile?success"; // exitosa
        } catch (IllegalArgumentException e) {
            bindingResult.reject("error.input", e.getMessage()); // Error en los datos de entrada
        } catch (Exception e) {
            bindingResult.reject("error.general", "Ha ocurrido un error inesperado"); // Error general
        }

        return "View/Images/categories";
    }
}
