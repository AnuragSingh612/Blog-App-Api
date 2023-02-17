package com.example.Blog.App.Api.ServiceImpl;

import com.example.Blog.App.Api.payload.PostDto;

import java.util.List;

public interface postServiceInterface {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllposts(int pageNo,int pageSize,String sortBy);
    PostDto getPostById(long id);
    PostDto updatepost(long id,PostDto postDto);
     void deletePost(long id);
}
