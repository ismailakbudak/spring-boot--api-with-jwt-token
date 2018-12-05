package report.service.v3.request;

import report.service.v3.exception.ArgumentsRequiredException;

import java.util.Map;

public class TransactionIdRequest {
    private String transactionId;

    public TransactionIdRequest(Map<String, Object> payload) throws ArgumentsRequiredException {
        if(payload != null && payload.get("transactionId") != null) {
            this.setTransactionId(payload.get("transactionId").toString());
        }

        if(this.getTransactionId() == null) {
            throw new ArgumentsRequiredException("transactionId arguments required");
        }
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
