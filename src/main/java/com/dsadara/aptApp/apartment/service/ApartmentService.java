package com.dsadara.aptApp.apartment.service;

import com.dsadara.aptApp.apartment.dto.ApartmentDto;
import com.dsadara.aptApp.apartment.dto.CreateApartment;
import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.exception.ApartmentException;
import com.dsadara.aptApp.apartment.repository.ApartmentRepository;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ApartmentDto> getApartmentByName(String name) {
        List<Apartment> apartment = apartmentRepository.findByName(name);
        return apartment.stream()
                .map(ApartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ApartmentDto> getApartmentByAs1(String as1) {
        List<Apartment> apartment = apartmentRepository.findByAs1(as1);
        return apartment.stream()
                .map(ApartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ApartmentDto> getApartmentByAs2(String as2) {
        List<Apartment> apartment = apartmentRepository.findByAs2(as2);
        return apartment.stream()
                .map(ApartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ApartmentDto> getApartmentByAs3(String as3) {
        List<Apartment> apartment = apartmentRepository.findByAs3(as3);
        return apartment.stream()
                .map(ApartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ApartmentDto> getApartmentByAs4(String as4) {
        List<Apartment> apartment = apartmentRepository.findByAs4(as4);
        return apartment.stream()
                .map(ApartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ApartmentDto> getApartmentByFeature(ApartmentFeature feature) {
        List<Apartment> apartment = apartmentRepository.findByFeature(feature);
        return apartment.stream()
                .map(ApartmentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public Apartment getApartmentDetail(String aptCode) {
        return apartmentRepository.findByAptCode(aptCode)
                .orElseThrow(() -> new ApartmentException(APARTMENT_NOT_FOUND));
    }
}
