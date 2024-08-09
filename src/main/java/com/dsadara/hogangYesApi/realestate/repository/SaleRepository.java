package com.dsadara.hogangYesApi.realestate.repository;

import com.dsadara.hogangYesApi.realestate.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
