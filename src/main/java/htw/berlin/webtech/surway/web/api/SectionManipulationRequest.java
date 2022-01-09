package htw.berlin.webtech.surway.web.api;

public class SectionManipulationRequest {

    private String question;
    private String answer;
    private Long surveyId;

    public SectionManipulationRequest(String question, String answer, Long surveyId) {
        this.question = question;
        this.answer = answer;
        this.surveyId = surveyId;
    }

    public SectionManipulationRequest() {}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }
}
