package rs.etf.kupacandroid.network.dto;

import java.util.Date;

public class CommentDTO {
    private Date date;
    private String comment;

    public CommentDTO() {
    }

    public CommentDTO(Date date, String comment) {
        this.date = date;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "date=" + date +
                ", comment='" + comment + '\'' +
                '}';
    }
}
