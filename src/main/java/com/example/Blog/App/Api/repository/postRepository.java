package com.example.Blog.App.Api.repository;

import com.example.Blog.App.Api.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@Repository
public interface postRepository extends JpaRepository<Post,Integer> {
    List<Post> findPostByCategoryId(Integer categoryId) ;
}
