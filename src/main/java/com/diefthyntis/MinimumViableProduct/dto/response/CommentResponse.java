package com.diefthyntis.MinimumViableProduct.dto.response;

import lombok.Data;

/*
 * Tous les champs sont des strings car ils vont être droppés dans une réponse qui va transiter sur 
 * la couche ethernet, qui ne gère que les string
 */
@Data
public class CommentResponse {
	private String id;
	private String sentence;
    private String speakerId;
    private String articleId;
    private String creationdate;
	private String modificationdate;
    
}
