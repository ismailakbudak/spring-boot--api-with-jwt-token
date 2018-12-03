package report.service.v3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import report.service.v3.request.TransactionListRequest;
import report.service.v3.response.TransactionListResponse;
import report.service.v3.service.TransactionService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v3")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transaction/list", method = RequestMethod.POST)
    public TransactionListResponse getTransactions(@RequestBody (required=false) Map<String, Object> payload) throws IOException {

        TransactionListRequest transactionListRequest = new TransactionListRequest(payload);
        return new TransactionListResponse(transactionService, transactionListRequest, "/api/v3/transaction/list");
    }
}

