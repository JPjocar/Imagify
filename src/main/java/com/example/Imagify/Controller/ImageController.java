/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Controller;

<<<<<<< Updated upstream
=======
import com.example.Imagify.DTO.UserRegisterDTO;
import com.example.Imagify.Entity.Category;
>>>>>>> Stashed changes
import com.example.Imagify.Entity.Image;
import com.example.Imagify.Entity.Tag;
import com.example.Imagify.Repository.ImageRepository;
import com.example.Imagify.Repository.UserRepository;
import com.example.Imagify.Service.ImageService;
import com.example.Imagify.Service.StorageService;
import com.example.Imagify.Service.TagService;
import com.example.Imagify.Service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
 * Esta clase administra la solicitudes HTPP de las imagenes
 * @author Jocar
 * @version 1.0
 * @since 1.1
 */

@Controller
@RequestMapping(path = "images")
public class ImageController {
    
    Logger logger = LoggerFactory.getLogger(ImageController.class);
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private UserRepository userRepository;
    
<<<<<<< Updated upstream
=======
    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;
>>>>>>> Stashed changes
    
    @Autowired
    private StorageService storageService;
    
    @Autowired
    private TagService tagService;
     
    /**
     * Este metodo sirve para mostrar la lista de imagenes
     * @param model El modelo utilizado para pasar datos a la vista.
    *  @return El nombre de la vista de la página principal de imágenes.
     */
//    @GetMapping
//    public String index(Model model){
//        List<Image> images = this.imageService.getAll();
//        model.addAttribute("images", images);
//        logger.info("Accediendo a la pagina principal");
//        return "View/Images/index";
//    }
    
    @GetMapping("/buscar")
    public String buscador(@RequestParam("busq") String busq, Model model){
        System.out.println(busq);
        List<Image> images = this.imageService.search(busq);
        model.addAttribute("images", images);
        model.addAttribute("buscar", true);
        model.addAttribute("busqueda", busq);
        return "View/Images/index";
    }
    
    @GetMapping("/tags/create")
    public String tags(Model model){
        Tag tag = new Tag();
        model.addAttribute("tag", tag);
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        List<Tag> tags = this.tagService.getAllDes();
        model.addAttribute("tags", tags);
        return "View/Images/tags";
    }
    
    @PostMapping("/tags")
    public String createTag(@ModelAttribute("tag") Tag tag){
        this.tagService.save(tag);
        return "redirect:/images/tags/create";
    }
    
    @GetMapping
    public String index(Model model){
        List<Image> images = this.imageService.getAllPublicImages();
        model.addAttribute("images", images);
        model.addAttribute("buscar", false);
        model.addAttribute("busqueda", "");
        logger.info("Accediendo a la pagina principal");
        return "View/Images/index";
    }
    
    /**
     * Este metodo sirve para mostrar el formulario para subir una nueva imagen
     * @param model El modelo utilizado para pasar datos a la vista. En este caso, se usa para agregar 
     * un objeto `Image` vacío que será llenado en el formulario de creación.
     * @return El nombre de la vista para mostrar el formulario de creación de imágenes.
     */
    @GetMapping("/create")
    public String create(Model model){
        String username = getCurrentUsername();
        model.addAttribute("username", username);
<<<<<<< Updated upstream

=======
        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        List<Tag> tags = this.tagService.getAll();
        model.addAttribute("tags", tags);
>>>>>>> Stashed changes
        Image image = new Image();
        model.addAttribute("image", image);
        return "View/Images/create";
    }
    
