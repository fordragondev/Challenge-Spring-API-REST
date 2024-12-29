package dev.fordragon.forohub.api.domain.topic.customRules;

import dev.fordragon.forohub.api.domain.topic.TopicCreationDTO;
import dev.fordragon.forohub.api.domain.topic.TopicUpdateDTO;

public interface TopicRules {
    void validar(TopicCreationDTO datos);

    void validar(TopicUpdateDTO datos);
}
