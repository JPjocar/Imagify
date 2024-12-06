package com.example.Imagify;


import com.example.Imagify.Repository.UserRepository;
import com.example.Imagify.Service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {

<<<<<<< HEAD
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//    }
//
//    @Test
//    public void testSave() {
//        UserRegisterDTO dto = new UserRegisterDTO("Test User", "test@example.com", "password");
//        User user = new User(dto.getNombre(), dto.getEmail(), "encodedPassword", 
//                Collections.singletonList(new Role("ROLE_USER")));
//
//        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPassword");
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        User savedUser = userService.save(dto);
//
//        assertEquals("Test User", savedUser.getNombre());
//        assertEquals("test@example.com", savedUser.getEmail());
//        assertEquals("encodedPassword", savedUser.getPassword());
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    public void testLoadUserByUsername_UserFound() {
//        User user = new User("Test User", "test@example.com", "encodedPassword", 
//                Collections.singletonList(new Role("ROLE_USER")));
//
//        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
//
//        UserDetails userDetails = userService.loadUserByUsername("test@example.com");
//
//        assertNotNull(userDetails);
//        assertEquals("test@example.com", userDetails.getUsername());
//    }
//
//    @Test
//    public void testLoadUserByUsername_UserNotFound() {
//        when(userRepository.findByEmail("unknown@example.com")).thenReturn(null);
//        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(
//                "unknown@example.com"));
//    }
=======
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
        UserRegisterDTO dto = new UserRegisterDTO("TestUser", "test@example.com", "Pass1234");
        User user = new User(dto.getNombre(), dto.getEmail(), "encodedPassword123",
                Collections.singletonList(new Role("ROLE_USER")));

        // Configurar mocks
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPassword123");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act: Guardar el usuario
        User savedUser = userService.save(dto);

        // Assert: Verificar resultados
        System.out.println("=== TEST: Guardar Usuario ===");
        System.out.println("Esperado: Nombre = TestUser, Email = test@example.com, Password = encodedPassword123");
        System.out.println("Obtenido: Nombre = " + savedUser.getNombre()
                + ", Email = " + savedUser.getEmail()
                + ", Password = " + savedUser.getPassword());

        assertEquals("TestUser", savedUser.getNombre(), "El nombre del usuario no coincide");
        assertEquals("test@example.com", savedUser.getEmail(), "El email del usuario no coincide");
        assertEquals("encodedPassword123", savedUser.getPassword(), "La contraseña del usuario no coincide");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        User user = new User("Test User", "test@example.com", "encodedPassword",
                Collections.singletonList(new Role("ROLE_USER")));

        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername("test@example.com");

        System.out.println("=== TEST: Cargar Usuario por Email (Encontrado) ===");
        System.out.println("Esperado: Email = test@example.com");
        System.out.println("Obtenido: Email = " + userDetails.getUsername());

        assertNotNull(userDetails, "El usuario encontrado no debe ser nulo");
        assertEquals("test@example.com", userDetails.getUsername(), "El email del usuario no coincide");
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(null);

        System.out.println("=== TEST: Cargar Usuario por Email (No Encontrado) ===");
        System.out.println("Esperado: Se lanza UsernameNotFoundException para unknown@example.com");

        Exception exception = assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("unknown@example.com"));

        System.out.println("Obtenido: Excepción lanzada = " + exception.getClass().getSimpleName());
    }
>>>>>>> 56747a701de98c3282bb45c5220033c8ab487cd2
    
}
