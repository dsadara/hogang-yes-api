package com.dsadara.aptApp.apartment.repository;

import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Page<Apartment> findByName(String name, Pageable pageable);

    Page<Apartment> findByAs1(String as1, Pageable pageable);

    Page<Apartment> findByAs2(String as2, Pageable pageable);

    Page<Apartment> findByAs3(String as3, Pageable pageable);

    Page<Apartment> findByAs4(String as4, Pageable pageable);

    @Query(value = "select * from apartment where JSON_CONTAINS(feature, JSON_ARRAY(:#{#paramFeature.name()}))", nativeQuery = true)
    Page<Apartment> findByFeature(@Param("paramFeature") ApartmentFeature apartmentFeature, Pageable pageable);

    Optional<Apartment> findByAptCode(String aptCode);

    Boolean existsByName(String name);

    Boolean existsByAptCode(String aptCode);
}
