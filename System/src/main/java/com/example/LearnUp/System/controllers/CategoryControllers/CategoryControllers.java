package com.example.LearnUp.System.controllers.CategoryControllers;


import com.example.LearnUp.System.entity.UserEntity.UserEntity;
import com.example.LearnUp.System.model.CategoryModel.Category;
import com.example.LearnUp.System.model.CategoryModel.CategoryResponse;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.service.CategoryService.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryControllers {

    private final ObjectMapper mapper;

    private final CategoryService categoryService;


    @PostMapping("/admin/addcategory")
    public ResponseEntity<Object> addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @GetMapping("/users/category")
    public ResponseEntity<List<CategoryResponse>> getCategories() {

        return categoryService.getCategory();
    }

    @PatchMapping("/admin/category/updatePhoto")
    public ResponseEntity<Object> updatePhoto(@RequestParam("data") String data,
                                              @RequestParam("photo") MultipartFile file)
            throws JsonProcessingException {
        Category category = mapper.readValue(data, Category.class);
        return categoryService.updatePhoto(file, category);
    }

    @DeleteMapping("/admin/category")
    public ResponseEntity<?> deleteCategory(@RequestBody Category category) throws IOException {
        return categoryService.deleteCategory(category.getId());
    }

    @GetMapping("/users/category/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }



}
