package com.openclassrooms.mdd_api.controller;

import com.openclassrooms.mdd_api.dto.TopicDto;
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
    List<TopicDto> findAll() throws ResourceNotFoundException {
        return this.topicService.findAll();
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

}
