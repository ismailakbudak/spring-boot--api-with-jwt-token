package report.service.v3.dto;

public class TransactionReport {
    private Long count;
    private Long total;
    private String currency;

    public TransactionReport(String currency, Long count, Long total) {
        this.currency  = currency;
        this.total = total;
        this.count  = count;
    }

    public Long getCount() {
        return count;
    }

    public Long getTotal() {
        return total;
    }

    public String getCurrency() {
        return currency;
    }
}
