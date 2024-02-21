package com.blog.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blog.vo.Comment;


@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Serializable> {
	List<Comment> findByPostIdOrderByRegDateDesc(Long postId);
	
	Comment findById(Long id);
	
	List<Comment> findByPostIdAndCommentContaining(Long postId, String query);
	
	List<Comment> findByPostIdAndCommentContainingOrderByRegDateDesc(Long postId, String query);
	

}