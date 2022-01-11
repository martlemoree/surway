package htw.berlin.webtech.surway.service;

import htw.berlin.webtech.surway.persistance.Limit;
import htw.berlin.webtech.surway.persistance.SurveyEntity;
import htw.berlin.webtech.surway.persistance.SurveyRepository;
import htw.berlin.webtech.surway.web.api.Survey;
import htw.berlin.webtech.surway.web.api.SurveyManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyTransformer surveyTransformer;

    public SurveyService(SurveyRepository surveyRepository, SurveyTransformer surveyTransformer) {
        this.surveyRepository = surveyRepository;
        this.surveyTransformer = surveyTransformer;
    }

    public List<Survey> findAll() {
        List<SurveyEntity> surveys = surveyRepository.findAll();
        return surveys.stream()
                .map(surveyTransformer::transformEntity)
                .collect(Collectors.toList());
    }

    public Survey findById(Long id) {
        var surveyEntity = surveyRepository.findById(id);
        return surveyEntity.map(surveyTransformer::transformEntity).orElse(null);
    }

    public Survey create(SurveyManipulationRequest request) {
        var limitDate = Limit.valueOf(request.getLimitDate());
        var surveyEntity = new SurveyEntity(request.getTitle(), request.getDescription(), request.isLimited(), limitDate);
        surveyEntity = surveyRepository.save(surveyEntity);
        return surveyTransformer.transformEntity(surveyEntity);
    }

    public Survey update(Long id, SurveyManipulationRequest request) {
        var surveyEntityOptional = surveyRepository.findById(id);
        if (surveyEntityOptional.isEmpty()) {
            return null;
        }

        var surveyEntity = surveyEntityOptional.get();
        surveyEntity.setTitle(request.getTitle());
        surveyEntity.setDescription(request.getDescription());
        surveyEntity.setLimited(request.isLimited());
        surveyEntity.setLimitDate(Limit.valueOf(request.getLimitDate()));
        surveyEntity = surveyRepository.save(surveyEntity);

        return surveyTransformer.transformEntity(surveyEntity);
    }

    public boolean deleteById(Long id) {
        if (!surveyRepository.existsById(id)) {
            return false;
        }

        surveyRepository.deleteById(id);
        return true;
    }
}
