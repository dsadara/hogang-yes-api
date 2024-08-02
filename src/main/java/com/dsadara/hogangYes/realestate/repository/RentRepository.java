package com.dsadara.hogangYes.realestate.repository;

import com.dsadara.hogangYes.realestate.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
}
