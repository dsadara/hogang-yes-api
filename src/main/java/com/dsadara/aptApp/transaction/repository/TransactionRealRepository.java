package com.dsadara.aptApp.transaction.repository;

import com.dsadara.aptApp.transaction.entity.TransactionReal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRealRepository extends JpaRepository<TransactionReal, Long> {
}
