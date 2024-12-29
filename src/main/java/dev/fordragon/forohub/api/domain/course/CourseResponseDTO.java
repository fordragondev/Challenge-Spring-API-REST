package dev.fordragon.forohub.api.domain.course;

public record CourseResponseDTO(
        long id,
        String name,
        Category category
) {
}
