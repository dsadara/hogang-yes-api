package com.dsadara.aptApp.transaction.repository;

import com.dsadara.aptApp.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
