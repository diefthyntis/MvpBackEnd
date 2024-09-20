package com.diefthyntis.MinimumViableProduct.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.diefthyntis.MinimumViableProduct.dto.response.TopicResponse;

import com.diefthyntis.MinimumViableProduct.mapping.TopicMapping;

import com.diefthyntis.MinimumViableProduct.model.Topic;


import com.diefthyntis.MinimumViableProduct.service.TopicService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class TopicController {
	private final TopicService topicService;
	private final TopicMapping topicMapping;
	
	
	@GetMapping("/topics")
    public List<TopicResponse> getTopics() {
		List<Topic> topics =  topicService.getTopics();
		List<TopicResponse> topicResponses = new ArrayList();
		
		
        topics.stream().forEach(topic -> {
        	final TopicResponse topicResponse =topicMapping.mapTopicToTopicResponse(topic);
        	topicResponses.add(topicResponse);
        });
        
        
        return topicResponses;
    }
	
	
	
	
	
}