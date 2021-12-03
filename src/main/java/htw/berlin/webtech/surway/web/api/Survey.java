package htw.berlin.webtech.surway.web.api;

public class Survey {

    private long id;
    private String title;
    private String description;
    private boolean limited;
    private String limitDate;

    public Survey(long id, String title, String description, boolean limited, String limitDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.limited = limited;
        this.limitDate = limitDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
