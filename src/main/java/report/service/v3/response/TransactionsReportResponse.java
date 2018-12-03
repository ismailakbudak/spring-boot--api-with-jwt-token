package report.service.v3.response;

import report.service.v3.dto.TransactionReportDTO;
import report.service.v3.request.TransactionsReportRequest;
import report.service.v3.service.TransactionReportService;

import java.util.List;

import static report.service.v3.constants.ResponseConstants.APPROVED;

public class TransactionsReportResponse {
    private String status = APPROVED;
    private List<TransactionReportDTO> response;

    public TransactionsReportResponse(TransactionReportService transactionReportService, TransactionsReportRequest transactionsReportRequest) {
        this.response = transactionReportService.getTransactionsReport(transactionsReportRequest);
    }

    public String getStatus() {
        return status;
    }

    public List<TransactionReportDTO> getResponse() {
        return response;
    }
}
