package com.dsadara.hogangYes.realestate.repository;

import com.dsadara.hogangYes.realestate.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
