/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Controller;

import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Repository.UserRepository;
import com.example.Imagify.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "perfil")
public class EditProfileController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;
    
    public EditProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String showEditProfileForm(Model model) {
        UserRegisterDTO currentUserDTO = userService.getCurrentUserDTO();
        model.addAttribute("user", currentUserDTO);
        System.out.println("ASDASDASDAS");
        return "View/Images/edit"; 
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute("user") UserRegisterDTO userDTO){
        this.userService.save(userDTO);
        return "redirect:/profile/update";
    }

    
}
