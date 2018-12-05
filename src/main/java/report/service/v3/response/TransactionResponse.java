package report.service.v3.response;

import report.service.v3.dto.*;
import report.service.v3.exception.TransactionNotFoundException;
import report.service.v3.model.AgentInfo;
import report.service.v3.model.CustomerInfo;
import report.service.v3.model.Transaction;
import report.service.v3.request.TransactionIdRequest;
import report.service.v3.service.TransactionService;

public class TransactionResponse {
    private TransactionMerchantDTO fx; // FxTransactionDTO
    private CustomerInfoDTO customerInfo;
    private AcquirerTransactionDTO acquirer;
    private MerchantDTO merchant;
    private TransactionMerchantDTO transaction; // TransactionWithAgentDTO

    public TransactionResponse(TransactionService transactionService, TransactionIdRequest transactionIdRequest) throws TransactionNotFoundException {
        Transaction transactionModel = transactionService.findTransactionByTransactionId(transactionIdRequest.getTransactionId());

        // Fx Transaction
        FxTransactionDTO fxTransaction = new FxTransactionDTO();
        fxTransaction.setOriginalCurrency(transactionModel.getFxTransaction().getOriginalCurrency());
        fxTransaction.setOriginalAmount(transactionModel.getFxTransaction().getOriginalAmount());
        fx = new TransactionMerchantDTO(fxTransaction);

        // Transaction - Agent info
        AgentInfo transactionAgentInfo = transactionModel.getAgentInfo();
        AgentInfoDTO agentInfoDTO = new AgentInfoDTO();
        agentInfoDTO.setId(transactionAgentInfo.getId());
        agentInfoDTO.setCustomerIp(transactionAgentInfo.getCustomerIp());
        agentInfoDTO.setCustomerUserAgent(transactionAgentInfo.getCustomerUserAgent());
        agentInfoDTO.setMerchantIp(transactionAgentInfo.getMerchantIp());
        agentInfoDTO.setMerchantUserAgent(transactionAgentInfo.getMerchantUserAgent());
        agentInfoDTO.setCreatedAt(transactionAgentInfo.getCreatedAt());

        TransactionWithAgentDTO transactionDTO = new TransactionWithAgentDTO();
        transactionDTO.setId(transactionModel.getId());
        transactionDTO.setTransactionId(transactionModel.getTransactionId());
        transactionDTO.setReferenceNo(transactionModel.getReferenceNo());
        transactionDTO.setStatus(transactionModel.getStatus());
        transactionDTO.setChannel(transactionModel.getChannel());
        transactionDTO.setCode(transactionModel.getCode());
        transactionDTO.setCustomData(transactionModel.getCustomData());
        transactionDTO.setChainId(transactionModel.getChainId());
        transactionDTO.setOperation(transactionModel.getOperation());
        transactionDTO.setMessage(transactionModel.getMessage());
        transactionDTO.setFxTransactionId(transactionModel.getFxTransactionId());
        transactionDTO.setMerchantId(transactionModel.getMerchantId());
        transactionDTO.setCustomerInfoId(transactionModel.getCustomerInfoId());
        transactionDTO.setAgentInfoId(transactionModel.getAgentInfoId());
        transactionDTO.setAcquirerTransactionId(transactionModel.getAcquirerTransactionId());
        transactionDTO.setCreatedAt(transactionModel.getCreatedAt());
        transactionDTO.setUpdatedAt(transactionModel.getUpdatedAt());
        transactionDTO.setAgentInfo(agentInfoDTO);
        transaction = new TransactionMerchantDTO(transactionDTO);

        // Merchant
        merchant = new MerchantDTO();
        merchant.setId(transactionModel.getMerchant().getId());
        merchant.setName(transactionModel.getMerchant().getName());

        // Acquirer Transaction
        acquirer = new AcquirerTransactionDTO();
        acquirer.setId(transactionModel.getAcquirerTransaction().getId());
        acquirer.setCode(transactionModel.getAcquirerTransaction().getCode());
        acquirer.setName(transactionModel.getAcquirerTransaction().getName());
        acquirer.setType(transactionModel.getAcquirerTransaction().getType());

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

    public TransactionMerchantDTO getFx() {
        return fx;
    }

    public void setFx(FxTransactionDTO fx) {
        this.fx = new TransactionMerchantDTO(fx);
    }

    public CustomerInfoDTO getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfoDTO customerInfo) {
        this.customerInfo = customerInfo;
    }

    public AcquirerTransactionDTO getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(AcquirerTransactionDTO acquirer) {
        this.acquirer = acquirer;
    }

    public MerchantDTO getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantDTO merchant) {
        this.merchant = merchant;
    }

    public TransactionMerchantDTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionWithAgentDTO transaction) {
        this.transaction = new TransactionMerchantDTO(transaction);
    }
}
