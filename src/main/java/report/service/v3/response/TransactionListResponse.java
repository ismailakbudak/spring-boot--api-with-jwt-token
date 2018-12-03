package report.service.v3.response;

import report.service.v3.dto.TransactionListDTO;
import report.service.v3.request.TransactionListRequest;
import report.service.v3.service.TransactionService;

import java.util.List;

public class TransactionListResponse {
    private Integer perPage;
    private Integer currentPage;
    private String nextPageUrl;
    private String prevPageUrl;
    private Integer from;
    private Integer to;
    private List<TransactionListDTO> data;

    public TransactionListResponse(TransactionService transactionService, TransactionListRequest transactionListRequest, String pageUrl) {
        this.data = transactionService.getTransactions(transactionListRequest);

        this.perPage = transactionService.getPage().getSize();
        currentPage = transactionListRequest.getPage();

        if(transactionService.getPage().getTotalPages() > currentPage) {
            this.nextPageUrl = pageUrl + "?page=" + (currentPage + 1);
        }
        if(currentPage > 1) {
            this.prevPageUrl = pageUrl + "?page=" + (currentPage - 1);
        }
        this.from = ((currentPage - 1) * perPage) + 1;
        this.to = from - 1 + data.size();
    }

    public Integer getPer_page() {
        return perPage;
    }

    public Integer getCurrent_page() {
        return currentPage;
    }

    public String getNext_page_url() {
        return nextPageUrl;
    }

    public String getPrev_page_url() {
        return prevPageUrl;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public  List<TransactionListDTO> getData() {
        return data;
    }
}
