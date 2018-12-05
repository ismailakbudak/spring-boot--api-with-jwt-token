package report.service.v3.request;

import report.service.v3.exception.ArgumentsRequiredException;
import report.service.v3.exception.InvalidDateFormatException;
import report.service.v3.util.DateUtil;

import java.util.Date;
import java.util.Map;

public class TransactionsReportRequest {
    private Integer merchant;
    private Integer acquirer;
    private Date fromDate;
    private Date endDate;

    public TransactionsReportRequest(Map<String, Object> payload) throws InvalidDateFormatException, ArgumentsRequiredException {
        if(payload != null) {
            this.setMerchant(payload.getOrDefault("merchant", null));
            this.setAcquirer(payload.getOrDefault("acquirer", null));
            this.setFromDate(DateUtil.parse(payload.get("fromDate")));
            this.setEndDate(DateUtil.parse(payload.get("endDate")));
        }

        if( this.getFromDate() == null || this.getEndDate() == null ) {
            throw new ArgumentsRequiredException("fromDate and endDate arguments required");
        }
    }

    public Integer getMerchant() {
        return merchant;
    }

    public void setMerchant(Object merchant) {
        if(merchant == null)
            return;

        this.merchant = Integer.parseInt(merchant.toString());
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(Object acquirer) {
        if(acquirer == null)
            return;

        this.acquirer = Integer.parseInt(acquirer.toString());
    }
}
