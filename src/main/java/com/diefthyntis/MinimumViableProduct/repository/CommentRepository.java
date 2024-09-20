package com.diefthyntis.MinimumViableProduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diefthyntis.MinimumViableProduct.model.Article;
import com.diefthyntis.MinimumViableProduct.model.Comment;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	

	List<Comment> findByArticle(Article article);
	
}
