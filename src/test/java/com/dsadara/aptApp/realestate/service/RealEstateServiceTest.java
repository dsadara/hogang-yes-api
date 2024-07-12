package com.dsadara.aptApp.realestate.service;

import com.dsadara.aptApp.common.type.ErrorCode;
import com.dsadara.aptApp.realestate.dto.CreateRealEstate;
import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.exception.RealEstateException;
import com.dsadara.aptApp.realestate.repository.RealEstateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static com.dsadara.aptApp.realestate.type.RealEstateFeature.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class RealEstateServiceTest {
    @Autowired
    private RealEstateService realEstateService;

    @Autowired
    private RealEstateRepository realEstateRepository;

    @BeforeEach
    void beforeEach() {
        realEstateRepository.deleteAll();

        realEstateService.createRealEstate(CreateRealEstate.Request.builder()
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

        realEstateService.createRealEstate(CreateRealEstate.Request.builder()
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
    @DisplayName("성공-getApartmentByName()")
    void getApartmentByName_Success() {
        //given
        String aptName = "apt1";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByName(aptName, pageable);

        //then
        assertEquals(aptName, aptPages.getContent().get(0).getName());
    }

    @Test
    @DisplayName("성공-getApartmentByAs1()")
    void getApartmentByAs1_Success() {
        //given
        String siDo = "**시";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByAs1(siDo, pageable);

        //then
        assertEquals(siDo, aptPages.getContent().get(0).getAs1());
    }

    @Test
    @DisplayName("성공-getApartmentByAs2()")
    void getApartmentByAs2_Success() {
        //given
        String siGunGu = "**구";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByAs2(siGunGu, pageable);

        //then
        assertEquals(siGunGu, aptPages.getContent().get(0).getAs2());
    }

    @Test
    @DisplayName("성공-getApartmentByAs3()")
    void getApartmentByAs3_Success() {
        //given
        String eupMyeon = "**읍";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByAs3(eupMyeon, pageable);

        //then
        assertEquals(eupMyeon, aptPages.getContent().get(0).getAs3());
    }

    @Test
    @DisplayName("성공-getApartmentByAs4()")
    void getApartmentByAs4_Success() {
        //given
        String dongLee = "**동";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByAs4(dongLee, pageable);

        //then
        assertEquals(dongLee, aptPages.getContent().get(0).getAs4());
    }

    @Test
    @DisplayName("성공-getApartmentDetail()")
    void getApartmentDetail_Success() {
        //given
        String aptCode = "aptcode1";

        //when
        RealEstateInfo realEstateInfo = realEstateService.getRealEstateDetail(aptCode);

        //then
        assertEquals(aptCode, realEstateInfo.getAptCode());
    }

    @Test
    @DisplayName("실패-getApartmentDetail()")
    void getApartmentDetail_Fail_ApartmentNotFound() {
        //given
        String wrongAptCode = "wrongcode";

        //when
        RealEstateException exception = assertThrows(RealEstateException.class,
                () -> realEstateService.getRealEstateDetail(wrongAptCode));

        //then
        assertEquals(ErrorCode.APARTMENT_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("실패-createApartment()")
    void createApartment_Fail_ApartmentAlreadyExist() {
        //given
        CreateRealEstate.Request request = CreateRealEstate.Request.builder()
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
        RealEstateException exception = assertThrows(RealEstateException.class,
                () -> realEstateService.createRealEstate(request));

        //then
        assertEquals(ErrorCode.APARTMENT_ALREADY_EXIST, exception.getErrorCode());
    }

    @Test
    @DisplayName("테스트시 data.sql 데이터 삭제했는지 확인")
    void check_IfInitialData_Deleted() {
//        // given
//        String aptCodeFromInitialData = "아파트코드1";
//
//        // when
//        Optional<RealEstate> apartmentOptional = realEstateRepository.findByAptCode(aptCodeFromInitialData);
//
//        // then
//        assertFalse(apartmentOptional.isPresent());
    }
}