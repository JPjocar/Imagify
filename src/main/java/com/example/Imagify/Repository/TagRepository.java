/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Imagify.Repository;

import com.example.Imagify.Entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dev
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    @Query("SELECT u FROM Tag u ORDER BY u.id DESC")
    public List<Tag> getAllDesc();
}
