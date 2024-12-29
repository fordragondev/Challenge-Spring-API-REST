package dev.fordragon.forohub.api.domain.topic;

import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
        @NotNull
        long id,
        String title,
        String message,
        Status status
) {
    public TopicUpdateDTO(Topic newTopic) {

        this(newTopic.getId(), newTopic.getTitle(), newTopic.getMessage(), newTopic.getStatus());
    }
}
