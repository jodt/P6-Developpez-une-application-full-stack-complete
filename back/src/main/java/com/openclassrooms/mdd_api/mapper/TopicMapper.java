package com.openclassrooms.mdd_api.mapper;

import com.openclassrooms.mdd_api.dto.TopicDto;
import com.openclassrooms.mdd_api.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicDto asTopicDto (Topic topic);

    List<TopicDto> asTopicDtos (List<Topic> topic);
}
