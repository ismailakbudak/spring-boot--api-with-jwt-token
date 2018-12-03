package report.service.v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import report.service.v3.dto.*;
import report.service.v3.model.Transaction;
import report.service.v3.repository.TransactionRepository;
import report.service.v3.request.TransactionListRequest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class TransactionServiceImpl implements TransactionService {
    public static final Integer DEFAULT_LIMIT = 30;

    @Autowired
    private TransactionRepository repository;

    EntityManager em;

    private Page<Transaction> page;

    static Specification<Transaction> afterDate(Date date) {
        if(date != null) {
            return (root, cq, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), date);
        }
        return (root, cq, cb) -> cb.and();
    }

    static Specification<Transaction> beforeDate(Date date) {
        if(date != null) {
            return (root, cq, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), date);
        }
        return (root, cq, cb) -> cb.and();
    }

    @Override
    public List<TransactionListDTO> getTransactions(TransactionListRequest request) {
        Pageable pageableRequest = PageRequest.of(request.getPage(), DEFAULT_LIMIT);
        List<TransactionListDTO> transactionListDTOS = new ArrayList<>();
        TransactionListDTO transactionListDTO = null;
        TransactionShortDTO transactionDTO = null;
        FxTransactionDTO fxTransactionDTO = null;
        IpnTransactionDTO ipnTransactionDTO = null;
        MerchantDTO merchantDTO = null;
        CustomerInfoShortDTO customerInfoDTO = null;
        AcquirerTransactionDTO acquirerTransactionDTO = null;

        this.page = repository.findAll(
            where(afterDate(request.getFromDate())).and(beforeDate(request.getEndDate())),
            pageableRequest
        );

        List<Transaction> transactions = this.page.getContent();
        for(Transaction transaction : transactions){
            transactionListDTO = new TransactionListDTO();
            fxTransactionDTO = new FxTransactionDTO();
            merchantDTO = new MerchantDTO();
            ipnTransactionDTO = new IpnTransactionDTO();
            customerInfoDTO = new CustomerInfoShortDTO();
            acquirerTransactionDTO = new AcquirerTransactionDTO();

            // Transaction
            transactionDTO = new TransactionShortDTO();
            transactionDTO.setId(transaction.getId());
            transactionDTO.setTransactionId(transaction.getTransactionId());
            transactionDTO.setReferenceNo(transaction.getReferenceNo());
            transactionDTO.setStatus(transaction.getStatus());
            transactionDTO.setOperation(transaction.getOperation());
            transactionDTO.setMessage(transaction.getMessage());
            transactionDTO.setCreatedAt(transaction.getCreatedAt());

            // Fx Transaction
            fxTransactionDTO.setId(transaction.getFxTransaction().getId());
            fxTransactionDTO.setOriginalAmount(transaction.getFxTransaction().getOriginalAmount());
            fxTransactionDTO.setOriginalCurrency(transaction.getFxTransaction().getOriginalCurrency());

            // Ipn Transaction
            ipnTransactionDTO.setId(transaction.getIpnTransaction().getId());
            ipnTransactionDTO.setIpnReceived(transaction.getIpnTransaction().getIpnReceived());
            ipnTransactionDTO.setIpnSent(transaction.getIpnTransaction().getIpnSent());

            // Merchant
            merchantDTO.setId(transaction.getMerchant().getId());
            merchantDTO.setName(transaction.getMerchant().getName());

            // Customer Info
            customerInfoDTO.setId(transaction.getCustomerInfo().getId());
            customerInfoDTO.setNumber(transaction.getCustomerInfo().getNumber());
            customerInfoDTO.setEmail(transaction.getCustomerInfo().getEmail());
            customerInfoDTO.setBillingFirstName(transaction.getCustomerInfo().getBillingFirstName());
            customerInfoDTO.setBillingLastName(transaction.getCustomerInfo().getBillingLastName());

            // Acquirer Transaction
            acquirerTransactionDTO.setId(transaction.getAcquirerTransaction().getId());
            acquirerTransactionDTO.setName(transaction.getAcquirerTransaction().getName());
            acquirerTransactionDTO.setCode(transaction.getAcquirerTransaction().getCode());
            acquirerTransactionDTO.setType(transaction.getAcquirerTransaction().getType());

            transactionListDTO.setTransaction(transactionDTO);
            transactionListDTO.setFx(fxTransactionDTO);
            transactionListDTO.setIpn(ipnTransactionDTO);
            transactionListDTO.setMerchant(merchantDTO);
            transactionListDTO.setCustomerInfo(customerInfoDTO);
            transactionListDTO.setAcquirer(acquirerTransactionDTO);
            transactionListDTO.setRefundable(transaction.getRefundable());

            transactionListDTOS.add(transactionListDTO);
        }
        return transactionListDTOS;
    }

    public Page getPage() {
        return page;
    }
}
