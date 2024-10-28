/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import com.example.Imagify.Entity.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio para manejar imágenes comprimidas y guardarlas en formato webp.
 * @author Dev
 * 
 */
public class CompressedImageService extends ImageService{
    /**
     * Guarda una imagen en la base de datos y en el sistema de archivos como formato webp.
     * @param image El objeto Image que contiene los valores de la imagen.
     * @param imageFile El archivo de imagen cargado por el usuario.
     * @throws Exception Si ocurre un error durante el procesamiento de la imagen.
     */
    @Override
    public void save(Image image, MultipartFile imageFile) throws Exception{
        String uploadDirectory = System.getProperty("user.dir") + "/uploads/";
        if(imageFile.isEmpty()){
            throw new Exception("La imagen esta vacias");
        }
        // Directorio existe sino lo crea
        File dir = new File(uploadDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // Leer la imagen de la imagen subida
        BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());
        // Convertir la imagen a formato webp usando configuraciones por default
        Path webpFilePath = Paths.get(uploadDirectory + 
                getFileNameWithoutExtension(imageFile.getOriginalFilename()) + ".webp");
        File webpFile = webpFilePath.toFile();
        ImageIO.write(originalImage, "webp", webpFile);
        image.setFilename(getFileNameWithoutExtension(imageFile.getOriginalFilename())+".webp");
        image.setUrl(imageFile.getOriginalFilename());
        super.imageRepository.save(image);    
    }
    /**
     * Obtiene el nombre del archivo sin su extensión.
     *
     * @param fileName El nombre del archivo original.
     * @return El nombre del archivo sin la extensión.
     */
    private String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }
    
}
