package htw.berlin.webtech.surway.persistance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "surveys")
public class SurveyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "limited")
    private Boolean limited;

    @Column(name = "limitDate")
    @Enumerated(value = EnumType.STRING)
    private Limit limitDate;

    @OneToMany(mappedBy = "surveyId", fetch = FetchType.EAGER)
    private List<SectionEntity> sections = new ArrayList<>();

    public SurveyEntity(String title, String description, Boolean limited, Limit limitDate) {
        this.title = title;
        this.description = description;
        this.limited = limited;
        this.limitDate = limitDate;
    }

    protected SurveyEntity() {}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLimited() {
        return limited;
    }

    public void setLimited(Boolean limited) {
        this.limited = limited;
    }

    public Limit getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Limit limitDate) {
        this.limitDate = limitDate;
    }

    public List<SectionEntity> getSections() {
        return sections;
    }

    public void setSections(List<SectionEntity> sections) {
        this.sections = sections;
    }
}
