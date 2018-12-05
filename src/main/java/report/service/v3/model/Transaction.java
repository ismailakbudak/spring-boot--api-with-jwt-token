package report.service.v3.model;

import report.service.v3.model.security.Merchant;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transactionId")
    private String transactionId;

    @Column(name = "referenceNo")
    private String referenceNo;

    @Column(name = "status")
    private String status;

    @Column(name = "channel")
    private String channel;

    @Column(name = "code")
    private String code;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "customData")
    private String customData;

    @Column(name = "chainId")
    private String chainId;

    @Column(name = "returnUrl")
    private String returnUrl;

    @Column(name = "type")
    private String type;

    @Column(name = "operation")
    private String operation;

    @Column(name = "message")
    private String message;

    @Column(name = "refundable")
    private Boolean refundable;

    @Column(name = "ipnTransactionId")
    private Integer ipnTransactionId;

    @OneToOne
    @JoinColumn(name="ipnTransactionId", insertable=false, updatable=false)
    private IpnTransaction ipnTransaction;

    @Column(name = "fxTransactionId")
    private Integer fxTransactionId;

    @OneToOne
    @JoinColumn(name="fxTransactionId", insertable=false, updatable=false)
    private FxTransaction fxTransaction;

    @Column(name = "merchantId")
    private Integer merchantId;

    @OneToOne
    @JoinColumn(name="merchantId", insertable=false, updatable=false)
    private Merchant merchant;

    @Column(name = "customerInfoId")
    private Integer customerInfoId;

    @OneToOne
    @JoinColumn(name="customerInfoId", insertable=false, updatable=false)
    private CustomerInfo customerInfo;

    @Column(name = "agentInfoId")
    private Integer agentInfoId;

    @OneToOne
    @JoinColumn(name="agentInfoId", insertable=false, updatable=false)
    private AgentInfo agentInfo;

    @Column(name = "acquirerTransactionId")
    private Integer acquirerTransactionId;

    @OneToOne
    @JoinColumn(name="acquirerTransactionId", insertable=false, updatable=false)
    private AcquirerTransaction acquirerTransaction;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "deletedAt")
    private Date deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    public Integer getIpnTransactionId() {
        return ipnTransactionId;
    }

    public void setIpnTransactionId(Integer ipnTransactionId) {
        this.ipnTransactionId = ipnTransactionId;
    }

    public Integer getFxTransactionId() {
        return fxTransactionId;
    }

    public void setFxTransactionId(Integer fxTransactionId) {
        this.fxTransactionId = fxTransactionId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCustomerInfoId() {
        return customerInfoId;
    }

    public void setCustomerInfoId(Integer customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    public Integer getAgentInfoId() {
        return agentInfoId;
    }

    public void setAgentInfoId(Integer agentInfoId) {
        this.agentInfoId = agentInfoId;
    }

    public Integer getAcquirerTransactionId() {
        return acquirerTransactionId;
    }

    public void setAcquirerTransactionId(Integer acquirerTransactionId) {
        this.acquirerTransactionId = acquirerTransactionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public AcquirerTransaction getAcquirerTransaction() {
        return acquirerTransaction;
    }

    public void setAcquirerTransaction(AcquirerTransaction acquirerTransaction) {
        this.acquirerTransaction = acquirerTransaction;
    }

    public AgentInfo getAgentInfo() {
        return agentInfo;
    }

    public void setAgentInfo(AgentInfo agentInfo) {
        this.agentInfo = agentInfo;
    }

    public FxTransaction getFxTransaction() {
        return fxTransaction;
    }

    public void setFxTransaction(FxTransaction fxTransaction) {
        this.fxTransaction = fxTransaction;
    }

    public IpnTransaction getIpnTransaction() {
        return ipnTransaction;
    }

    public void setIpnTransaction(IpnTransaction ipnTransaction) {
        this.ipnTransaction = ipnTransaction;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
}