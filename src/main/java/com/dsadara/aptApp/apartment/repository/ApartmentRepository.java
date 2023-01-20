package com.dsadara.aptApp.apartment.repository;

import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findByName(String name);

    List<Apartment> findByAs1(String as1);

    List<Apartment> findByAs2(String as2);

    List<Apartment> findByAs3(String as3);

    List<Apartment> findByAs4(String as4);

    @Query(value = "select * from apartment where JSON_CONTAINS(feature, JSON_ARRAY(:#{#paramFeature.name()}))", nativeQuery = true)
    List<Apartment> findByFeature(@Param("paramFeature") ApartmentFeature apartmentFeature);

    Optional<Apartment> findByAptCode(String aptCode);

    Boolean existsByName(String name);

    Boolean existsByAptCode(String aptCode);
}
