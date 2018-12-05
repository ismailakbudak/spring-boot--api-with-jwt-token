package report.service.v3.request;

import report.service.v3.exception.InvalidFieldException;
import report.service.v3.util.DateUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TransactionListRequest {
    private Map<String, String> filterFields;
    private Integer page;
    private Date fromDate;
    private Date endDate;
    private String status;
    private String operation;
    private String paymentMethod;
    private String errorCode;
    private Integer merchantId;
    private Integer acquirerId;
    private String filterField;
    private String filterValue;
    private static final Integer DEFAULT_PAGE = 1;

    public TransactionListRequest(Map<String, Object> payload) throws InvalidFieldException {
        this.filterFields = new HashMap<String, String>(){{
            put("transactionId", "Transaction​ ​UUID");
            put("referenceNo", "Reference​ ​No");
            put("customData", "Custom​ ​Data");
        }};

        if(payload != null) {
            this.setPage(Integer.parseInt(payload.getOrDefault("page", DEFAULT_PAGE).toString()));
            this.setFromDate(DateUtil.parse(payload.get("fromDate")));
            this.setEndDate(DateUtil.parse(payload.get("endDate")));

            if(payload.get("status") != null)
                this.setStatus(payload.get("status").toString());

            if(payload.get("operation") != null)
                this.setOperation(payload.get("operation").toString());

            if(payload.get("paymentMethod") != null)
                this.setPaymentMethod(payload.get("paymentMethod").toString());

            if(payload.get("errorCode") != null)
                this.setErrorCode(payload.get("errorCode").toString());

            if(payload.get("merchantId") != null)
                this.setMerchantId(Integer.parseInt(payload.get("merchantId").toString()));

            if(payload.get("acquirerId") != null)
                this.setAcquirerId(Integer.parseInt(payload.get("acquirerId").toString()));

            if(payload.get("filterField") != null)
                this.setFilterField(payload.get("filterField").toString());

            if(payload.get("filterValue") != null)
                this.setFilterValue(payload.get("filterValue").toString());

        } else {
            this.setPage(DEFAULT_PAGE);
        }
    }

    public Integer getPage() {
        return page;
    }

    private void setPage(Integer page) {
        this.page = page;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(Integer acquirerId) {
        this.acquirerId = acquirerId;
    }

    public String getFilterField() {
        return filterField;
    }

    public void setFilterField(String filterField) throws InvalidFieldException {
        if(!filterFields.values().contains(filterField))
            throw new InvalidFieldException("filterField is invalid");

        int keyNumber =  Arrays.asList(filterFields.values().toArray()).indexOf(filterField);
        this.filterField = filterFields.keySet().toArray()[keyNumber].toString();
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }
}