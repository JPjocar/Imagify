package com.example.Imagify;

import com.example.Imagify.Repository.CategoryRepository;
import com.example.Imagify.Service.CategoryService;
import com.example.Imagify.Entity.Category;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class CategoryServiceTest {

    public CategoryServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<Category> mockCategories = Arrays.asList(
                new Category(1L, "Category 1", "Description 1", LocalDate.now(), new ArrayList<>()),
                new Category(2L, "Category 2", "Description 2", LocalDate.now(), new ArrayList<>())
        );
        when(categoryRepository.findAll()).thenReturn(mockCategories);

        List<Category> result = categoryService.getAll();

        System.out.println("=== TEST: Obtener todas las categorías ===");
        System.out.println("Esperado: " + mockCategories.size() + " categorías.");
        System.out.println("Obtenido: " + result.size() + " categorías.");

        for (int i = 0; i < result.size(); i++) {
            System.out.println("Categoría " + (i + 1) + " - Esperado: " + mockCategories.get(i).getName()
                    + ", Obtenido: " + result.get(i).getName());
        }

        assertEquals(mockCategories.size(), result.size(), "El tamaño de la lista no coincide");
        for (int i = 0; i < result.size(); i++) {
            assertEquals(mockCategories.get(i).getName(), result.get(i).getName(), "El nombre de la categoría no coincide");
        }
    }

    @Test
    void testGetById() {
        Category mockCategory = new Category(1L, "Category 1");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));

        Category result = categoryService.get(1L);

        System.out.println("=== TEST: Obtener categoría por ID ===");
        System.out.println("Esperado: ID = " + mockCategory.getId() + ", Nombre: " + mockCategory.getName());
        System.out.println("Obtenido: ID = " + result.getId() + ", Nombre = " + result.getName());

        assertNotNull(result, "La categoría obtenida es nula");
        assertEquals(mockCategory.getId(), result.getId(), "El ID no coincide");
        assertEquals(mockCategory.getName(), result.getName(), "El nombre no coincide");
    }
}
