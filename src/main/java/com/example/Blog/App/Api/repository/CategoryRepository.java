package com.example.Blog.App.Api.repository;

import com.example.Blog.App.Api.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
