package report.service.v3.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TransactionListRequest {
    private Integer page;
    private Date fromDate;
    private Date endDate;
    private static final Integer DEFAULT_PAGE = 1;

    public TransactionListRequest(Map<String, Object> payload){
        if(payload != null) {
            this.setPage((int) payload.getOrDefault("page", DEFAULT_PAGE));
            this.setFromDate(dateParse(payload.get("fromDate")));
            this.setEndDate(dateParse(payload.get("endDate")));
        } else {
            this.setPage(DEFAULT_PAGE);
        }
    }

    private Date dateParse(Object date) {
        if(date == null)
            return null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
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