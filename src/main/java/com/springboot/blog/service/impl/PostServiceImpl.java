package com.springboot.blog.service.impl;

import com.springboot.blog.Convertors.PostPaginationResponseConverter;
import com.springboot.blog.Convertors.PostRequestConvertor;
import com.springboot.blog.Convertors.PostResponseConvertor;
import com.springboot.blog.RequestDtos.PostRequestDto;
import com.springboot.blog.ResponseDto.PostPaginationResponseDto;
import com.springboot.blog.ResponseDto.PostResponseDto;
import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        Post post = PostRequestConvertor.convertRequestDtoToPost(postRequestDto);

        Category category = categoryRepository.findById(postRequestDto.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postRequestDto.getCategoryId()));
        post.setCategory(category);
       Post savedPost =  postRepository.save(post);

        PostResponseDto postResponseDto = PostResponseConvertor.convertPostToPostResponseDto(savedPost);
        postResponseDto.setCategoryId(postRequestDto.getCategoryId());

        return postResponseDto;
    }

    @Override
    public PostPaginationResponseDto getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> postList = postRepository.findAll(pageable);
        List<Post> listOfPosts = postList.getContent();
        List<PostResponseDto> postResponseDtoList = mapToPostResponseDto(listOfPosts);

        PostPaginationResponseDto postPaginationResponseDto = PostPaginationResponseConverter.convertPageOfPostToPostPaginationResponseDto(postList);
        postPaginationResponseDto.setContent(postResponseDtoList);
        return postPaginationResponseDto;
    }

    @Override
    public PostResponseDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        PostResponseDto postResponseDto = PostResponseConvertor.convertPostToPostResponseDto(post);
        postResponseDto.setCategoryId(post.getCategory().getId());
        return postResponseDto;
    }

    @Override
    public PostResponseDto updatePost(PostRequestDto postRequestDto, long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        Category category = categoryRepository.findById(postRequestDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postRequestDto.getCategoryId()));

        post.setTitle(postRequestDto.getTitle());
        post.setDescription(postRequestDto.getDescription());
        post.setContent(postRequestDto.getContent());

        post.setCategory(category);

        Post updatedPost = postRepository.save(post);

        PostResponseDto postResponseDto = PostResponseConvertor.convertPostToPostResponseDto(updatedPost);
        postResponseDto.setCategoryId(postRequestDto.getCategoryId());

        return postResponseDto;

    }

    @Override
    public String deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
        return "Post deleted successfully";
    }

    @Override
    public List<PostResponseDto> getPostByCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id",id));

        List<Post> postList = postRepository.findByCategoryId(id);

        List<PostResponseDto> postResponseDtoList = mapToPostResponseDto(postList);
        return postResponseDtoList;
    }


    private List<PostResponseDto> mapToPostResponseDto(List<Post> postList){
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for(Post post : postList){
            PostResponseDto postResponseDto = PostResponseConvertor.convertPostToPostResponseDto(post);
            postResponseDto.setCategoryId(post.getCategory().getId());
            postResponseDtoList.add(postResponseDto);
        }
        return postResponseDtoList;
    }


}
