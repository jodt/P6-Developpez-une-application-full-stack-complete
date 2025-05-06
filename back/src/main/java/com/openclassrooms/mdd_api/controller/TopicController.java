package com.openclassrooms.mdd_api.controller;

import com.openclassrooms.mdd_api.dto.TopicDto;
import com.openclassrooms.mdd_api.dto.TopicWithSubscriptionStatusDto;
import com.openclassrooms.mdd_api.dto.UserTopicsSubscribedDto;
import com.openclassrooms.mdd_api.exception.BadRequestException;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Get topics", description = "Returns all topics with the precision of whether the user has subscribed to them or not")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TopicWithSubscriptionStatusDto.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value="resource not found")))
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    List<TopicWithSubscriptionStatusDto> getAllTopicsWithSubscriptionStatus() throws ResourceNotFoundException {
        return this.topicService.findAllTopicsWithSubscriptionStatus();
    }

    @Operation(summary = "Get topics", description = "Return all topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = TopicDto.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)})
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/list")
    List<TopicDto> getAllTopics() {
        return this.topicService.findAllTopic();
    }


    @Operation(summary = "Subscribe to a topic", description = "Records a user's subscription to a topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value="resource not found"))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value="bad request")))})
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/{topicId}")
    ResponseEntity<?> subscribe(@PathVariable Long topicId) throws ResourceNotFoundException, BadRequestException {
        log.info("POST api/topic/{} called -> start the process to subscribe topic", topicId);
        this.topicService.subscribeTopic(topicId);
        log.info("Process terminated successfully");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Unsubscribe to a topic", description = "Records a user's unsubscription from a topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value="resource not found"))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value="bad request")))})
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{topicId}")
    ResponseEntity<?> unsubscribe(@PathVariable Long topicId) throws ResourceNotFoundException, BadRequestException {
        log.info("DELETE api/topic/{} called -> start the process to unsubscribe topic", topicId);
        this.topicService.unsubscribeTopic(topicId);
        log.info("Process terminated successfully");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get topics", description = "Returns all topics a user is subscribed to")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = UserTopicsSubscribedDto.class)))}),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "text/plain",
                    examples = @ExampleObject(value="resource not found")))})
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/subscribed")
    List<UserTopicsSubscribedDto> findSubscribedTopics() throws ResourceNotFoundException {
        log.info("POST api/topic/subscribed called -> start the process to retrieve topics subscribed by a user");
        List<UserTopicsSubscribedDto> userTopicsSubscribedDtos =  this.topicService.getSubscribedTopicsByUser();
        log.info("Process terminated successfully, {} topics retrieved", userTopicsSubscribedDtos.size());
        return userTopicsSubscribedDtos;
    }

}
