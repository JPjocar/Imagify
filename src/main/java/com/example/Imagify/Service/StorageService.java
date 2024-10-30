/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

/**
 *
 * Servicio para manejar el almacenamiento y la recuperación de archivos.
 *
 * @author Dev
 */
@Service
public class StorageService {
    private final Logger logger = LoggerFactory.getLogger(StorageService.class);
    
    private final Path rootLocation = Paths.get("uploads");
    
    /**
     * Carga un recurso de archivo por su nombre.
     *
     * @param filename El nombre del archivo a cargar.
     * @return El recurso de archivo cargado.
     * @throws RuntimeException Si el archivo no puede ser leído o si hay un error en la URL.
    */
    
    public Resource loadResource(String filename){
        try{
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo");
            }
        }catch(MalformedURLException e){
            logger.warn("La imagen no es un formato valido o no se encuentra en el servidor: filename={} : ",filename, e);
            throw new RuntimeException("Error in StorageService");
        }
    }
}
