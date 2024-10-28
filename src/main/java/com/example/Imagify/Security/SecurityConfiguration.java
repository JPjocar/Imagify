package com.example.Imagify.Security;

import com.google.common.base.Preconditions;

import com.example.Imagify.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad para la aplicación.
 * 
 * Esta clase define las configuraciones de seguridad, incluyendo la codificación
 * de contraseñas, proveedores de autenticación y reglas de seguridad HTTP.
 */
@Configuration

public class SecurityConfiguration {

    private UserService userService;

    /**
     * Constructor para inyectar el servicio de usuarios.
     *
     * @param userService El servicio de usuarios que se va a inyectar.
     */
    public SecurityConfiguration(UserService userService) {
        this.userService = Preconditions.checkNotNull(userService, "UserService no puede ser nulo");
    }

    /**
     * Define un bean para el codificador de contraseñas BCrypt.
     *
     * @return Una instancia de `BCryptPasswordEncoder`.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Define un proveedor de autenticación basado en DAO.
     *
     * @return Una instancia de `DaoAuthenticationProvider`.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    /**
     * Configura la cadena de filtros de seguridad HTTP.
     *
     * @param http El objeto `HttpSecurity` utilizado para configurar las reglas de seguridad.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register**", "/js/**", "/css/**", "/imgs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/images", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}
