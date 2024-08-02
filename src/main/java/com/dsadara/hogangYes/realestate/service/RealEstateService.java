package com.dsadara.hogangYes.realestate.service;

import com.dsadara.hogangYes.realestate.dto.RealEstateDto;
import com.dsadara.hogangYes.realestate.dto.RealEstateInfo;
import com.dsadara.hogangYes.realestate.dto.RentInfo;
import com.dsadara.hogangYes.realestate.dto.SaleInfo;
import com.dsadara.hogangYes.realestate.entity.RealEstate;
import com.dsadara.hogangYes.realestate.exception.RealEstateException;
import com.dsadara.hogangYes.realestate.repository.RealEstateRepository;
import com.dsadara.hogangYes.realestate.repository.RentRepository;
import com.dsadara.hogangYes.realestate.repository.SaleRepository;
import com.dsadara.hogangYes.realestate.type.RealEstateType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dsadara.hogangYes.common.type.ErrorCode.*;


@RequiredArgsConstructor
@Service
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;
    private final RentRepository rentRepository;
    private final SaleRepository saleRepository;

    @Transactional
    public Page<RealEstateDto> getRealEstateByName(String name, Pageable pageable) {
        Page<RealEstate> realEstatePage = realEstateRepository.findByName(name, pageable);
        return realEstatePage.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByBeopJeongDongCode(String beopJeongDongCode, Pageable pageable) {
        Page<RealEstate> realEstatePage = realEstateRepository.findBybeopJeongDongCode(Integer.valueOf(beopJeongDongCode), pageable);
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

    public SaleInfo getSaleDetail(String id) {
        return SaleInfo.fromEntity(
                saleRepository.findById(Integer.valueOf(id))
                        .orElseThrow(() -> new RealEstateException(SALE_NOT_FOUND))
        );
    }
}