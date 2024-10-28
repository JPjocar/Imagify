package com.example.Imagify.Service;
import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Model.Role;
import com.example.Imagify.Model.User;
import com.example.Imagify.Repository.UserRepository;
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
        User user = new User(registerDTO.getNombre(),
                registerDTO.getEmail(), passwordEncoder.encode(registerDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")));
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
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mappingAuthoritiesRoles(user.getRoles()));
    }

    /**
     * Mapea los roles del usuario a las autoridades de seguridad de Spring.
     *
     * @param roles Los roles del usuario.
     * @return Una colección de autoridades concedidas.
     */
    private Collection<? extends GrantedAuthority> mappingAuthoritiesRoles(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

}
