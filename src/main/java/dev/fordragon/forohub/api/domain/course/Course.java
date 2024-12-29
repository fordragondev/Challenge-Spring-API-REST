package dev.fordragon.forohub.api.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "course")
@Entity(name = "Course")

@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Course (CourseCreationDTO  courseCreationDTO) {
        this.name = courseCreationDTO.name();
        this.category = courseCreationDTO.category();
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Course() {
    }

    public Course(String name, Long id, Category category) {
        this.name = name;
        this.id = id;
        this.category = category;
    }
}
