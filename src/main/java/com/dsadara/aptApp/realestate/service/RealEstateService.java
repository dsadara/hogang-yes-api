package com.dsadara.aptApp.realestate.service;

import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.repository.RealEstateRepository;
import com.dsadara.aptApp.realestate.type.RealEstateType;
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
        Page<RealEstate> realEstatePage = realEstateRepository.findByName(name, pageable);
        return realEstatePage.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByBeopJeongDongCode(String beopJeongDongCode, Pageable pageable) {
        Page<RealEstate> realEstatePage = realEstateRepository.findBybeopJeongDongCode(beopJeongDongCode, pageable);
        return realEstatePage.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByBeopJeongDong(String beopJeongDong, Pageable pageable) {
        Page<RealEstate> realEstatePage = realEstateRepository.findByBeopJeongDong(beopJeongDong, pageable);
        return realEstatePage.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByParcelNumber(String parcelNumber, Pageable pageable) {
        Page<RealEstate> realEstatePage = realEstateRepository.findByParcelNumber(parcelNumber, pageable);
        return realEstatePage.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByRealEstateType(RealEstateType realEstateType, Pageable pageable) {
        Page<RealEstate> realEstatePage = realEstateRepository.findByRealEstateType(realEstateType, pageable);
        return realEstatePage.map(RealEstateDto::fromEntity);
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
