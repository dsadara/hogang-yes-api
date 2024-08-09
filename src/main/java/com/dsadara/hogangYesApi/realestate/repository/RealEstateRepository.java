package com.dsadara.hogangYesApi.realestate.repository;

import com.dsadara.hogangYesApi.realestate.entity.RealEstate;
import com.dsadara.hogangYesApi.realestate.type.RealEstateType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    Page<RealEstate> findByName(String name, Pageable pageable);

    Page<RealEstate> findBybeopJeongDongCode(Integer beopJeongDongCode, Pageable pageable);

    Page<RealEstate> findByBeopJeongDong(String beopJeongDong, Pageable pageable);

    Page<RealEstate> findByParcelNumber(String parcelNumber, Pageable pageable);

    Page<RealEstate> findByRealEstateType(RealEstateType realEstateType, Pageable pageable);

    Optional<RealEstate> findById(Integer id);

    Boolean existsByName(String name);

    Boolean existsById(Integer id);
}
