package com.example.Imagify.DTO;


/**
 * Data Transfer Object (DTO) para el registro de usuarios.
 * 
 */
public class UserRegisterDTO {

    private Long id;
    private String nombre;
    private String email;
    private String password;

     /**
     * Obtiene el identificador único del usuario.
     * 
     * @return el ID del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     * 
     * @param id el ID del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre el nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return el correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    
    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email el correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return la contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password la contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Constructor con todos los campos.
     * 
     * @param id el ID del usuario.
     * @param nombre el nombre del usuario.
     * @param email el correo electrónico del usuario.
     * @param password la contraseña del usuario.
     */
    public UserRegisterDTO(Long id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor sin el ID.
     * 
     * @param nombre el nombre del usuario.
     * @param email el correo electrónico del usuario.
     * @param password la contraseña del usuario.
     */
    public UserRegisterDTO(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor con solo el email.
     * 
     * @param email el correo electrónico del usuario.
     */
    public UserRegisterDTO(String email) {
        super();
        this.email = email;
    }

    public UserRegisterDTO() {
    }
}
