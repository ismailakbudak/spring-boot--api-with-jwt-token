package report.service.v3.request;

import report.service.v3.exception.InvalidDateFormatException;
import report.service.v3.util.DateUtil;

import java.util.Date;
import java.util.Map;

public class TransactionListRequest {
    private Integer page;
    private Date fromDate;
    private Date endDate;
    private static final Integer DEFAULT_PAGE = 1;

    public TransactionListRequest(Map<String, Object> payload) throws InvalidDateFormatException {
        if(payload != null) {
            this.setPage((int) payload.getOrDefault("page", DEFAULT_PAGE));
            this.setFromDate(DateUtil.parse(payload.get("fromDate")));
            this.setEndDate(DateUtil.parse(payload.get("endDate")));
        } else {
            this.setPage(DEFAULT_PAGE);
        }
    }

    public Integer getPage() {
        return page;
    }

    private void setPage(Integer page) {
        this.page = page;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}