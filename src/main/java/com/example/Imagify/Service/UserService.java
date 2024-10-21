package com.example.Imagify.Service;

import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User save(UserRegisterDTO registerDTO);
}
