package htw.berlin.webtech.surway.web;

import htw.berlin.webtech.surway.service.SurveyService;
import htw.berlin.webtech.surway.web.api.Survey;
import htw.berlin.webtech.surway.web.api.SurveyManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class SurveyRestController {

    private final SurveyService surveyService;


    public SurveyRestController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping(path = "/api/v1/surveys")
    public ResponseEntity<List<Survey>> fetchSurveys() {
        return ResponseEntity.ok(surveyService.findAll());
    }

    @GetMapping(path = "/api/v1/surveys/{id}")
    public ResponseEntity<Survey> fetchSurveyById(@PathVariable Long id) {
        var survey = surveyService.findById(id);
        return survey != null? ResponseEntity.ok(survey) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/survey")
    public ResponseEntity<Void> createSurvey(@RequestBody SurveyManipulationRequest request) throws URISyntaxException {
        var survey = surveyService.create(request);
        URI uri = new URI("/api/v1/surveys/" + survey.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/surveys/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody SurveyManipulationRequest request) {
        var survey = surveyService.update(id, request);
        return survey != null? ResponseEntity.ok(survey) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/surveys/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        boolean successful = surveyService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
