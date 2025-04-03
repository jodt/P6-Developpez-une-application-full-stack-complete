package com.openclassrooms.mdd_api.controller;

import com.openclassrooms.mdd_api.dto.TopicWithSubscriptionStatusDto;
import com.openclassrooms.mdd_api.dto.UserTopicsSubscribedDto;
import com.openclassrooms.mdd_api.exception.BadRequestException;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    List<TopicWithSubscriptionStatusDto> getAllTopicsWithSubscriptionStatus() throws ResourceNotFoundException {
        return this.topicService.findAllTopicsWithSubscriptionStatus();
    }

    @PostMapping("/{topicId}")
    ResponseEntity<?> subscribe(@PathVariable Long topicId) throws ResourceNotFoundException, BadRequestException {
        log.info("POST api/topic/{} called -> start the process to subscribe topic", topicId);
        this.topicService.subscribeTopic(topicId);
        log.info("Process terminated successfully");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{topicId}")
    ResponseEntity<?> unsubscribe(@PathVariable Long topicId) throws ResourceNotFoundException, BadRequestException {
        log.info("DELETE api/topic/{} called -> start the process to unsubscribe topic", topicId);
        this.topicService.unsubscribeTopic(topicId);
        log.info("Process terminated successfully");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscribed")
    List<UserTopicsSubscribedDto> findSubscribedTopics() throws ResourceNotFoundException {
        log.info("POST api/topic/subscribed called -> start the process to retrieve topics subscribed by a user");
        List<UserTopicsSubscribedDto> userTopicsSubscribedDtos =  this.topicService.getSubscribedTopicsByUser();
        log.info("Process terminated successfully, {} topics retrieved", userTopicsSubscribedDtos.size());
        return userTopicsSubscribedDtos;
    }

}
