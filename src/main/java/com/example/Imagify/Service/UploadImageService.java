/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import com.example.Imagify.Entity.Image;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * Interfaz que define la operación para guardar una imagen y su archivo asociado.
 *
 * @author Jocar
 */
public interface UploadImageService {
    /**
     * Guarda una imagen en la base de datos y en el sistema de archivos.
     *
     * @param image El objeto Image que contiene los valores de la imagen.
     * @param imageFile El archivo de imagen cargado por el usuario.
     * @throws Exception Si ocurre un error durante el procesamiento de la imagen.
     */
    public abstract void save(Image image, MultipartFile imageFile) throws Exception;
}
