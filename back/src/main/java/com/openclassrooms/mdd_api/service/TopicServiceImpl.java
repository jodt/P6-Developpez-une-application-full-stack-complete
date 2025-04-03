package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.TopicDto;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.model.Topic;
import com.openclassrooms.mdd_api.model.User;
import com.openclassrooms.mdd_api.repository.TopicRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{

    private final TopicRepository topicRepository;
    private final UserService userService;

    public TopicServiceImpl(TopicRepository topicRepository, UserService userService) {
        this.topicRepository = topicRepository;
        this.userService = userService;
    }

    @Override
    public List<TopicDto> findAll() throws ResourceNotFoundException {

        User userLogged = userService.findUserByMail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(ResourceNotFoundException::new);

        List<Topic> topics = this.topicRepository.findAll();


        return mapTopicsToDtosWithSubscriptionStatus(topics, userLogged);
    }

    private List<TopicDto> mapTopicsToDtosWithSubscriptionStatus(List<Topic> topics, User userLogged) {
        List<TopicDto> topicDtos = topics.stream().map(topic -> {
            boolean hasAlreadySubscribed = userLogged.getTopics().contains(topic);

            return TopicDto.builder()
                    .id(topic.getId())
                    .description(topic.getDescription())
                    .title(topic.getTitle())
                    .subscribed(hasAlreadySubscribed)
                    .build();
        }).toList();
        return topicDtos;
    }

    @Override
    public List<Topic> getSubscribedTopicsByUser(Long userId) {
        return null;
    }

    @Override
    public void subscribeTopic(Long topicId) {

    }

    @Override
    public void unsubscribeTopic(Long topicId) {

    }
}
