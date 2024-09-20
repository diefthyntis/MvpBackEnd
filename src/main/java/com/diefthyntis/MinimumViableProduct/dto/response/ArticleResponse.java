package com.diefthyntis.MinimumViableProduct.dto.response;

import lombok.Data;

@Data
public class ArticleResponse {
	private String id;
	private String sentence;
	private String title;
    private String speakerid;
    private String topicid;
    private String creationdate;
	private String modificationdate;
    
}
