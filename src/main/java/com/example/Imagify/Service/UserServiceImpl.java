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

/**
 * Implementación del servicio de usuarios para manejar operaciones relacionadas con la entidad User.
 * 
 * Proporciona métodos para guardar usuarios y cargar detalles de usuario para autenticación y autorización.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    /**
     * Constructor para inyectar el repositorio de usuarios.
     *
     * @param userRepository El repositorio de usuarios que se va a inyectar.
     */
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param registerDTO El objeto UserRegisterDTO que contiene los datos de registro del usuario.
     * @return El usuario guardado.
     */
    @Override
    public User save(UserRegisterDTO registerDTO) {
        
        if (!UserValidator.isValidName(registerDTO.getNombre())) {
            throw new IllegalArgumentException("Nombre inválido. Debe contener solo letras y tener al menos 3 caracteres.");
        } 
        if (!UserValidator.isValidEmail(registerDTO.getEmail())) {
            throw new IllegalArgumentException("Email no válido.");
        }
//        if (!UserValidator.isValidPassword(registerDTO.getEmail())) {
//            throw new IllegalArgumentException("La contraseña debe ser alfanumérica y tener al menos 8 caracteres.");
//        }
        
        User user = new User(
                registerDTO.getNombre(),
                registerDTO.getEmail(), 
                passwordEncoder.encode(registerDTO.getPassword()), 
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    /**
     * Carga los detalles del usuario por nombre de usuario (correo electrónico).
     *
     * @param username El nombre de usuario (correo electrónico).
     * @return Los detalles del usuario necesarios para la autenticación y autorización.
     * @throws UsernameNotFoundException Si el usuario no se encuentra.
     */
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

    /**
     * Mapea los roles del usuario a las autoridades de seguridad de Spring.
     *
     * @param roles Los roles del usuario.
     * @return Una colección de autoridades concedidas.
     */
    private Collection<? extends GrantedAuthority> mappingAuthoritiesRoles(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .collect(Collectors.toList());
    }
<<<<<<< Updated upstream
=======
    
    
    
    
    
     public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No hay un usuario autenticado");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            // Buscar el usuario por su nombre de usuario para obtener el ID
            User user = userRepository.findByNombre(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            return user.getId();
        } else {
            throw new IllegalStateException("El principal no es de tipo UserDetails");
        }
    }

    @Override
    public UserRegisterDTO getCurrentUserDTO() {
        Long currentUserId = getCurrentUserId();
        User currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UserRegisterDTO(
                currentUser.getId(),
                currentUser.getNombre(),
                currentUser.getEmail(),
                "" 
        );
    }

    @Override
    public User update(UserRegisterDTO registerDTO) {
        if (registerDTO == null) {
            throw new IllegalArgumentException("El DTO no puede ser nulo");
        }
        if (!isValidEmail(registerDTO.getEmail())) {
            throw new IllegalArgumentException("Email no válido");
        }
        User currentUser = userRepository.findById(getCurrentUserId())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        currentUser.setNombre(registerDTO.getNombre());
        currentUser.setEmail(registerDTO.getEmail());
        userRepository.save(currentUser);
        return null;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
>>>>>>> Stashed changes

}
