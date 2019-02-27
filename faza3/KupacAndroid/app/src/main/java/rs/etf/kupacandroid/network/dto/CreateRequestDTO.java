package rs.etf.kupacandroid.network.dto;

import java.util.Date;

public class CreateRequestDTO {
    private String clientUsername;
    private String repairmanUsername;
    private Date dateBegin;
    private Date dateEnd;
    private String paymentMethod;

    public CreateRequestDTO() {
    }

    public CreateRequestDTO(String clientUsername, String repairmanUsername, Date dateBegin, Date dateEnd, String paymentMethod) {
        this.clientUsername = clientUsername;
        this.repairmanUsername = repairmanUsername;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.paymentMethod = paymentMethod;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getRepairmanUsername() {
        return repairmanUsername;
    }

    public void setRepairmanUsername(String repairmanUsername) {
        this.repairmanUsername = repairmanUsername;
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

    @Override
    public String toString() {
        return "CreateRequestDTO{" +
                "clientUsername='" + clientUsername + '\'' +
                ", repairmanUsername='" + repairmanUsername + '\'' +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
