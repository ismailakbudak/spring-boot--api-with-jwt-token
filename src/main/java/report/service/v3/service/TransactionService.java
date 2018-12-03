package report.service.v3.service;

import org.springframework.data.domain.Page;
import report.service.v3.dto.TransactionListDTO;
import report.service.v3.dto.TransactionReportDTO;
import report.service.v3.request.TransactionListRequest;
import report.service.v3.request.TransactionsReportRequest;

import java.util.List;

public interface TransactionService {
    List<TransactionListDTO> getTransactions(TransactionListRequest request);
    Page getPage();
}
