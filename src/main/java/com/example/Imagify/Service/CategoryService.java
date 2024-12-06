
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
