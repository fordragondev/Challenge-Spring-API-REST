package dev.fordragon.forohub.api.domain.topic;

import dev.fordragon.forohub.api.domain.course.Course;
import dev.fordragon.forohub.api.domain.appuser.AppUser;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Table(name = "topic")
@Entity(name = "Topic")
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private AppUser author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;

    public Topic (TopicCreationDTO topicCreationDTO) {
        this.title = topicCreationDTO.title();
    }

    public Topic (String title, String message, AppUser author, Course course) {
        this.title = title;
        this.message = message;
        this.author = author;
        this.course = course;

        this.status = Status.ACTIVE;
        this.created = LocalDateTime.now(); // Assign the current date and time
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Status getStatus() {
        return status;
    }

    public AppUser getAuthor() {
        return author;
    }

    public Course getCourse() {
        return course;
    }

    public Topic() {
    }

    public Topic(Course course, AppUser author, Status status, LocalDateTime created, String message, String title, Long id) {
        this.course = course;
        this.author = author;
        this.status = status;
        this.created = created;
        this.message = message;
        this.title = title;
        this.id = id;
    }

    public void desactivateTopic() {
        this.status = Status.DEACTIVE;
    }

    public void updateTopic(TopicUpdateDTO topicUpdateDTO) {
        if (topicUpdateDTO.title() != null) {
            this.title = topicUpdateDTO.title();
        }
        if (topicUpdateDTO.message() != null) {
            this.message = topicUpdateDTO.message();
        }
        if (topicUpdateDTO.status() != null) {
            this.status = topicUpdateDTO.status();
        }
    }
}
