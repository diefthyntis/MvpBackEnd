package com.diefthyntis.MinimumViableProduct.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.diefthyntis.MinimumViableProduct.exception.ArticleNotFoundException;
import com.diefthyntis.MinimumViableProduct.model.Article;
import com.diefthyntis.MinimumViableProduct.model.Speaker;
import com.diefthyntis.MinimumViableProduct.model.Topic;
import com.diefthyntis.MinimumViableProduct.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

/*
 * RequiredArgsConstructor à ajouter pour avoir une injection de dépendance par constructeur
 */

@Service
@RequiredArgsConstructor
public class ArticleService {

private final ArticleRepository articleRepository;
	
	
	public void save(Article article) {
		articleRepository.save(article);
	}
	
	public Article getArticleById(Integer id) {
		return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("Article Not Found"));
	}
	

	public List<Article> getArticlesBySpeaker(Speaker speaker) {
		final List<Article> articleList= articleRepository.findBySpeaker(speaker);
		return articleList;
				}
	
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}

}
