package dev.fordragon.forohub.api.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicCreationDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long idAuthor,
        @NotNull
        Long idCourse
) {
}
