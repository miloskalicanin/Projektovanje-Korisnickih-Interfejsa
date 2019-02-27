package kupacdesktop.network.dto;

import java.util.Date;

public class SearchParametersDTO {

    private String job;
    private String priceFrom;
    private String priceTo;
    private Date dateBegin;
    private Date dateEnd;
    private String experienceFrom;
    private String experienceTo;
    private String ratingFrom;
    private String ratingTo;
    private boolean specialTechniques;
    private boolean urgency;

    public SearchParametersDTO() {
    }

    public SearchParametersDTO(String job, String priceFrom, String priceTo, Date dateBegin, Date dateEnd, String experienceFrom, String experienceTo, String ratingFrom, String ratingTo, boolean specialTechniques, boolean urgency) {
        this.job = job;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.experienceFrom = experienceFrom;
        this.experienceTo = experienceTo;
        this.ratingFrom = ratingFrom;
        this.ratingTo = ratingTo;
        this.specialTechniques = specialTechniques;
        this.urgency = urgency;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getExperienceFrom() {
        return experienceFrom;
    }

    public void setExperienceFrom(String experienceFrom) {
        this.experienceFrom = experienceFrom;
    }

    public String getExperienceTo() {
        return experienceTo;
    }

    public void setExperienceTo(String experienceTo) {
        this.experienceTo = experienceTo;
    }

    public String getRatingFrom() {
        return ratingFrom;
    }

    public void setRatingFrom(String ratingFrom) {
        this.ratingFrom = ratingFrom;
    }

    public String getRatingTo() {
        return ratingTo;
    }

    public void setRatingTo(String ratingTo) {
        this.ratingTo = ratingTo;
    }

    public boolean isSpecialTechniques() {
        return specialTechniques;
    }

    public void setSpecialTechniques(boolean specialTechniques) {
        this.specialTechniques = specialTechniques;
    }

    public boolean isUrgency() {
        return urgency;
    }

    public void setUrgency(boolean urgency) {
        this.urgency = urgency;
    }

    @Override
    public String toString() {
        return "SearchParametersDTO{"
                + "job='" + job + '\''
                + ", priceFrom='" + priceFrom + '\''
                + ", priceTo='" + priceTo + '\''
                + ", dateBegin=" + dateBegin
                + ", dateEnd=" + dateEnd
                + ", experienceFrom='" + experienceFrom + '\''
                + ", experienceTo='" + experienceTo + '\''
                + ", ratingFrom='" + ratingFrom + '\''
                + ", ratingTo='" + ratingTo + '\''
                + ", specialTechniques=" + specialTechniques
                + ", urgency=" + urgency
                + '}';
    }
}
