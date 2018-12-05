package report.service.v3.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import report.service.v3.model.Transaction;

import java.util.Optional;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    @Query(value = "select t from Transaction t where t.transactionId = ?1")
    Optional<Transaction> findByTransactionId(String transactionId);
}
