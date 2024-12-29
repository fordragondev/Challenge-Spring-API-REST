package dev.fordragon.forohub.api.domain.topic;

import java.time.LocalDateTime;

public record TopicResponseDTO(
        long id,
        String title,
        String message,
        LocalDateTime created,
        Status status,
        long authorId,
        long courseId
) {
    public TopicResponseDTO(Topic newTopic) {

        this(newTopic.getId(), newTopic.getTitle(), newTopic.getMessage(), newTopic.getCreated(), newTopic.getStatus(),
                newTopic.getAuthor().getId(), newTopic.getCourse().getId());
    }
}
