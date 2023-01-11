package com.dsadara.aptApp.transaction.repository;

import com.dsadara.aptApp.transaction.entity.RequestTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTransactionRepository extends JpaRepository<RequestTransaction, Long> {
}
