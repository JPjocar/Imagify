package com.example.Imagify.Model;

import jakarta.persistence.*;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

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
