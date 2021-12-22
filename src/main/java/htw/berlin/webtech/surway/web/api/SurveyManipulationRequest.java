package htw.berlin.webtech.surway.web.api;

public class SurveyManipulationRequest {

    private String title;
    private String description;
    private boolean limited;
    private String limitDate;

    public SurveyManipulationRequest(String title, String description, boolean limited, String limitDate) {
        this.title = title;
        this.description = description;
        this.limited = limited;
        this.limitDate = limitDate;
    }

    public SurveyManipulationRequest() {}

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

    public boolean isLimited() {
        return limited;
    }

    public void setLimited(boolean limited) {
        this.limited = limited;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }
}
