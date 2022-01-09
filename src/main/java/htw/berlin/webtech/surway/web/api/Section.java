package htw.berlin.webtech.surway.web.api;

public class Section {

    private Long id;
    private String question;
    private String answer;
    private Survey survey;

    public Section(Long id, String question, String answer, Survey survey) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
