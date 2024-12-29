package dev.fordragon.forohub.api.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseCreationDTO(
        @NotBlank
        String name,
        @NotNull
        Category category
) {
}
