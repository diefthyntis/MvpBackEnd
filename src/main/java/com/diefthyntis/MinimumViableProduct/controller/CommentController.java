package com.diefthyntis.MinimumViableProduct.controller;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diefthyntis.MinimumViableProduct.dto.request.ArticleRequest;
import com.diefthyntis.MinimumViableProduct.dto.request.CommentRequest;
import com.diefthyntis.MinimumViableProduct.dto.response.CommentResponse;
import com.diefthyntis.MinimumViableProduct.dto.response.ServerResponse;
import com.diefthyntis.MinimumViableProduct.mapping.CommentMapping;
import com.diefthyntis.MinimumViableProduct.model.Article;
import com.diefthyntis.MinimumViableProduct.model.Comment;
import com.diefthyntis.MinimumViableProduct.service.ArticleService;
import com.diefthyntis.MinimumViableProduct.service.CommentService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * @RequiredArgsConstructor permet d'avoir un constructeur avec la dépendance "final" injectée 
 * "dépendance" est en fait une déclaration de classe de type service "service, repository ou mapping
 * @RequiredArgsConstructor permet de ne pas avoir à créer le constructeur chargé d'instancier commentService et commentMapping
 */

/*
 * @Slf4j permet d'injecter un loggueur
 */

/*
 * Il y autant de thread que d'utilisateurs connecté
 * L'information de l'utilisateur connecté est disponible dans l'interface Principal
 */



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
	private final CommentService commentService;
	private final CommentMapping commentMapping;
	private final ArticleService articleService;
	
	
	@PostMapping("/comments")
    public ResponseEntity<ServerResponse> create(final @RequestBody CommentRequest commentRequest) throws IOException, java.io.IOException {
		log.info("debut de la creation de comment");
			
		/*
		 * l'objet CommentRequest est posté par le FrontEnd et reçu par le controller
		 */
		
		final Comment comment = commentMapping.mapCommentRequestToComment(commentRequest);
		commentService.save(comment);
			
		
		return ResponseEntity.ok(new ServerResponse("Comment send with success"));
      
    }
	
	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/comments/{articleid}")
	public List<CommentResponse> GetCommentsByArticle(@PathVariable Integer articleid) {
		final Article article = articleService.getArticleById(articleid);
		final List<Comment> comments = commentService.GetCommentsByArticle(article);
		final List<CommentResponse> commentResponses = new ArrayList();
		comments.stream().forEach(comment -> {
			final CommentResponse commentResponse = commentMapping.mapCommentToCommentResponse(comment);
			commentResponses.add(commentResponse);
			
		});
		return commentResponses;
	}
	
	@ResponseStatus(HttpStatus.OK)
	   @GetMapping("/comments")
		public List<CommentResponse> GetComments() {
			final List<Comment> comments = commentService.GetComments();
			final List<CommentResponse> commentResponses = new ArrayList();
			comments.stream().forEach(comment -> {
				final CommentResponse commentResponse = commentMapping.mapCommentToCommentResponse(comment);
				commentResponses.add(commentResponse);
				
			});
			return commentResponses;
		}
	
	
}
