package com.example.Imagify.Repository;
import com.example.Imagify.Entity.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Repositorio para manejar operaciones CRUD y consultas personalizadas
 * relacionadas con la entidad Image.
 * @author Jocar
 * @version 1.0
 * @since 1.1
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
    /**
     * Obtiene los detalles básicos de una imagen específica.
     * @param id El identificador único de la imagen.
     * @return Los detalles básicos de la imagen.
     */
    @Query("SELECT u FROM Image u WHERE u.id = :id")
    public ImageBasicDetails findImageBasicDetailsById(Long id);
    
    
    @Query("SELECT u FROM Image u WHERE u.visibility='public' ORDER BY u.id DESC")
    public List<Image> getAllPublicImages();
    
    @Query("SELECT u FROM Image u WHERE u.visibility='private' AND u.user.id = :id_user ORDER BY u.id DESC")
    public List<Image> getPrivateImagesByUser(@Param("id_user") Long id_user);
    
    
    @Query("SELECT u FROM Image u WHERE u.visibility='public' AND u.user.id = :id_user ORDER BY u.id DESC")
    public List<Image> getPublicImagesByUser(@Param("id_user") Long id_user);
    
    /**
     * Obtiene una lista de los detalles básicos de todas las imágenes.
     *
     * @return Una lista de los detalles básicos de las imágenes.
     */
    @Query("SELECT u FROM Image u")
    public List<ImageBasicDetails> getImagesDetailsById();
    
}
