/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import com.example.Imagify.Entity.Image;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones CRUD básicas para la entidad Image.
 * @author Jocar
 * @version 1.0
 * @since 1.1
 */
public interface CRUDImageService {
    /**
     * Guarda una imagen en la base de datos.
     *
     * @param image La imagen a guardar.
     */
    public void basicSave(Image image);
    /**
     * Obtiene todas las imágenes almacenadas.
     *
     * @return Una lista de todas las imágenes.
     */
    public List<Image> getAll();
    /**
     * Obtiene una imagen específica por su identificador.
     *
     * @param id El identificador de la imagen.
     * @return Un Optional que contiene la imagen si se encuentra, o vacío si no.
     */
    public Optional<Image> get(Long id);
    /**
     * Elimina una imagen específica por su identificador.
     *
     * @param id El identificador de la imagen a eliminar.
     */
    public void delete(Long id);
}
