package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.ServiceImpl.CategoryServiceImpl;
import com.example.Blog.App.Api.payload.CategoryDto;
import com.example.Blog.App.Api.payload.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
    }
    /* @RequestBody will accept json from request and convert it into java object*/

}
