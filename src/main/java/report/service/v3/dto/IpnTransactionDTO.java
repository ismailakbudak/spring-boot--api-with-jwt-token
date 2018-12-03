package report.service.v3.dto;

public class IpnTransactionDTO {
    private Long id;
    private Boolean ipnSent;
    private Boolean ipnReceived;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Boolean getIpnSent() {
        return ipnSent;
    }

    public void setIpnSent(Boolean ipnSent) {
        this.ipnSent = ipnSent;
    }

    public Boolean getIpnReceived() {
        return ipnReceived;
    }

    public void setIpnReceived(Boolean ipnReceived) {
        this.ipnReceived = ipnReceived;
    }
}
