package com.example.Imagify.Repository;
import com.example.Imagify.Entity.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    
    /**
     * Obtiene una lista de los detalles básicos de todas las imágenes.
     *
     * @return Una lista de los detalles básicos de las imágenes.
     */
    @Query("SELECT u FROM Image u")
    public List<ImageBasicDetails> getImagesDetailsById();
    
}
