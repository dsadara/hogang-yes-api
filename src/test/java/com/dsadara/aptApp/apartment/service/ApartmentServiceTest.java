package com.dsadara.aptApp.apartment.service;

import com.dsadara.aptApp.apartment.dto.ApartmentDto;
import com.dsadara.aptApp.apartment.dto.CreateApartment;
import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.exception.ApartmentException;
import com.dsadara.aptApp.common.type.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dsadara.aptApp.apartment.type.ApartmentFeature.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class ApartmentServiceTest {
    @Autowired
    private ApartmentService apartmentService;

    @BeforeEach
    void init() {
        apartmentService.createApartment(CreateApartment.Request.builder()
                .aptCode("aptcode1")
                .name("apt1")
                .as1("**시").as2("**구").as3("**읍").as4("**동")
                .drmAddress("도로명주소1")
                .apprvDate(LocalDate.of(2001, 1, 1))
                .dongNo(10)
                .houseNo(1000)
                .parkingSpaceNo(2000)
                .bjdCode("bjdcode1")
                .feature(new ArrayList<>(Arrays.asList(NEAR_STATION, GOOD_SCHOOL)))
                .build());

        apartmentService.createApartment(CreateApartment.Request.builder()
                .aptCode("aptcode2")
                .name("apt2")
                .as1("##시").as2("**구").as3("##읍").as4("**동")
                .drmAddress("도로명주소2")
                .apprvDate(LocalDate.of(2002, 2, 2))
                .dongNo(20)
                .houseNo(2000)
                .parkingSpaceNo(4000)
                .bjdCode("bjdcode2")
                .feature(new ArrayList<>(Arrays.asList(NEAR_STATION, NEAR_SUPERMARKET, COUPANG_ROCKET))).build());
    }

    @Test
    @DisplayName("아파트 검색 (단지명)")
    void getApartmentByNameSuccess() {
        //given
        //when
        List<ApartmentDto> apts = apartmentService.getApartmentByName("apt1");

        //then
        for (ApartmentDto apartmentDto : apts) {
            assertEquals(apartmentDto.getName(), "apt1");
        }
    }

    @Test
    @DisplayName("아파트 검색 (시, 도)")
    void getApartmentByAs1Success() {
        //given
        //when
        List<ApartmentDto> apts = apartmentService.getApartmentByAs1("**시");

        //then
        for (ApartmentDto apartmentDto : apts) {
            assertEquals(apartmentDto.getAs1(), "**시");
            assertEquals(apartmentDto.getName(), "apt1");
        }
    }

    @Test
    @DisplayName("아파트 검색 (시, 군, 구)")
    void getApartmentByAs2Success() {
        //given
        //when
        List<ApartmentDto> apts = apartmentService.getApartmentByAs2("**구");

        //then
        for (ApartmentDto apartmentDto : apts) {
            assertEquals(apartmentDto.getAs2(), "**구");
        }
    }

    @Test
    @DisplayName("아파트 검색 (읍, 면)")
    void getApartmentByAs3Success() {
        //given
        //when
        List<ApartmentDto> apts = apartmentService.getApartmentByAs3("**읍");

        //then
        for (ApartmentDto apartmentDto : apts) {
            assertEquals(apartmentDto.getAs3(), "**읍");
            assertEquals(apartmentDto.getName(), "apt1");
        }
    }

    @Test
    @DisplayName("아파트 검색 (동, 리)")
    void getApartmentByAs4Success() {
        //given
        //when
        List<ApartmentDto> apts = apartmentService.getApartmentByAs4("**동");

        //then
        for (ApartmentDto apartmentDto : apts) {
            assertEquals(apartmentDto.getAs4(), "**동");
        }
    }

    @Test
    @DisplayName("아파트 상세 정보 조회")
    void getApartmentDetailSuccess() {
        //given
        //when
        Apartment apartment = apartmentService.getApartmentDetail("aptcode1");

        //then
        assertEquals(apartment.getName(), "apt1");
        assertEquals(apartment.getDrmAddress(), "도로명주소1");
    }

    @Test
    @DisplayName("아파트 상세 정보 조회 실패")
    void getApartmentDetailFailed_ApartmentNotFound() {
        //given
        //when
        ApartmentException exception = assertThrows(ApartmentException.class,
                () -> apartmentService.getApartmentDetail("wrongcode"));

        //then
        assertEquals(ErrorCode.APARTMENT_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("아파트 저장 실패")
    void createApartmentFailed_ApartmentAlreadyExist() {
        //given
        CreateApartment.Request request = CreateApartment.Request.builder()
                .aptCode("aptcode1")
                .name("apt1")
                .as1("**시").as2("**구").as3("**읍").as4("**동")
                .drmAddress("도로명주소1")
                .apprvDate(LocalDate.of(2001, 1, 1))
                .dongNo(10)
                .houseNo(1000)
                .parkingSpaceNo(2000)
                .bjdCode("bjdcode1")
                .feature(new ArrayList<>(Arrays.asList(NEAR_STATION, GOOD_SCHOOL)))
                .build();
        //when
        ApartmentException exception = assertThrows(ApartmentException.class,
                () -> apartmentService.createApartment(request));

        //then
        assertEquals(ErrorCode.APARTMENT_ALREADY_EXIST, exception.getErrorCode());
    }
}
