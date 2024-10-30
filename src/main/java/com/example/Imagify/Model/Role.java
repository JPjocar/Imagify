package com.example.Imagify.Model;

import jakarta.persistence.*;

/**
 * Entidad que representa un rol en el sistema y la tabla en la base de datos.
 * @author Dev
 */

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    /**
     * Obtiene el identificador único del rol.
     *
     * @return el ID del rol.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del rol.
     *
     * @param id el ID del rol.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del rol.
     *
     * @return el nombre del rol.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del rol.
     *
     * @param nombre el nombre del rol.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Role(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Role() {
        super();
    }

    public Role(String nombre) {
        this.nombre = nombre;
    }
}
