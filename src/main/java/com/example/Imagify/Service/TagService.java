/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import com.example.Imagify.Entity.Tag;
import com.example.Imagify.Repository.TagRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dev
 */
@Service
public class TagService {
    
    @Autowired
    private TagRepository tagRepository;
    
    
    public void save(Tag tag){
        tagRepository.save(tag);
    }
    
    public List<Tag> getAll(){
        return this.tagRepository.findAll();
    }
    
    public List<Tag> findAllById(List<Long> ids){
        return this.tagRepository.findAllById(ids);
    }
    
    
    public List<Tag> getAllDes(){
        return this.tagRepository.getAllDesc();
    }
}
