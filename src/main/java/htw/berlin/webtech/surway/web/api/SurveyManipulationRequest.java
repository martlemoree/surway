package htw.berlin.webtech.surway.web.api;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SurveyManipulationRequest {

    @Size(min = 3, message = "Please provide a title with at least 3 characters or more.")

    private String title;

    private String description;

    private boolean limited;

    @Pattern(
            regexp = "one|seven|thirty|onehundredandeighty|threehundredandsixty",
            message = "Please provide a limit as 1 day, 7, 30, 180 or 360 days."
    )
    private String limitDate;

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
