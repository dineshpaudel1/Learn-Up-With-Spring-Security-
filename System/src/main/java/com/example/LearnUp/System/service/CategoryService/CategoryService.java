package com.example.LearnUp.System.service.CategoryService;

import com.example.LearnUp.System.model.CategoryModel.Category;
import com.example.LearnUp.System.model.CategoryModel.CategoryResponse;
import com.example.LearnUp.System.model.CourseModel.Course;
import com.example.LearnUp.System.model.CourseModel.CourseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CategoryService {
    ResponseEntity<Object> addCategory(Category category,MultipartFile file);
    ResponseEntity<List<CategoryResponse>>getCategory();
    ResponseEntity<String>deleteCategory(Long categoryId);
    ResponseEntity<String>updateCategory(Long categoryId, Category category, MultipartFile file);
}
