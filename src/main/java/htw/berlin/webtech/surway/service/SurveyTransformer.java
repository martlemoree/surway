package htw.berlin.webtech.surway.service;

import htw.berlin.webtech.surway.persistance.SectionEntity;
import htw.berlin.webtech.surway.persistance.SurveyEntity;
import htw.berlin.webtech.surway.web.api.Survey;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SurveyTransformer {

    public Survey transformEntity(SurveyEntity surveyEntity) {
        var limitDate = surveyEntity.getLimitDate().name();
        var sectionIds = surveyEntity.getSections().stream().map(SectionEntity::getId).collect(Collectors.toList());
        return new Survey(
                surveyEntity.getId(),
                surveyEntity.getTitle(),
                surveyEntity.getDescription(),
                surveyEntity.getLimited(),
                limitDate,
                sectionIds
        );
    }
}
