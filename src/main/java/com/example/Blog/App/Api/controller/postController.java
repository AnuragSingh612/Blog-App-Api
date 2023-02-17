package com.example.Blog.App.Api.controller;

import com.example.Blog.App.Api.ServiceImpl.postService;
import com.example.Blog.App.Api.payload.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class postController {
    @Autowired
    postService postService;
    @PostMapping
    public ResponseEntity<PostDto> createpost(@RequestBody PostDto postdto){
        return new ResponseEntity<>(postService.createPost(postdto), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDto> getallpost(
            @RequestParam(value= "pageNo", defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy
    )
    {
        return postService.getAllposts(pageNo,pageSize,sortBy);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name="id") long id,@RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.updatepost(id,postDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post Deleted Succesfully",HttpStatus.OK);
    }

}
