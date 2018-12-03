package report.service.v3.dto;

public class TransactionListDTO {
    private TransactionMerchantDTO fx; // FxTransactionDTO
    private CustomerInfoShortDTO customerInfo;
    private MerchantDTO merchant;
    private IpnTransactionDTO ipn;
    private TransactionMerchantDTO transaction; // TransactionShortDTO
    private AcquirerTransactionDTO acquirer;
    private Boolean refundable;

    public Boolean getRefundable() {
        return refundable;
    }

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    public AcquirerTransactionDTO getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(AcquirerTransactionDTO acquirer) {
        this.acquirer = acquirer;
    }

    public CustomerInfoShortDTO getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfoShortDTO customerInfo) {
        this.customerInfo = customerInfo;
    }

    public MerchantDTO getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantDTO merchant) {
        this.merchant = merchant;
    }

    public TransactionMerchantDTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionShortDTO transaction) {
        this.transaction = new TransactionMerchantDTO(transaction);
    }

    public IpnTransactionDTO getIpn() {
        return ipn;
    }

    public void setIpn(IpnTransactionDTO ipn) {
        this.ipn = ipn;
    }

    public TransactionMerchantDTO getFx() {
        return fx;
    }

    public void setFx(FxTransactionDTO fx) {
        this.fx = new TransactionMerchantDTO(fx);
    }
}
