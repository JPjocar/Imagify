/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Repository;

/**
 * Proyección para obtener detalles básicos de una imagen. 
 * Esta interfaz proporciona solo un subconjunto de los campos de la entidad
 * completa de la imagen, enfocándose en los detalles básicos como el ID,
 * el nombre del archivo, el título y la descripción.
 * @author Jocar
 * @version 1.0
 * @since 1.1
 */
public interface ImageBasicDetails {
    public Long getId();
    public String getFilename();
    public String getTitle();
    public String getDescription();
}
