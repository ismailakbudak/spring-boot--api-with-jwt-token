package report.service.v3.dto;

public class TransactionMerchantDTO {
    private Object merchant;

    public TransactionMerchantDTO(Object merchantDTO) {
        this.setMerchant(merchantDTO);
    }

    public Object getMerchant() {
        return merchant;
    }

    public void setMerchant(Object merchant) {
        this.merchant = merchant;
    }
}
