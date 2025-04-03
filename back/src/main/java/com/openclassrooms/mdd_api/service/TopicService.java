package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.TopicDto;
import com.openclassrooms.mdd_api.dto.UserTopicsSubscribedDto;
import com.openclassrooms.mdd_api.exception.BadRequestException;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;

import java.util.List;

public interface TopicService {

    List<TopicDto> findAll() throws ResourceNotFoundException;

    List<UserTopicsSubscribedDto> getSubscribedTopicsByUser() throws ResourceNotFoundException;

    void subscribeTopic(Long topicId) throws ResourceNotFoundException, BadRequestException;

    void unsubscribeTopic(Long topicId) throws ResourceNotFoundException, BadRequestException;

}
