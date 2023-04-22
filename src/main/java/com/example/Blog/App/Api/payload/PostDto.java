package com.example.Blog.App.Api.payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class PostDto {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private Integer categoryId;
}
