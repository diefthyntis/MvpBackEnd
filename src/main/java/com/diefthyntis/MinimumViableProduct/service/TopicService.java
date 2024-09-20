package com.diefthyntis.MinimumViableProduct.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diefthyntis.MinimumViableProduct.exception.TopicNotFoundException;
import com.diefthyntis.MinimumViableProduct.model.Topic;
import com.diefthyntis.MinimumViableProduct.repository.TopicRepository;

import lombok.RequiredArgsConstructor;


/*
 * RequiredArgsConstructor à ajouter pour avoir une injection de dépendance par constructeur
 */

@Service
@RequiredArgsConstructor
public class TopicService {
	private final TopicRepository topicRepository;
	
	public Topic save(Topic topic) {
		return topicRepository.save(topic);
	}
	
	public Topic getTopicById(Integer topicId) {
		return topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic Not Found"));
	}
	

	 
	public List<Topic> getTopics() {
		// TODO Auto-generated method stub
		final List<Topic> topicList=topicRepository.findAll();
		return topicList;
		}
	 
	

	
	
	public void update(Topic topic) {
		topicRepository.save(topic);
	}

	
	
	
}
