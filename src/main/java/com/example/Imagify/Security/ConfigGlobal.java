/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Security;

import com.example.Imagify.Service.CompressedImageService;
import com.example.Imagify.Service.ImageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuración global para la aplicación.
 * Esta clase define beans que se gestionan en el contexto de Spring.
 * @author Dev
 * @version 1.0
 * @since 1.1
 */
@Configuration
public class ConfigGlobal {
    
    /**
     * Define un bean para el servicio de imágenes comprimidas.
     * @return Una instancia de CompressedImageService.
     */
    @Bean
    public ImageService compressedImageService(){
        return new CompressedImageService();
    }
}
