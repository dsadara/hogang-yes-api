package com.dsadara.aptApp.realestate.repository;

import com.dsadara.aptApp.realestate.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
