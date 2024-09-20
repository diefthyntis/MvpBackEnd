package com.diefthyntis.MinimumViableProduct.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.MinimumViableProduct.dto.request.CommentRequest;
import com.diefthyntis.MinimumViableProduct.dto.response.CommentResponse;
import com.diefthyntis.MinimumViableProduct.model.Article;
import com.diefthyntis.MinimumViableProduct.model.Comment;
import com.diefthyntis.MinimumViableProduct.model.Speaker;

import com.diefthyntis.MinimumViableProduct.service.ArticleService;
import com.diefthyntis.MinimumViableProduct.service.SpeakerService;
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
public class CommentMapping {
	private final SpeakerService speakerService;
	private final ArticleService articleService;
	public Comment mapCommentRequestToComment(CommentRequest commentRequest)
	{
		final Comment comment = new Comment();
		comment.setSentence(commentRequest.getSentence());
		Speaker speaker = speakerService.getSpeakerById(NumberUtils.convertToInteger(commentRequest.getSpeakerid()));
		comment.setSpeaker(speaker);
		Article article=articleService.getArticleById(NumberUtils.convertToInteger(commentRequest.getArticleid()));
		comment.setArticle(article);
		
		return comment;
	}
	
	/*
	 * La réponse est un datagramme envoyé par le BackEnd au FrontEnd
	 */
	public CommentResponse mapCommentToCommentResponse(Comment comment) {
		final CommentResponse commentResponse = new CommentResponse();
		commentResponse.setArticleId(NumberUtils.convertToString(comment.getArticle().getId()));
		commentResponse.setSpeakerId(NumberUtils.convertToString(comment.getSpeaker().getId()));
		commentResponse.setId(NumberUtils.convertToString(comment.getId()));
		commentResponse.setCreationdate(DateUtils.convertLocalDateToString(comment.getCreationdate()));
		commentResponse.setModificationdate(DateUtils.convertLocalDateToString(comment.getModificationdate()));
		commentResponse.setSentence(comment.getSentence());
		return commentResponse;
	}

}
