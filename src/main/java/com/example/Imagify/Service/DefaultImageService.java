/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import com.example.Imagify.Entity.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio para manejar imágenes y guardarlas en el sistema de archivos y la base de datos.
 * @author Dev
 */
public class DefaultImageService extends ImageService{
    
    /**
     * Guarda una imagen en la base de datos y en el sistema de archivos.
     *
     * @param image El objeto Image que contiene los valores de la imagen.
     * @param imageFile El archivo de imagen cargado por el usuario.
     * @throws Exception Si ocurre un error durante el procesamiento de la imagen.
     */
    @Override
    public void save(Image image, MultipartFile imageFile) throws Exception {
            if(!imageFile.isEmpty()){
            String __dirname = Paths.get("images").toFile().getAbsolutePath();
            try{
                byte[] imageByte = imageFile.getBytes();
                Path completePath = Paths.get(__dirname+"//"+imageFile.getOriginalFilename());
                Files.write(completePath, imageByte);
                
                image.setFilename(imageFile.getOriginalFilename());
                image.setUrl(imageFile.getOriginalFilename());

                super.imageRepository.save(image);
                
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    
}
