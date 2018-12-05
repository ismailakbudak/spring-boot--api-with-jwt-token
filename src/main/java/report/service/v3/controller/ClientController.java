package report.service.v3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import report.service.v3.exception.TransactionNotFoundException;
import report.service.v3.request.TransactionRequest;
import report.service.v3.response.ClientResponse;
import report.service.v3.service.TransactionService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v3")
public class ClientController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ClientResponse getClient(@RequestBody (required=false) Map<String, Object> payload) throws IOException, TransactionNotFoundException {

        TransactionRequest transactionRequest = new TransactionRequest(payload);
        return new ClientResponse(transactionService, transactionRequest);
    }
}

