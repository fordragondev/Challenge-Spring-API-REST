package dev.fordragon.forohub.api.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByAuthorId(Long idAuthor);

    boolean existsByTitleAndMessage  (String title, String message);

}
