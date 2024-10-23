/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dev
 */
@Service
public class StorageService {
    private final Path rootLocation = Paths.get("images");
    public Resource loadResource(String filename){
        try{
            Path file = rootLocation.resolve(filename);
            System.out.println(file.toString()); //comentar
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo");
            }
        }catch(MalformedURLException e){
            throw new RuntimeException("No se encuentra el archivo");
        }
    }
}
