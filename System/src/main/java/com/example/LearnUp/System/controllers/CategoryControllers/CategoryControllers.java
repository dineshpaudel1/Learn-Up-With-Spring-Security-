package com.example.LearnUp.System.controllers.CategoryControllers;


import com.example.LearnUp.System.model.CategoryModel.Category;
import com.example.LearnUp.System.service.CategoryService.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

}
