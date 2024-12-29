package dev.fordragon.forohub.api.domain.course;

public record CourseResponseListDTO(
        long id,
        String name,
        Category category
) {
    public CourseResponseListDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
