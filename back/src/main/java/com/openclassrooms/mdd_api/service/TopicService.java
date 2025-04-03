package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.TopicDto;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.model.Topic;

import java.util.List;

public interface TopicService {

    List<TopicDto> findAll() throws ResourceNotFoundException;

    List<Topic> getSubscribedTopicsByUser(Long userId);

    void subscribeTopic(Long topicId);

    void unsubscribeTopic(Long topicId);

}
