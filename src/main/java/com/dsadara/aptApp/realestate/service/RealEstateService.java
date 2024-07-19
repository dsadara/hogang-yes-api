package com.dsadara.aptApp.realestate.service;

import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.dto.RentInfo;
import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.exception.RealEstateException;
import com.dsadara.aptApp.realestate.repository.RealEstateRepository;
import com.dsadara.aptApp.realestate.repository.RentRepository;
import com.dsadara.aptApp.realestate.type.RealEstateType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dsadara.aptApp.common.type.ErrorCode.REAL_ESTATE_NOT_FOUND;
import static com.dsadara.aptApp.common.type.ErrorCode.RENT_NOT_FOUND;


@RequiredArgsConstructor
@Service
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;
    private final RentRepository rentRepository;

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
    public RealEstateInfo getRealEstateDetail(String id) {
        return RealEstateInfo.fromEntity(
                realEstateRepository.findById(Integer.valueOf(id))
                        .orElseThrow(() -> new RealEstateException(REAL_ESTATE_NOT_FOUND))
        );
    }

    @Transactional
    public RentInfo getRentDetail(String id) {
        return RentInfo.fromEntity(
                rentRepository.findById(Integer.valueOf(id))
                        .orElseThrow(() -> new RealEstateException(RENT_NOT_FOUND))
        );
    }
}