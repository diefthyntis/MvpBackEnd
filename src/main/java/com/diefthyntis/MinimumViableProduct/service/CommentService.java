package com.diefthyntis.MinimumViableProduct.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diefthyntis.MinimumViableProduct.exception.CommentNotFoundException;
import com.diefthyntis.MinimumViableProduct.model.Article;
import com.diefthyntis.MinimumViableProduct.model.Comment;
import com.diefthyntis.MinimumViableProduct.model.Speaker;
import com.diefthyntis.MinimumViableProduct.repository.ArticleRepository;
import com.diefthyntis.MinimumViableProduct.repository.CommentRepository;

import lombok.RequiredArgsConstructor;


/*
 * RequiredArgsConstructor à ajouter pour avoir une injection de dépendance par constructeur
 */

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final ArticleRepository articleRepository;
	
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public Comment getCommentById(Integer commentId) {
		return commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment Not Found"));
	}
	

	 
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
		}
	 
	
	
	
	
	public void update(Comment comment) {
		commentRepository.save(comment);
	}



	public List<Comment> GetCommentsByArticleid(Integer articlerid) {
		// TODO Auto-generated method stub
		final Optional<Article> article = articleRepository.findById(articlerid);
		return commentRepository.findByArticle(article.get());
		
	}
	
	public List<Comment> GetCommentsByArticle(Article article) {
		// TODO Auto-generated method stub
		
		return commentRepository.findByArticle(article);
		
	}

	public List<Comment> GetComments() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}
	
	
	
	
	
}

