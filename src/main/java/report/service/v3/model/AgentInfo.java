package report.service.v3.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "agent_infos")
public class AgentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customerIp")
    private String customerIp;

    @Column(name = "customerUserAgent")
    private String customerUserAgent;

    @Column(name = "merchantIp")
    private String merchantIp;

    @Column(name = "merchantUserAgent")
    private String merchantUserAgent;

    @Column(name = "createdAt")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getCustomerUserAgent() {
        return customerUserAgent;
    }

    public void setCustomerUserAgent(String customerUserAgent) {
        this.customerUserAgent = customerUserAgent;
    }

    public String getMerchantIp() {
        return merchantIp;
    }

    public void setMerchantIp(String merchantIp) {
        this.merchantIp = merchantIp;
    }

    public String getMerchantUserAgent() {
        return merchantUserAgent;
    }

    public void setMerchantUserAgent(String merchantUserAgent) {
        this.merchantUserAgent = merchantUserAgent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
