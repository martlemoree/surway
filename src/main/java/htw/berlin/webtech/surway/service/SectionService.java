package htw.berlin.webtech.surway.service;

import htw.berlin.webtech.surway.persistance.SurveyRepository;
import htw.berlin.webtech.surway.persistance.SectionEntity;
import htw.berlin.webtech.surway.persistance.SectionRepository;
import htw.berlin.webtech.surway.web.api.Section;
import htw.berlin.webtech.surway.web.api.SectionManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyTransformer surveyTransformer;

    public SectionService(SectionRepository sectionRepository, SurveyRepository surveyRepository, SurveyTransformer surveyTransformer) {
        this.sectionRepository = sectionRepository;
        this.surveyRepository = surveyRepository;
        this.surveyTransformer = surveyTransformer;
    }

    public List<Section> findAll() {
        List<SectionEntity> sections = sectionRepository.findAll();
        return sections.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Section create(SectionManipulationRequest request) {
        var surveyId = surveyRepository.findById(request.getSurveyId()).orElseThrow();
        var sectionEntity = new SectionEntity(request.getQuestion(), request.getAnswer(), surveyId);
        sectionEntity = sectionRepository.save(sectionEntity);
        return transformEntity(sectionEntity);
    }

    private Section transformEntity(SectionEntity sectionEntity) {
        return new Section(
                sectionEntity.getId(),
                sectionEntity.getQuestion(),
                sectionEntity.getAnswer(),
                surveyTransformer.transformEntity(sectionEntity.getSurveyId()));
    }
}
