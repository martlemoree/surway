package htw.berlin.webtech.surway.persistance;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sections")
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "answer")
    private String answer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "surveyId", referencedColumnName = "id")
    private SurveyEntity surveyId;

    public SectionEntity() {
    }

    public SectionEntity(String question, String answer, SurveyEntity surveyId) {
        this.question = question;
        this.answer = answer;
        this.surveyId = surveyId;
    }

    public Long getId() {
        return id;
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

    public SurveyEntity getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(SurveyEntity surveyId) {
        this.surveyId = surveyId;
    }
}
