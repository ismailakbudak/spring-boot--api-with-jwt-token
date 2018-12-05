package report.service.v3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import report.service.v3.dto.*;
import report.service.v3.exception.TransactionNotFoundException;
import report.service.v3.model.Transaction;
import report.service.v3.repository.TransactionRepository;
import report.service.v3.request.TransactionListRequest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class TransactionServiceImpl implements TransactionService {
    public static final Integer DEFAULT_LIMIT = 30;

    @Autowired
    private TransactionRepository repository;

    private Page<Transaction> page;

    static Specification<Transaction> afterDate(Date date) {
        if(date != null) {
            return (root, cq, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), date);
        }
        return (root, cq, cb) -> cb.and();
    }

    static Specification<Transaction> beforeDate(Date date) {
        return (root, cq, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), date);
    }

    static Specification<Transaction> fieldEql(String field, String fieldValue) {
        return (root, cq, cb) -> cb.like(root.get(field), fieldValue);
    }

    static Specification<Transaction> relationFieldEql(String field, Integer fieldValue) {
        return (root, cq, cb) -> cb.equal(root.get(field), fieldValue);
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

        Specification<Transaction> spec = handleSpecification(request);

        this.page = repository.findAll(spec, pageableRequest);

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

    private Specification<Transaction> handleSpecification(TransactionListRequest request) {
        Specification<Transaction> spec = where(afterDate(request.getFromDate()));

        if(request.getEndDate() != null)
            spec = spec.and(beforeDate(request.getEndDate()));

        if(request.getStatus() != null)
            spec = spec.and(fieldEql("status", request.getStatus()));

        if(request.getOperation() != null)
            spec = spec.and(fieldEql("operation", request.getOperation()));

        if(request.getErrorCode() != null)
            spec = spec.and(fieldEql("code", request.getErrorCode()));

        if(request.getMerchantId() != null)
            spec = spec.and(relationFieldEql("merchantId", request.getMerchantId()));

        if(request.getAcquirerId() != null)
            spec = spec.and(relationFieldEql("acquirerTransactionId", request.getAcquirerId()));

        if(request.getFilterField() != null)
            spec = spec.and(fieldEql(request.getFilterField(), request.getFilterValue()));

        return spec;
    }

    public Page getPage() {
        return page;
    }

    @Override
    public Transaction findTransactionByTransactionId(String transactionId) throws TransactionNotFoundException {
        Optional<Transaction> optionalTransaction = this.repository.findByTransactionId(transactionId);

        if(!optionalTransaction.isPresent()) {
            throw new TransactionNotFoundException();
        }

        return optionalTransaction.get();
    }
}
