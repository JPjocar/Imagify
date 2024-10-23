/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Controller;

import com.example.Imagify.Entity.Image;
import com.example.Imagify.Service.ImageService;
import com.example.Imagify.Service.StorageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Dev
 */
@Controller
@RequestMapping(path = "images")
public class ImageController {
    
    @Autowired
    private ImageService imageService;
    @Autowired
    private StorageService storageService;
    
    @GetMapping
    public String index(Model model){
        List<Image> images = this.imageService.getAll();
        model.addAttribute("images", images);
        return "View/Images/index";
    }
    
    @GetMapping("/create")
    public String create(Model model){
        Image image = new Image();
        model.addAttribute("image", image);
        return "View/Images/create";
    }
    
    @GetMapping("/resources/{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = storageService.loadResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"").body(file);
    }
    
    @PostMapping
    public String store(@ModelAttribute("image") Image image, @RequestParam("imageFile") MultipartFile imageFile){
        if(!imageFile.isEmpty()){
            String __dirname = Paths.get("images").toFile().getAbsolutePath();
            try{
                byte[] imageByte = imageFile.getBytes();
                Path completePath = Paths.get(__dirname+"//"+imageFile.getOriginalFilename());
                Files.write(completePath, imageByte);
                
                image.setFilename(imageFile.getOriginalFilename());
                image.setUrl(imageFile.getOriginalFilename());
                
                this.imageService.save(image);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return "redirect:/images";
    }
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        this.imageService.delete(id);
        return "redirect:/images";
    }
}
