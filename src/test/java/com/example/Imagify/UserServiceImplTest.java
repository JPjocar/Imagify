package com.example.Imagify;

import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Model.Role;
import com.example.Imagify.Model.User;
import com.example.Imagify.Repository.UserRepository;
import com.example.Imagify.Service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testSave() {
        UserRegisterDTO dto = new UserRegisterDTO("Test User", "test@example.com", "password");
        User user = new User(dto.getNombre(), dto.getEmail(), "encodedPassword", 
                Collections.singletonList(new Role("ROLE_USER")));

        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.save(dto);

        assertEquals("Test User", savedUser.getNombre());
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        User user = new User("Test User", "test@example.com", "encodedPassword", 
                Collections.singletonList(new Role("ROLE_USER")));

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(
                "unknown@example.com"));
    }
}
