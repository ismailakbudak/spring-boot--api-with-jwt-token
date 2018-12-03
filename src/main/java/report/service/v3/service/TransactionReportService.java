package report.service.v3.service;

import report.service.v3.dto.TransactionReportDTO;
import report.service.v3.request.TransactionsReportRequest;

import java.util.List;

public interface TransactionReportService {
    List<TransactionReportDTO> getTransactionsReport(TransactionsReportRequest transactionsReportRequest);
}
