package report.service.v3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import report.service.v3.dto.TransactionReport;
import report.service.v3.model.Transaction;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionReportRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    @Query(value = "select new report.service.v3.dto.TransactionReport(t.currency, count(t) as count, sum(t.amount) as total) from Transaction t where t.createdAt > ?1 AND t.createdAt < ?2 group by t.currency")
    List<TransactionReport> findTransactionCount(Date fromDate, Date endDate);

    @Query(value = "select new report.service.v3.dto.TransactionReport(t.currency, count(t) as count, sum(t.amount) as total) from Transaction t where t.createdAt > ?1 AND t.createdAt < ?2 AND t.merchantId = ?3 group by t.currency")
    List<TransactionReport> findTransactionWithMerchantCount(Date fromDate, Date endDate, Integer merchantId);

    @Query(value = "select new report.service.v3.dto.TransactionReport(t.currency, count(t) as count, sum(t.amount) as total) from Transaction t where t.createdAt > ?1 AND t.createdAt < ?2 AND t.acquirerTransactionId = ?3 group by t.currency")
    List<TransactionReport> findTransactionWithAcquirerCount(Date fromDate, Date endDate, Integer acquirerTransactionId);

    @Query(value = "select new report.service.v3.dto.TransactionReport(t.currency, count(t) as count, sum(t.amount) as total) from Transaction t where t.createdAt > ?1 AND t.createdAt < ?2  AND t.merchantId = ?3 AND t.acquirerTransactionId = ?4 group by t.currency")
    List<TransactionReport> findTransactionWithMerchantAndAcquirerCount(Date fromDate, Date endDate, Integer merchantId, Integer acquirerTransactionId);
}
