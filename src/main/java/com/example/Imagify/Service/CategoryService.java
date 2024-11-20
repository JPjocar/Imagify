/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Service;

import com.example.Imagify.Entity.Category;
import com.example.Imagify.Repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAll(){
        return this.categoryRepository.findAll();
    }
    
    public Category get(Long id){
        return this.categoryRepository.findById(id).get();
    }
}
