package report.service.v3.model;

import javax.persistence.*;

@Entity
@Table(name = "ipn_transactions")
public class IpnTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ipnSent")
    private Boolean ipnSent;

    @Column(name = "ipnReceived")
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
