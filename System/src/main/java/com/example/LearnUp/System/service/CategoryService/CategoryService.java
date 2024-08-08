package com.example.LearnUp.System.service.CategoryService;

import com.example.LearnUp.System.model.CategoryModel.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CategoryService {
    ResponseEntity<Object> addCategory(Category category);
}
