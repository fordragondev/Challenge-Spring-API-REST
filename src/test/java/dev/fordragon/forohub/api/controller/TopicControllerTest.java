package dev.fordragon.forohub.api.controller;

import dev.fordragon.forohub.api.domain.topic.*;
import dev.fordragon.forohub.api.domain.topic.customRules.TopicRules;
import org.junit.jupiter.api.Test;

import dev.fordragon.forohub.api.domain.appuser.AppUser;
import dev.fordragon.forohub.api.domain.appuser.AppUserCreationDTO;
import dev.fordragon.forohub.api.domain.course.Category;
import dev.fordragon.forohub.api.domain.course.Course;
import dev.fordragon.forohub.api.domain.course.CourseCreationDTO;
import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TopicCreatorService topicCreatorService;

    @Autowired
    private JacksonTester<TopicCreationDTO> topicCreationDTOJacksonTester;

    @Autowired
    private JacksonTester<TopicResponseDTO> topicResponseDTOJacksonTester;

    @Test
    @DisplayName("Deberia devolver http 400 cuando la request no tenga datos")
    @WithMockUser
    void createTopicNoData() throws Exception {
        var response = mvc.perform(post("/topics"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia devolver http 201 cuando la request reciba un json valido")
    @WithMockUser
    void createTopicWithData() throws Exception {

        var category = Category.CATEGORYA;
        var datosDetalle = new TopicResponseDTO(1, "title", "message", LocalDateTime.now(), Status.ACTIVE,1L, 1L );
        when(topicCreatorService.createTopic(any())).thenReturn(datosDetalle);

        var response = mvc.perform(post("/topics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(topicCreationDTOJacksonTester.write(
                                        new TopicCreationDTO("title", "message", 1L, 1L )).getJson())
                )
                .andReturn().getResponse();

        var jsonEsperado = topicResponseDTOJacksonTester.write(datosDetalle).getJson();
        //assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}