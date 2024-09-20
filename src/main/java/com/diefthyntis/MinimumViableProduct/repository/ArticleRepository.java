package com.diefthyntis.MinimumViableProduct.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diefthyntis.MinimumViableProduct.model.Article;
import com.diefthyntis.MinimumViableProduct.model.Speaker;



@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

	List<Article> findBySpeaker(Speaker speaker);
	
	 
}
