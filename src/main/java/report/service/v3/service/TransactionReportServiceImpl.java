package report.service.v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.service.v3.dto.TransactionReport;
import report.service.v3.dto.TransactionReportDTO;
import report.service.v3.repository.TransactionReportRepository;
import report.service.v3.request.TransactionsReportRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionReportServiceImpl implements TransactionReportService {

    @Autowired
    private TransactionReportRepository repository;

    @Override
    public List<TransactionReportDTO> getTransactionsReport(TransactionsReportRequest request) {
        List<TransactionReport> transactions = handleQuery(request);

        List<TransactionReportDTO> transactionReportDTOs = new ArrayList<>();

        TransactionReportDTO transactionReportDTO = null;

        for(TransactionReport transactionReport : transactions){
            transactionReportDTO = new TransactionReportDTO();
            transactionReportDTO.setCount(transactionReport.getCount());
            transactionReportDTO.setTotal(transactionReport.getTotal());
            transactionReportDTO.setCurrency(transactionReport.getCurrency());

            transactionReportDTOs.add(transactionReportDTO);
        }

        return transactionReportDTOs;
    }

    private List<TransactionReport> handleQuery(TransactionsReportRequest request) {
        Date fromDate = request.getFromDate();
        Date endDate = request.getEndDate();
        Integer merchant = request.getMerchant();
        Integer acquirer = request.getAcquirer();

        if(acquirer != null && merchant != null)
            return repository.findTransactionWithMerchantAndAcquirerCount(fromDate, endDate, merchant, acquirer);
        if(merchant != null)
            return repository.findTransactionWithMerchantCount(fromDate, endDate, merchant);
        if(acquirer != null)
            return repository.findTransactionWithAcquirerCount(fromDate, endDate, acquirer);

        return repository.findTransactionCount(fromDate, endDate);
    }
}
