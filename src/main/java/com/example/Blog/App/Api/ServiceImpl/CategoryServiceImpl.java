package com.example.Blog.App.Api.ServiceImpl;

import com.example.Blog.App.Api.Entity.Category;
import com.example.Blog.App.Api.exception.ResourceNotfoundException;
import com.example.Blog.App.Api.payload.CategoryDto;
import com.example.Blog.App.Api.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public CategoryDto getCategory(Integer id) {
        Category category=categoryRepository.findById(id).orElseThrow(()-> new  ResourceNotfoundException("Category",id,"ID"));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category1= categoryRepository.findAll();
       return  category1.stream().map(category-> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }
}
