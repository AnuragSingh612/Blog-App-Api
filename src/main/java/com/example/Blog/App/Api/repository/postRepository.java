package com.example.Blog.App.Api.repository;

import com.example.Blog.App.Api.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface postRepository extends JpaRepository<Post,Long> {
    List<Post> findPostByCategoryId(Long categoryId) ;
}