    public String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return ((User) authentication.getPrincipal()).getUsername();
        }
        return null;
    }
    
    @GetMapping("/private")
    public String privateImages(Model model){
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        List<Image> images = this.imageService.getPrivateImagesByUser();
        model.addAttribute("images", images);
        return "View/Images/private";
    }
    
    @GetMapping("/public")
    public String publicImages(Model model){
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        List<Image> images = this.imageService.getPublicImagesByUser();
        model.addAttribute("images", images);
        return "View/Images/private";
    }
    
    @GetMapping("/{id}/hide")
    public String hide(@PathVariable("id") Long id){
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            username = ((User) authentication.getPrincipal()).getUsername();
            Image image = this.imageService.get(id).get();
            image.setVisibility("private");
            this.imageService.basicSave(image);
        }
        return "redirect:/images/private";
    }
    
    @GetMapping("/{id}/show")
    public String show(@PathVariable("id") Long id){
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            username = ((User) authentication.getPrincipal()).getUsername();
            Image image = this.imageService.get(id).get();
            image.setVisibility("public");
            this.imageService.basicSave(image);
        }
        return "redirect:/images/public";
    }
    
    /**
     * Este metodo sirve para generar un recurso de una imagen determinada por el filename
    * @param filename El nombre del archivo de imagen a servir.
    * @return Un `ResponseEntity` que contiene el recurso de la imagen solicitada y las cabeceras HTTP 
    *         adecuadas para la descarga.
    */
    @GetMapping("/resources/{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = storageService.loadResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
                "attachment; filename=\""+file.getFilename()+"\"").body(file);
    }
    
    /**
     * Este metodo sirve para almacenar una imagen recien creada en la base de datos
     * @param image El objeto image contiene los valores de las propiedades de la imagen.
     * @param imageFile El archivo de imagen cargado por el usuario.
     * @return Una redirección a la página de listado de imágenes.
     * @throws Exception Si ocurre un error durante el proceso de almacenamiento de la imagen.
     */
    @PostMapping
    public String store(@ModelAttribute("image") Image image, @RequestParam("imageFile") MultipartFile imageFile, @RequestParam List<Long> tags) throws Exception{
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            username = ((User) authentication.getPrincipal()).getUsername();
        }
        com.example.Imagify.Model.User user = this.userRepository.findByEmail(username);
        image.setUser(user);
        List<Tag> tagsDB = this.tagService.findAllById(tags);
        image.setTags(tagsDB);
        
        long start = System.currentTimeMillis();
        this.imageService.save(image, imageFile);
        long totalTime = System.currentTimeMillis() - start;
        logger.info("El tiempo para subir la imagen fue de {} milisegundos", totalTime);
        return "redirect:/images";
    }
    
    /**
     * Este metodo sirve para eliminar una imagen de la base de datos
     * @param id El identificador de la imagen a eliminar.
     * @return Una redirección a la página de listado de imágenes.
     */
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        String filename = this.imageService.get(id).get().getFilename();
        this.imageService.delete(id);
        logger.info("Se ha eliminado una imagen con filename={}", filename);
        return "redirect:/images";
    }
<<<<<<< Updated upstream
=======
    
    @GetMapping("/categories")
    public String categories(Model model){
        List<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        return "View/Images/categories";
    }
    
    @GetMapping("/categories/{id}")
    public String imagesByCategory(@PathVariable("id") Long id, Model model){
        List<Image> imagesCategory = this.categoryService.get(id).getImages();
        model.addAttribute("images", imagesCategory);
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        return "View/Images/private"; 
    }
    
    
    @GetMapping("/perfil/edit")
    
    public String perfilEdit(Model model) {
        String username1 = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            username1 = ((User) authentication.getPrincipal()).getUsername();
        }
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        com.example.Imagify.Model.User user = this.userRepository.findByEmail(username1);
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(user.getId(), user.getNombre(), user.getEmail(), "");
        model.addAttribute("user", userRegisterDTO);
        return "View/Images/edit"; 
    }
    
    @PostMapping("/perfil/update")
    public String perfilUpdate(@ModelAttribute("user") UserRegisterDTO userRegisterDTO){
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            username = ((User) authentication.getPrincipal()).getUsername();
        }
        com.example.Imagify.Model.User user = this.userRepository.findByEmail(username);

        userRegisterDTO.setId(user.getId());
        System.out.println(userRegisterDTO.getNombre());
        this.userRepository.deleteById(user.getId());
        this.userService.save(userRegisterDTO);
        return "redirect:/images/perfil/edit";
    }
    
>>>>>>> Stashed changes
}
