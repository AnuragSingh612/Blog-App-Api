package com.example.Blog.App.Api.ServiceImpl;

import com.example.Blog.App.Api.payload.CategoryDto;

import java.util.List;

public interface CAtegoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Integer id);
    List<CategoryDto> getAllCategory();
}
