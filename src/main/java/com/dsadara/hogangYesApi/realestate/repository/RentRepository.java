package com.dsadara.hogangYesApi.realestate.repository;

import com.dsadara.hogangYesApi.realestate.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
}
