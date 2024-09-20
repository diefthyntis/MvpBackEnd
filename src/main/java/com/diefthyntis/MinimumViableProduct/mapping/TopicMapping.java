package com.diefthyntis.MinimumViableProduct.mapping;

import org.springframework.stereotype.Component;

import com.diefthyntis.MinimumViableProduct.dto.request.TopicRequest;
import com.diefthyntis.MinimumViableProduct.dto.response.TopicResponse;
import com.diefthyntis.MinimumViableProduct.model.Topic;


import com.diefthyntis.MinimumViableProduct.util.NumberUtils;




@Component
public class TopicMapping {
	
	public Topic mapTopicRequestToTopic(TopicRequest topicRequest)  {
		final Topic topic = new Topic();
		topic.setDescription(topicRequest.getDescription());
		topic.setTitle(topicRequest.getTitle());
		topic.setId(NumberUtils.convertToInteger(topicRequest.getId()));
		return topic;
	}

	public TopicResponse mapTopicToTopicResponse(Topic topic) {
		// TODO Auto-generated method stub
		final TopicResponse topicResponse = new TopicResponse();
		topicResponse.setId(NumberUtils.convertToString(topic.getId()));
		topicResponse.setDescription(topic.getDescription());
		topicResponse.setTitle(topic.getTitle());
		return topicResponse;
	}
}
