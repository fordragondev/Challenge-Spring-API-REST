package dev.fordragon.forohub.api.domain.topic;

import java.time.LocalDateTime;

public record TopicResponseListDTO(
        long id,
        String title,
        String message,
        LocalDateTime created,
        Status status,
        long courseId
) {
    public TopicResponseListDTO(Topic newTopic) {

        this(newTopic.getId(), newTopic.getTitle(), newTopic.getMessage(), newTopic.getCreated(), newTopic.getStatus(),
                newTopic.getCourse().getId());
    }
}
