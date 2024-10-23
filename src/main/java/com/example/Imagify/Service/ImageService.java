/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import com.example.Imagify.Entity.Image;
import com.example.Imagify.Repository.ImageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dev
 */
@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    public void save(Image image){
        this.imageRepository.save(image);
    }
    
    public List<Image> getAll(){
        return this.imageRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    
    public Optional<Image> get(Long id){
        return this.imageRepository.findById(id);
    }
    
    public void delete(Long id){
        this.imageRepository.deleteById(id);
    }
}
