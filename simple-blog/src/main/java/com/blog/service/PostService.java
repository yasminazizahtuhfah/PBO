package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.PostJpaRepository;
import com.blog.repository.PostRepository;
import com.blog.vo.Post;

import io.micrometer.common.util.StringUtils;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostJpaRepository postJpaRepository;
    
    public Post getPost(Long id) {
        Post post = postJpaRepository.findOneById(id);

        return post;
    }

    public List<Post> getPosts() {
        List<Post> posts = postJpaRepository.findAllByOrderByUpdtDateDesc();

        return posts;
    }

    public List<Post> getPostsOrderByUpdtAsc() {
        List<Post> posts = postJpaRepository.findAllByOrderByUpdtDateAsc();

        return posts;
    }

    public List<Post> getPostsOrderByRegDesc() {
        List<Post> posts = postRepository.findPostOrderByRegDateDesc();

        return posts;
    }

    public List<Post> searchPostByTitle(String query) {
        List<Post> posts = postJpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);

        return posts;
    }

    public List<Post> searchPostByContent(String query) {
        List<Post> posts = postJpaRepository.findByContentContainingOrderByUpdtDateDesc(query);

        return posts;
    }

    public boolean savePost(Post post) {
        Post result = postJpaRepository.save(post);
        boolean isSuccess = true;

        if(result == null) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean deletePost(Long id) {
        Post result = postJpaRepository.findOneById(id);

        if(result == null)
            return false;
        
        postJpaRepository.deleteById(id);
        return true;
    }

    public boolean updatePost(Post post) {
        Post result = postJpaRepository.findOneById(post.getId());

        if(result == null)
            return false;

        if(!StringUtils.isEmpty(post.getTitle())) {
            result.setTitle(post.getTitle());
        }

        if(!StringUtils.isEmpty(post.getContent())) {
            result.setContent(post.getContent());
        }

        postJpaRepository.save(result);

        return true;
    }

}