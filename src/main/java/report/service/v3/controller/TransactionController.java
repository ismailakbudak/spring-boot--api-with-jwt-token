package report.service.v3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import report.service.v3.exception.InvalidDateFormatException;
import report.service.v3.exception.TransactionNotFoundException;
import report.service.v3.request.TransactionListRequest;
import report.service.v3.request.TransactionRequest;
import report.service.v3.response.TransactionListResponse;
import report.service.v3.response.TransactionResponse;
import report.service.v3.service.TransactionService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v3")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transaction/list", method = RequestMethod.POST)
    public TransactionListResponse getTransactions(@RequestBody (required=false) Map<String, Object> payload) throws IOException, InvalidDateFormatException {

        TransactionListRequest transactionListRequest = new TransactionListRequest(payload);
        return new TransactionListResponse(transactionService, transactionListRequest, "/api/v3/transaction/list");
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public TransactionResponse getTransaction(@RequestBody (required=false) Map<String, Object> payload) throws IOException, TransactionNotFoundException {

        TransactionRequest transactionRequest = new TransactionRequest(payload);
        return new TransactionResponse(transactionService, transactionRequest);
    }
}

