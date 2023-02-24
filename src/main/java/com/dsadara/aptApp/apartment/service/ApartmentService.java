package com.dsadara.aptApp.apartment.service;

import com.dsadara.aptApp.apartment.dto.ApartmentDto;
import com.dsadara.aptApp.apartment.dto.ApartmentInfo;
import com.dsadara.aptApp.apartment.dto.CreateApartment;
import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.exception.ApartmentException;
import com.dsadara.aptApp.apartment.repository.ApartmentRepository;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_ALREADY_EXIST;
import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_NOT_FOUND;


@RequiredArgsConstructor
@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Transactional
    public ApartmentDto createApartment(CreateApartment.Request request) {
        validateCreateApartment(request);

        return ApartmentDto.fromEntity(
                apartmentRepository.save(
                        Apartment.builder()
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

    private void validateCreateApartment(CreateApartment.Request request) {
        if (apartmentRepository.existsByAptCode(request.getAptCode())) {
            throw new ApartmentException(APARTMENT_ALREADY_EXIST);
        }
        if (apartmentRepository.existsByName(request.getName())) {
            throw new ApartmentException(APARTMENT_ALREADY_EXIST);
        }
    }

    @Transactional
    public Page<ApartmentDto> getApartmentByName(String name, Pageable pageable) {
        Page<Apartment> apartment = apartmentRepository.findByName(name, pageable);
        return apartment.map(ApartmentDto::fromEntity);
    }

    @Transactional
    public Page<ApartmentDto> getApartmentByAs1(String as1, Pageable pageable) {
        Page<Apartment> apartment = apartmentRepository.findByAs1(as1, pageable);
        return apartment.map(ApartmentDto::fromEntity);
    }

    @Transactional
    public Page<ApartmentDto> getApartmentByAs2(String as2, Pageable pageable) {
        Page<Apartment> apartment = apartmentRepository.findByAs2(as2, pageable);
        return apartment.map(ApartmentDto::fromEntity);
    }

    @Transactional
    public Page<ApartmentDto> getApartmentByAs3(String as3, Pageable pageable) {
        Page<Apartment> apartment = apartmentRepository.findByAs3(as3, pageable);
        return apartment.map(ApartmentDto::fromEntity);
    }

    @Transactional
    public Page<ApartmentDto> getApartmentByAs4(String as4, Pageable pageable) {
        Page<Apartment> apartment = apartmentRepository.findByAs4(as4, pageable);
        return apartment.map(ApartmentDto::fromEntity);
    }

    @Transactional
    public Page<ApartmentDto> getApartmentByFeature(ApartmentFeature feature, Pageable pageable) {
        Page<Apartment> apartment = apartmentRepository.findByFeature(feature, pageable);
        return apartment.map(ApartmentDto::fromEntity);
    }

    @Transactional
    public ApartmentInfo getApartmentDetail(String aptCode) {
        return ApartmentInfo.fromEntity(
                apartmentRepository.findByAptCode(aptCode)
                        .orElseThrow(() -> new ApartmentException(APARTMENT_NOT_FOUND))
        );
    }
}
