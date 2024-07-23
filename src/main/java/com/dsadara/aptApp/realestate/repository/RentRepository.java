package com.dsadara.aptApp.realestate.repository;

import com.dsadara.aptApp.realestate.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
}
