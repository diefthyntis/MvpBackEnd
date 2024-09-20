package com.diefthyntis.MinimumViableProduct.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.MinimumViableProduct.dto.request.ArticleRequest;
import com.diefthyntis.MinimumViableProduct.dto.response.ArticleResponse;
import com.diefthyntis.MinimumViableProduct.model.Article;
import com.diefthyntis.MinimumViableProduct.model.Speaker;
import com.diefthyntis.MinimumViableProduct.model.Topic;
import com.diefthyntis.MinimumViableProduct.service.SpeakerService;
import com.diefthyntis.MinimumViableProduct.service.TopicService;
import com.diefthyntis.MinimumViableProduct.util.DateUtils;
import com.diefthyntis.MinimumViableProduct.util.NumberUtils;

import lombok.RequiredArgsConstructor;

/*
* TopicMapping propose des méthodes qui permettent de mapper un objet TopicRequest vers Topic
* L'objet TopicRequest est posté par le FrontEnd et reçu par le controller
*/

/*
* Grace à l'annotation @RequiredArgsConstructor, il n'y pas besoin d'instancier speakerService
*/

/*
 * l'objet ArticleRequest est posté par le FrontEnd et reçu par le controller
 */
@Component
@RequiredArgsConstructor
public class ArticleMapping {
	private final SpeakerService speakerService;
	private final TopicService topicService;
	public Article mapArticleRequestToArticle(ArticleRequest articleRequest)
	{
		final Article article = new Article();
		article.setSentence(articleRequest.getSentence());
		article.setTitle(articleRequest.getTitle());
		final Speaker speaker = speakerService.getSpeakerById(NumberUtils.convertToInteger(articleRequest.getSpeakerid()));
		article.setSpeaker(speaker);
		final Topic topic=topicService.getTopicById(NumberUtils.convertToInteger(articleRequest.getTopicid()));
		article.setTopic(topic);
		
		return article;
	}
	/*
	 * La réponse est le datagramme à envoyer au frontend
	 */
	public ArticleResponse mapArticleToArticleResponse(Article article) {
		final ArticleResponse articleResponse = new ArticleResponse();
		articleResponse.setId(NumberUtils.convertToString(article.getId()));
		articleResponse.setSpeakerid(NumberUtils.convertToString(article.getSpeaker().getId()));
		articleResponse.setTopicid(NumberUtils.convertToString(article.getTopic().getId()));
		articleResponse.setTitle(article.getTitle());
		articleResponse.setSentence(article.getSentence());
		articleResponse.setCreationdate(DateUtils.convertLocalDateToString(article.getCreationdate()));
		articleResponse.setModificationdate(DateUtils.convertLocalDateToString(article.getModificationdate()));
		return articleResponse;
		
		
	}

}
