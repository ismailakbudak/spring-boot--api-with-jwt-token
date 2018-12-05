package report.service.v3.response;

import report.service.v3.dto.*;
import report.service.v3.exception.TransactionNotFoundException;
import report.service.v3.model.CustomerInfo;
import report.service.v3.model.Transaction;
import report.service.v3.request.TransactionIdRequest;
import report.service.v3.service.TransactionService;

public class ClientResponse {
    private CustomerInfoDTO customerInfo;

    public ClientResponse(TransactionService transactionService, TransactionIdRequest transactionIdRequest) throws TransactionNotFoundException {
        Transaction transactionModel = transactionService.findTransactionByTransactionId(transactionIdRequest.getTransactionId());

        // Customer Info
        CustomerInfo transactionCustomer = transactionModel.getCustomerInfo();
        customerInfo = new CustomerInfoDTO();
        customerInfo.setId(transactionCustomer.getId());
        customerInfo.setNumber(transactionCustomer.getNumber());
        customerInfo.setExpiryMonth(transactionCustomer.getExpiryMonth());
        customerInfo.setExpiryYear(transactionCustomer.getExpiryYear());
        customerInfo.setStartMonth(transactionCustomer.getStartMonth());
        customerInfo.setStartYear(transactionCustomer.getStartYear());
        customerInfo.setIssueNumber(transactionCustomer.getIssueNumber());
        customerInfo.setEmail(transactionCustomer.getEmail());
        customerInfo.setBirthday(transactionCustomer.getBirthday());
        customerInfo.setGender(transactionCustomer.getGender());
        customerInfo.setBillingTitle(transactionCustomer.getBillingTitle());
        customerInfo.setBillingFirstName(transactionCustomer.getBillingFirstName());
        customerInfo.setBillingLastName(transactionCustomer.getBillingLastName());
        customerInfo.setBillingCompany(transactionCustomer.getBillingCompany());
        customerInfo.setBillingAddress1(transactionCustomer.getBillingAddress1());
        customerInfo.setBillingAddress2(transactionCustomer.getBillingAddress2());
        customerInfo.setBillingCity(transactionCustomer.getBillingCity());
        customerInfo.setBillingPostcode(transactionCustomer.getBillingPostcode());
        customerInfo.setBillingState(transactionCustomer.getBillingState());
        customerInfo.setBillingCountry(transactionCustomer.getBillingCountry());
        customerInfo.setBillingPhone​(transactionCustomer.getBillingPhone​());
        customerInfo.setBillingFax(transactionCustomer.getBillingFax());
        customerInfo.setShippingTitle(transactionCustomer.getShippingTitle());
        customerInfo.setShippingFirstName(transactionCustomer.getShippingFirstName());
        customerInfo.setShippingLastName​(transactionCustomer.getShippingLastName​());
        customerInfo.setShippingCompany(transactionCustomer.getShippingCompany());
        customerInfo.setShippingAddress1​(transactionCustomer.getShippingAddress1​());
        customerInfo.setShippingAddress2​(transactionCustomer.getShippingAddress2​());
        customerInfo.setShippingCity​(transactionCustomer.getShippingCity​());
        customerInfo.setShippingPostcode​(transactionCustomer.getShippingPostcode​());
        customerInfo.setShippingState(transactionCustomer.getShippingState());
        customerInfo.setShippingCountry(transactionCustomer.getShippingCountry());
        customerInfo.setShippingPhone(transactionCustomer.getShippingPhone());
        customerInfo.setShippingFax(transactionCustomer.getShippingFax());
        customerInfo.setToken(transactionCustomer.getToken());
        customerInfo.setCreatedAt(transactionCustomer.getCreatedAt());
        customerInfo.setUpdatedAt(transactionCustomer.getUpdatedAt());
    }

    public CustomerInfoDTO getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfoDTO customerInfo) {
        this.customerInfo = customerInfo;
    }
}
