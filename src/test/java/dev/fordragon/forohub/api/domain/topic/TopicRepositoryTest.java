package dev.fordragon.forohub.api.domain.topic;

import dev.fordragon.forohub.api.domain.appuser.AppUser;
import dev.fordragon.forohub.api.domain.appuser.AppUserCreationDTO;
import dev.fordragon.forohub.api.domain.course.Category;
import dev.fordragon.forohub.api.domain.course.Course;
import dev.fordragon.forohub.api.domain.course.CourseCreationDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("return true if there is a topic already created with he same Title and Message")
    void existsByTitleAndMessageQueryTrue() {
        //given o arrange
        var course = createCourse("coursename", Category.CATEGORYA);
        var user =  createUser("login", "email@gmail.com", "1234567");

        createTopic("title", "message", user, course);
        //when o act
        var noCoincidence = topicRepository.existsByTitleAndMessage("title", "message");
        //then o assert
        assertThat(noCoincidence).isTrue();
    }

    @Test
    @DisplayName("return false if there isn't a topic already created with he same Title and Message")
    void existsByTitleAndMessageQueryFalse() {
        //given o arrange
        var course = createCourse("coursename", Category.CATEGORYA);
        var user =  createUser("login", "email@gmail.com", "1234567");

        createTopic("title", "message", user, course);
        //when o act
        var noCoincidence = topicRepository.existsByTitleAndMessage("other title", "other message");
        //then o assert
        assertThat(noCoincidence).isFalse();
    }

    private Course createCourse(String nombre, Category category) {
        var course = new Course(courseData(nombre, category));
        em.persist(course);
        return course;
    }

    private CourseCreationDTO courseData(String nombre, Category category) {
        return new CourseCreationDTO(
                nombre,
                category
        );
    }

    private AppUser createUser(String login, String email, String password) {
        var appUser = new AppUser(userData(login, email, password));
        em.persist(appUser);
        return appUser;
    }

    private AppUserCreationDTO userData(String login, String email, String password) {
        return new AppUserCreationDTO(
                login,
                email,
                password
        );
    }
    private void createTopic(String title, String message, AppUser author, Course course) {
        em.persist(new Topic(title, message, author, course));
    }

}