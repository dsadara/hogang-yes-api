package com.dsadara.aptApp.realestate.service;

import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.repository.RealEstateRepository;
import com.dsadara.aptApp.realestate.type.RealEstateFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public Page<RealEstateDto> getRealEstateByName(String name, Pageable pageable) {
        Page<RealEstate> apartment = realEstateRepository.findByName(name, pageable);
//        return apartment.map(RealEstateDto::fromEntity);
        return null;
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByAs1(String as1, Pageable pageable) {
//        Page<RealEstate> apartment = realEstateRepository.findByAs1(as1, pageable);
//        return apartment.map(RealEstateDto::fromEntity);
        return null;
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByAs2(String as2, Pageable pageable) {
//        Page<RealEstate> apartment = realEstateRepository.findByAs2(as2, pageable);
//        return apartment.map(RealEstateDto::fromEntity);
        return null;
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByAs3(String as3, Pageable pageable) {
//        Page<RealEstate> apartment = realEstateRepository.findByAs3(as3, pageable);
//        return apartment.map(RealEstateDto::fromEntity);
        return null;
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByAs4(String as4, Pageable pageable) {
//        Page<RealEstate> apartment = realEstateRepository.findByAs4(as4, pageable);
//        return apartment.map(RealEstateDto::fromEntity);
        return null;
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByFeature(RealEstateFeature feature, Pageable pageable) {
//        Page<RealEstate> apartment = realEstateRepository.findByFeature(feature, pageable);
//        return apartment.map(RealEstateDto::fromEntity);
        return null;
    }

    @Transactional
    public RealEstateInfo getRealEstateDetail(String aptCode) {
//        return RealEstateInfo.fromEntity(
//                realEstateRepository.findByAptCode(aptCode)
//                        .orElseThrow(() -> new RealEstateException(APARTMENT_NOT_FOUND))
//        );
        return null;
    }
}
