package report.service.v3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import report.service.v3.exception.ArgumentsRequiredException;
import report.service.v3.exception.InvalidDateFormatException;
import report.service.v3.request.TransactionsReportRequest;
import report.service.v3.response.TransactionsReportResponse;
import report.service.v3.service.TransactionReportService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v3")
public class TransactionsReportController {

    @Autowired
    private TransactionReportService transactionReportService;

    @RequestMapping(value = "/transactions/report", method = RequestMethod.POST)
    public TransactionsReportResponse getTransactionsReport(@RequestBody (required=false) Map<String, Object> payload) throws IOException, InvalidDateFormatException, ArgumentsRequiredException {
        TransactionsReportRequest transactionsReportRequest = new TransactionsReportRequest(payload);
        return new TransactionsReportResponse(transactionReportService, transactionsReportRequest);
    }
}

