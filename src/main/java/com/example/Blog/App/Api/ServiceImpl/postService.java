package com.example.Blog.App.Api.ServiceImpl;
import com.example.Blog.App.Api.Entity.Post;
import com.example.Blog.App.Api.exception.ResourceNotfoundException;
import com.example.Blog.App.Api.payload.PostDto;
import com.example.Blog.App.Api.repository.postRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class postService implements postServiceInterface{
    @Autowired
    private postRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        //Convert postDto to entity
        Post post=maptoEntity(postDto);
        //Saving post to Repository
        Post newPost= postRepository.save(post);
        //Convert entity to dto
        PostDto postDto1= maptoDTO(newPost);
        return postDto1;
    }
    @Override
    public List<PostDto> getAllposts(int pageNo,int pageSize, String sortBy) {
        Pageable pageable =PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Post> postList =postRepository.findAll(pageable);
        //Get Content from Page Object
        List<Post> listofPost= postList.getContent();
        return postList.stream().map(post-> maptoDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
       Post post= postRepository.findById(id).orElseThrow(()-> new ResourceNotfoundException("Post",id,"id"));
       return maptoDTO(post);
    }

    @Override
    public PostDto updatepost(long id,PostDto postDto) {
//        PostDto postDto1=getPostById(id);
//        Post post=maptoEntity(postDto1);
//        post.setContent(postDto1.getContent());
//        post.setTitle(postDto1.getTitle());
//        post.setDescription(postDto1.getDescription());
////       Post updatePost= postRepository.save(post);
//        return maptoDTO(post);
        Post post= postRepository.findById(id).orElseThrow(() -> new ResourceNotfoundException("Post",id,"id"));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatePost = postRepository.save(post);
        return maptoDTO(updatePost);
    }

    private Post maptoEntity(PostDto postDto)
    {
        Post post= new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
    private PostDto maptoDTO(Post newPost)
    {
        PostDto postDto1= new PostDto();
        postDto1.setId(newPost.getId());
        postDto1.setContent(newPost.getContent());
        postDto1.setDescription(newPost.getDescription());
        postDto1.setTitle(newPost.getTitle());
        return postDto1;
    }
    public void deletePost(long id){
      postRepository.deleteById(id);
    }
}
