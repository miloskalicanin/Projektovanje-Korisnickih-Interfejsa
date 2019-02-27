package kupacdesktop.network.dto;

import java.util.Date;

public class RequestInfoDTO {

    private UserDTO client;
    private UserDTO repairman;
    private Date dateBegin;
    private Date dateEnd;
    private String paymentMethod;
    private boolean rateSent = false;
    private boolean commentSent = false;

    public RequestInfoDTO() {
    }

    public RequestInfoDTO(UserDTO client, UserDTO repairman, Date dateBegin, Date dateEnd, String paymentMethod) {
        this.client = client;
        this.repairman = repairman;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.paymentMethod = paymentMethod;
    }

    public UserDTO getClient() {
        return client;
    }

    public void setClient(UserDTO client) {
        this.client = client;
    }

    public UserDTO getRepairman() {
        return repairman;
    }

    public void setRepairman(UserDTO repairman) {
        this.repairman = repairman;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isRateSent() {
        return rateSent;
    }

    public void setRateSent(boolean rateSent) {
        this.rateSent = rateSent;
    }

    public boolean isCommentSent() {
        return commentSent;
    }

    public void setCommentSent(boolean commentSent) {
        this.commentSent = commentSent;
    }

    @Override
    public String toString() {
        return "RequestInfoDTO{"
                + "client=" + client
                + ", repairman=" + repairman
                + ", dateBegin=" + dateBegin
                + ", dateEnd=" + dateEnd
                + ", paymentMethod='" + paymentMethod + '\''
                + ", rateSent=" + rateSent
                + ", commentSent=" + commentSent
                + '}';
    }
}
