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



    @PostMapping("/users/addcategory")
    public ResponseEntity<Object> addCategory(@RequestParam("category")String data, @RequestParam("file") MultipartFile file)throws IOException {
         Category category = mapper.readValue(data, Category.class);
        return categoryService.addCategory(category,file);
    }
    @GetMapping("/users/category")
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return categoryService.getCategory();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String>deleteCategory(@PathVariable Long id) throws IOException {
        return categoryService.deleteCategory(id);
    }
    @PostMapping("/users/{categoryId}")
    public ResponseEntity<String>updateCategory(@PathVariable Long categoryId, @RequestParam("category")String data,@RequestParam(value="file",required=false)MultipartFile file) throws IOException{
        Category category = mapper.readValue(data, Category.class);
        return categoryService.updateCategory(categoryId,category,file);
    }

}
