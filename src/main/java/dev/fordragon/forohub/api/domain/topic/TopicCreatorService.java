package dev.fordragon.forohub.api.domain.topic;

import dev.fordragon.forohub.api.domain.topic.customRules.TopicRules;
import dev.fordragon.forohub.api.domain.course.CourseRepository;
import dev.fordragon.forohub.api.domain.appuser.AppUserRepository;
import dev.fordragon.forohub.api.infra.validation.CustomValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicCreatorService {

    @Autowired
    private AppUserRepository authorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private List<TopicRules> validadores;

    public TopicResponseDTO createTopic(TopicCreationDTO datos){

        var authorOptional = authorRepository.findById(datos.idAuthor());
        if (!authorOptional.isPresent()) {
            throw new CustomValidationException("No existe un author con el id");
        }

        var courseOptional = courseRepository.findById(datos.idCourse());
        if (!courseOptional.isPresent()) {
            throw new CustomValidationException("No existe un curso con el id");
        }

        //Business logic validations
        validadores.forEach(v -> v.validar(datos));

        var author = authorOptional.get();
        var course = courseOptional.get();

        var newTopic = new Topic(datos.title(), datos.message(), author, course);
        topicRepository.save(newTopic);

        return new TopicResponseDTO(newTopic);
    }
}
