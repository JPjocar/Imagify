package com.example.Imagify.Service;
import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Model.Role;
import com.example.Imagify.Model.User;
import com.example.Imagify.Repository.UserRepository;
import com.example.Imagify.Validator.UserValidator;
        
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegisterDTO registerDTO) {
        
        if (!UserValidator.isValidName(registerDTO.getNombre())) {
            throw new IllegalArgumentException("Nombre inválido. Debe contener solo letras y tener al menos 3 caracteres.");
        } 
        if (!UserValidator.isValidEmail(registerDTO.getEmail())) {
            throw new IllegalArgumentException("Email no válido.");
        }
        if (!UserValidator.isValidPassword(registerDTO.getEmail())) {
            throw new IllegalArgumentException("La contraseña debe ser alfanumérica y tener al menos 8 caracteres.");
        }
        
        User user = new User(
                registerDTO.getNombre(),
                registerDTO.getEmail(), 
                passwordEncoder.encode(registerDTO.getPassword()), 
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), 
                user.getPassword(), 
                mappingAuthoritiesRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mappingAuthoritiesRoles(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());
    }

}
