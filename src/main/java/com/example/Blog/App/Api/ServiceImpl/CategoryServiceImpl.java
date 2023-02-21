package com.example.Blog.App.Api.ServiceImpl;

import com.example.Blog.App.Api.Entity.Category;
import com.example.Blog.App.Api.payload.CategoryDto;
import com.example.Blog.App.Api.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CAtegoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto,Category.class);
        Category savedCAtegory=categoryRepository.save(category);

        return modelMapper.map(savedCAtegory,CategoryDto.class);
    }
}
