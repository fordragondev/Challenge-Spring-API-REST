package dev.fordragon.forohub.api.controller;

import dev.fordragon.forohub.api.domain.course.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> registrarCourse (@RequestBody @Valid CourseCreationDTO courseCreationDTO,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        Course course = courseRepository.save(new Course(courseCreationDTO));

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(course.getId(), course.getName(), course.getCategory());
        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(courseResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseListDTO>> listCourse(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(courseRepository.findAll(paginacion).map(CourseResponseListDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourse(@PathVariable Long id) {
        Course course = courseRepository.getReferenceById(id);
        var courseData = new CourseResponseDTO(course.getId(), course.getName(), course.getCategory());
        return ResponseEntity.ok(courseData);
    }
}
