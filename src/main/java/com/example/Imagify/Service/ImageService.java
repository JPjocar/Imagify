/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import com.example.Imagify.Entity.Image;
import com.example.Imagify.Repository.ImageBasicDetails;
import com.example.Imagify.Repository.ImageRepository;
import com.example.Imagify.Repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio abstracto para manejar operaciones relacionadas con la entidad Image.
 * 
 * Proporciona métodos CRUD básicos y métodos adicionales para manejar la carga
 * y compresión de imágenes.
 * @author Dev
 */
@Service
public abstract class ImageService implements CRUDImageService, UploadImageService{
    
    
    @Autowired
    protected ImageRepository imageRepository;
    
    @Autowired
    protected UserRepository userRepository;
    /**
     * Guarda una imagen en la base de datos y en el sistema de archivos.
     *
     * @param image El objeto Image que contiene los valores de la imagen.
     * @param imageFile El archivo de imagen cargado por el usuario.
     * @throws Exception Si ocurre un error durante el procesamiento de la imagen.
     */
    @Override
    public abstract void save(Image image, MultipartFile imageFile) throws Exception; 
    public List<ImageBasicDetails> getImagesDetailsById(Long id){
        return this.imageRepository.getImagesDetailsById();
    }
    
    /**
     * Guarda una imagen en la base de datos.
     *
     * @param image La imagen a guardar.
     */
    @Override
    public void basicSave(Image image){
        this.imageRepository.save(image);
    } 
    
    /**
     * Obtiene todas las imágenes almacenadas, ordenadas por ID en orden descendente.
     *
     * @return Una lista de todas las imágenes.
     */
    @Override
    public List<Image> getAll(){
        return this.imageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }  
    
    public List<Image> getAllPublicImages(){
        return this.imageRepository.getAllPublicImages();
    }
    
    /**
     * Obtiene una imagen específica por su identificador.
     *
     * @param id El identificador de la imagen.
     * @return Un Optional que contiene la imagen si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Image> get(Long id){
        return this.imageRepository.findById(id);
    }  
    
    public List<Image> getPrivateImagesByUser(){
        Long id_user = this.getIdUserSession();        
        return this.imageRepository.getPrivateImagesByUser(id_user);
    }
    
    public List<Image> getPublicImagesByUser(){
        Long id_user = this.getIdUserSession();  
        return this.imageRepository.getPublicImagesByUser(id_user);
    }
    
    
    public Long getIdUserSession(){
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            username = ((User) authentication.getPrincipal()).getUsername();
        }
        System.out.println(username);
        return this.userRepository.findByEmail(username).getId();
    }
    /**
     * Elimina una imagen específica por su identificador.
     *
     * @param id El identificador de la imagen a eliminar.
     */
    @Override
    public void delete(Long id){
        this.imageRepository.deleteById(id);
    }
    
    
    public List<Image> search(String busqueda){
        return this.imageRepository.search(busqueda);
    }
}
