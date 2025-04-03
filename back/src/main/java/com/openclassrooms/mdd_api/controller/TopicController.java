package com.openclassrooms.mdd_api.controller;

import com.openclassrooms.mdd_api.dto.TopicDto;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.service.TopicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
