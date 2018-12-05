package report.service.v3.service;

import org.springframework.data.domain.Page;
import report.service.v3.dto.TransactionListDTO;
import report.service.v3.exception.TransactionNotFoundException;
import report.service.v3.model.Transaction;
import report.service.v3.request.TransactionListRequest;

import java.util.List;

public interface TransactionService {
    List<TransactionListDTO> getTransactions(TransactionListRequest request);

    Page getPage();

    Transaction findTransactionByTransactionId(String transactionId) throws TransactionNotFoundException;
}
