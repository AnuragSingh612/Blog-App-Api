package com.example.Blog.App.Api.payload;

import com.example.Blog.App.Api.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {
    Integer id;
    String name;
    String Description;
}
