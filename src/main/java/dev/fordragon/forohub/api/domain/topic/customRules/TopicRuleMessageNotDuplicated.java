package dev.fordragon.forohub.api.domain.topic.customRules;

import dev.fordragon.forohub.api.domain.topic.TopicCreationDTO;
import dev.fordragon.forohub.api.domain.topic.TopicRepository;
import dev.fordragon.forohub.api.domain.topic.TopicUpdateDTO;
import dev.fordragon.forohub.api.infra.validation.CustomValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicRuleMessageNotDuplicated implements TopicRules{

    @Autowired
    private TopicRepository repository;


    public void validar(TopicCreationDTO datos) {

        if(datos.idAuthor() == null) {
            return;
        }
        boolean tittleExist = repository.existsByTitleAndMessage (datos.title(), datos.message());
        if(tittleExist){
            throw new CustomValidationException ("El Topico ya existe con el mismo mensaje");
        }
    }

    public void validar(TopicUpdateDTO datos) {

        boolean tittleExist = repository.existsByTitleAndMessage (datos.title(), datos.message());
        if(tittleExist){
            throw new CustomValidationException ("El Topico ya existe con el mismo mensaje");
        }
    }
}
