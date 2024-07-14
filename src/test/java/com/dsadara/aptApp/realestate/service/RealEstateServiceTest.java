package com.dsadara.aptApp.realestate.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class RealEstateServiceTest {

    @Autowired
    private RealEstateService realEstateService;

    @Test
    @DisplayName("성공-getApartmentByName()")
    void getApartmentByName_Success() {
//        //given
//        String aptName = "apt1";
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByName(aptName, pageable);
//
//        //then
//        assertEquals(aptName, aptPages.getContent().get(0).getName());
    }

    @Test
    @DisplayName("성공-getApartmentByAs1()")
    void getApartmentByAs1_Success() {
//        //given
//        String siDo = "**시";
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByAs1(siDo, pageable);
//
//        //then
//        assertEquals(siDo, aptPages.getContent().get(0).getAs1());
    }

    @Test
    @DisplayName("성공-getApartmentByAs2()")
    void getApartmentByAs2_Success() {
//        //given
//        String siGunGu = "**구";
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByAs2(siGunGu, pageable);
//
//        //then
//        assertEquals(siGunGu, aptPages.getContent().get(0).getAs2());
    }

    @Test
    @DisplayName("성공-getApartmentByAs3()")
    void getApartmentByAs3_Success() {
//        //given
//        String eupMyeon = "**읍";
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByAs3(eupMyeon, pageable);
//
//        //then
//        assertEquals(eupMyeon, aptPages.getContent().get(0).getAs3());
    }

    @Test
    @DisplayName("성공-getApartmentByAs4()")
    void getApartmentByAs4_Success() {
//        //given
//        String dongLee = "**동";
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> aptPages = realEstateService.getRealEstateByAs4(dongLee, pageable);
//
//        //then
//        assertEquals(dongLee, aptPages.getContent().get(0).getAs4());
    }

    @Test
    @DisplayName("성공-getApartmentDetail()")
    void getApartmentDetail_Success() {
//        //given
//        String aptCode = "aptcode1";
//
//        //when
//        RealEstateInfo realEstateInfo = realEstateService.getRealEstateDetail(aptCode);
//
//        //then
//        assertEquals(aptCode, realEstateInfo.getAptCode());
    }

    @Test
    @DisplayName("실패-getApartmentDetail()")
    void getApartmentDetail_Fail_ApartmentNotFound() {
//        //given
//        String wrongAptCode = "wrongcode";
//
//        //when
//        RealEstateException exception = assertThrows(RealEstateException.class,
//                () -> realEstateService.getRealEstateDetail(wrongAptCode));
//
//        //then
//        assertEquals(ErrorCode.APARTMENT_NOT_FOUND, exception.getErrorCode());
    }

}
