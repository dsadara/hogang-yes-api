package com.dsadara.aptApp.realestate.repository;

import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.type.RealEstateFeature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    Page<RealEstate> findByName(String name, Pageable pageable);

    Page<RealEstate> findByAs1(String as1, Pageable pageable);

    Page<RealEstate> findByAs2(String as2, Pageable pageable);

    Page<RealEstate> findByAs3(String as3, Pageable pageable);

    Page<RealEstate> findByAs4(String as4, Pageable pageable);

    @Query(value = "select * from real_estate where JSON_CONTAINS(feature, JSON_ARRAY(:#{#paramFeature.name()}))", nativeQuery = true)
    Page<RealEstate> findByFeature(@Param("paramFeature") RealEstateFeature realEstateFeature, Pageable pageable);

    Optional<RealEstate> findByAptCode(String aptCode);

    Boolean existsByName(String name);

    Boolean existsByAptCode(String aptCode);
}
