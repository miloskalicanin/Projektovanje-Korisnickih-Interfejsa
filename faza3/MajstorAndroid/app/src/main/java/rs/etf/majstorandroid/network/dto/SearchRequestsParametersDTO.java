package rs.etf.majstorandroid.network.dto;

import java.util.Date;

public class SearchRequestsParametersDTO {
    private Double distance;
    private Date dateBegin;
    private boolean urgency;

    public SearchRequestsParametersDTO() {
    }

    public SearchRequestsParametersDTO(Double distance, Date dateBegin, boolean urgency) {
        this.distance = distance;
        this.dateBegin = dateBegin;
        this.urgency = urgency;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public boolean isUrgency() {
        return urgency;
    }

    public void setUrgency(boolean urgency) {
        this.urgency = urgency;
    }

    @Override
    public String toString() {
        return "SearchRequestsParametersDTO{" +
                "distance=" + distance +
                ", dateBegin=" + dateBegin +
                ", urgency=" + urgency +
                '}';
    }
}
