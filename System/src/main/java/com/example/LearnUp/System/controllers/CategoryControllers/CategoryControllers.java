package com.example.LearnUp.System.controllers.CategoryControllers;


import com.example.LearnUp.System.model.CategoryModel.Category;
import com.example.LearnUp.System.model.CategoryModel.CategoryResponse;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.service.CategoryService.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CategoryControllers {
    @Autowired
    private final ObjectMapper mapper;

    @Autowired
    private CategoryService categoryService;



    @PostMapping("/admin/addcategory")
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @GetMapping("/users/category")
    public ResponseEntity<List<CategoryResponse>> getCategories() {

        return categoryService.getCategory();
    }

    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<String>deleteCategory(@PathVariable Long id) throws IOException {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/users/category/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }



}
