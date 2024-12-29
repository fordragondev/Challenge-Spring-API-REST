package dev.fordragon.forohub.api.controller;

import dev.fordragon.forohub.api.domain.topic.*;
import dev.fordragon.forohub.api.domain.topic.customRules.TopicRules;
import dev.fordragon.forohub.api.infra.validation.CustomValidationException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicCreatorService newTopic;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private List<TopicRules> validadores;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> createTopic (@RequestBody @Valid TopicCreationDTO datos,
                                                         UriComponentsBuilder uriComponentsBuilder) {

        var detailTopic = newTopic.createTopic(datos);

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(detailTopic.id()).toUri();
        return ResponseEntity.created(url).body(detailTopic);
    }

    @GetMapping
    public ResponseEntity<Page<TopicResponseListDTO>> getListTopic(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicRepository.findAll(paginacion).map(TopicResponseListDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> getTopic(@PathVariable Long id) {

        if(!topicRepository.existsById(id)){
            throw new CustomValidationException("No existe un Topic con el id");
        }

        Topic topic = topicRepository.getReferenceById(id);
        var topicData = new TopicResponseDTO(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreated(), topic.getStatus(),
                topic.getAuthor().getId(), topic.getCourse().getId());
        return ResponseEntity.ok(topicData);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> updateTopic(@RequestBody @Valid TopicUpdateDTO updateTopicData) {

        if(!topicRepository.existsById(updateTopicData.id())){
            throw new CustomValidationException("No existe un Topic con el id");
        }

        validadores.forEach(v -> v.validar(updateTopicData));

        Topic topic = topicRepository.getReferenceById(updateTopicData.id());
        topic.updateTopic(updateTopicData);
        return ResponseEntity.ok(new TopicResponseDTO(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreated(), topic.getStatus(),
                topic.getAuthor().getId(), topic.getCourse().getId()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicResponseDTO> desactivateTopic(@RequestBody Long id) {

        if(!topicRepository.existsById(id)){
            throw new CustomValidationException("No existe un Topic con el id");
        }

        Topic topic = topicRepository.getReferenceById(id);
        topic.desactivateTopic();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {

        if(!topicRepository.existsById(id)){
            throw new CustomValidationException("No existe un Topic con el id: " + id.toString() );
        }

        Topic topic = topicRepository.getReferenceById(id);
        //topicRepository.delete(topic);
        topicRepository.deleteById(topic.getId());
        return ResponseEntity.noContent().build();
    }
}
