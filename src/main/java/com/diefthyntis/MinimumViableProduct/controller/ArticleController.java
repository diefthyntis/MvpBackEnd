package com.diefthyntis.MinimumViableProduct.controller;




import java.io.IOException;
import java.security.Principal;
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
import com.diefthyntis.MinimumViableProduct.dto.response.ArticleResponse;
import com.diefthyntis.MinimumViableProduct.dto.response.ServerResponse;
import com.diefthyntis.MinimumViableProduct.mapping.ArticleMapping;
import com.diefthyntis.MinimumViableProduct.model.Article;
import com.diefthyntis.MinimumViableProduct.model.Speaker;
import com.diefthyntis.MinimumViableProduct.service.ArticleService;
import com.diefthyntis.MinimumViableProduct.service.SpeakerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * @RequiredArgsConstructor permet d'avoir un constructeur avec la dépendance "final" injectée 
 * "dépendance" est en fait une déclaration de classe de type service "service, repository ou mapping
 * @RequiredArgsConstructor permet de ne pas avoir à créer le constructeur chargé d'instancier articleService et articleMapping
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
public class ArticleController {
	private final ArticleService articleService;
	private final ArticleMapping articleMapping;
	private final SpeakerService speakerService;
	
	@PostMapping("/articles")
    public ResponseEntity<ServerResponse> create(final @RequestBody ArticleRequest articleRequest) throws IOException, java.io.IOException {
		log.info("debut de la creation de article");
			
		/*
		 * l'objet ArticleRequest est posté par le FrontEnd et reçu par le controller
		 */
		
		final Article article = articleMapping.mapArticleRequestToArticle(articleRequest);
		articleService.save(article);
			
		
		return ResponseEntity.ok(new ServerResponse("Article send with success"));
      
    }
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/articles/{id}")
	public List<ArticleResponse> getArticlesBySpeaker(final Principal principal) {
		final String emailAddress = principal.getName();
		//final Speaker speaker = speakerService.findByEmailaddress(emailAddress);
		final Speaker speaker = speakerService.findBylogin(emailAddress);
		final List<Article> articles = articleService.getArticlesBySpeaker(speaker);
		final List<ArticleResponse> articleResponses = new ArrayList();
		articles.stream().forEach(article -> {
			final ArticleResponse articleResponse = articleMapping.mapArticleToArticleResponse(article);
			articleResponses.add(articleResponse);
		});
		return articleResponses;
		
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/articles")
	public List<ArticleResponse> getArticles() {
		final List<Article> articles = articleService.getArticles();
		final List<ArticleResponse> articleResponses = new ArrayList();
		articles.stream().forEach(article -> {
			final ArticleResponse articleResponse = articleMapping.mapArticleToArticleResponse(article);
			articleResponses.add(articleResponse);
		});
		return articleResponses;
		
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/article/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable String id) {
		int tmpId = Integer.parseInt(id);
        Article article = articleService.getArticleById(tmpId);
        if (article != null) {
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
}
