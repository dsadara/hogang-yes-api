package com.dsadara.aptApp.realestate.service;

import com.dsadara.aptApp.realestate.dto.CreateRealEstate;
import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.exception.RealEstateException;
import com.dsadara.aptApp.realestate.repository.RealEstateRepository;
import com.dsadara.aptApp.realestate.type.RealEstateFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_ALREADY_EXIST;
import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_NOT_FOUND;


@RequiredArgsConstructor
@Service
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;

    @Transactional
    public RealEstateDto createRealEstate(CreateRealEstate.Request request) {
        validateCreateRealEstate(request);

        return RealEstateDto.fromEntity(
                realEstateRepository.save(
                        RealEstate.builder()
                                .aptCode(request.getAptCode())
                                .viewWeek(0)
                                .viewTotal(0)
                                .name(request.getName())
                                .as1(request.getAs1())
                                .as2(request.getAs2())
                                .as3(request.getAs3())
                                .as4(request.getAs4())
                                .drmAddress(request.getDrmAddress())
                                .apprvDate(request.getApprvDate())
                                .dongNo(request.getDongNo())
                                .houseNo(request.getHouseNo())
                                .parkingSpaceNo(request.getParkingSpaceNo())
                                .bjdCode(request.getBjdCode())
                                .feature(request.getFeature())
                                .build()
                )
        );
    }

    private void validateCreateRealEstate(CreateRealEstate.Request request) {
        if (realEstateRepository.existsByAptCode(request.getAptCode())) {
            throw new RealEstateException(APARTMENT_ALREADY_EXIST);
        }
        if (realEstateRepository.existsByName(request.getName())) {
            throw new RealEstateException(APARTMENT_ALREADY_EXIST);
        }
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByName(String name, Pageable pageable) {
        Page<RealEstate> apartment = realEstateRepository.findByName(name, pageable);
        return apartment.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByAs1(String as1, Pageable pageable) {
        Page<RealEstate> apartment = realEstateRepository.findByAs1(as1, pageable);
        return apartment.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByAs2(String as2, Pageable pageable) {
        Page<RealEstate> apartment = realEstateRepository.findByAs2(as2, pageable);
        return apartment.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByAs3(String as3, Pageable pageable) {
        Page<RealEstate> apartment = realEstateRepository.findByAs3(as3, pageable);
        return apartment.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByAs4(String as4, Pageable pageable) {
        Page<RealEstate> apartment = realEstateRepository.findByAs4(as4, pageable);
        return apartment.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public Page<RealEstateDto> getRealEstateByFeature(RealEstateFeature feature, Pageable pageable) {
        Page<RealEstate> apartment = realEstateRepository.findByFeature(feature, pageable);
        return apartment.map(RealEstateDto::fromEntity);
    }

    @Transactional
    public RealEstateInfo getRealEstateDetail(String aptCode) {
        return RealEstateInfo.fromEntity(
                realEstateRepository.findByAptCode(aptCode)
                        .orElseThrow(() -> new RealEstateException(APARTMENT_NOT_FOUND))
        );
    }
}
