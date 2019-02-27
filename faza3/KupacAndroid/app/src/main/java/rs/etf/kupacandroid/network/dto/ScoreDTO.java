package rs.etf.kupacandroid.network.dto;

import java.util.Date;

public class ScoreDTO {
    private Date date;
    private Double score;

    public ScoreDTO() {
    }

    public ScoreDTO(Date date, Double score) {
        this.date = date;
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreDTO{" +
                "date=" + date +
                ", score=" + score +
                '}';
    }
}
