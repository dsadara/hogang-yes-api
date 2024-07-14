package com.dsadara.aptApp.realestate.controller;

import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.exception.RealEstateException;
import com.dsadara.aptApp.realestate.service.RealEstateService;
import com.dsadara.aptApp.realestate.type.RealEstateFeature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_NOT_FOUND;
import static com.dsadara.aptApp.realestate.type.RealEstateFeature.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RealEstateController.class)
public class RealEstateControllerTest {

    @MockBean
    private RealEstateService realEstateService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("성공-아파트 이름 검색")
    void getApartmentByName_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Collections.singletonList(
                        RealEstateDto.builder()
                                .aptCode("sampleCode")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByName(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/apt?searchKey=name&searchValue=아파트1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].aptCode").value("sampleCode"))
                .andExpect(jsonPath("$.content[0].name").value("아파트1"))
                .andExpect(jsonPath("$.content[0].as1").value("**시"))
                .andExpect(jsonPath("$.content[0].as2").value("**구"))
                .andExpect(jsonPath("$.content[0].as3").value("**읍"))
                .andExpect(jsonPath("$.content[0].as4").value("**동"))
                .andExpect(jsonPath("$.content[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$.content[0].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("성공-아파트 시,도별 검색")
    void getApartmentByAs1_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build(),
                        RealEstateDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(COUPANG_ROCKET, NEAR_STATION))
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByAs1(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/apt?searchKey=as1&searchValue=**시"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$.content[0].name").value("아파트1"))
                .andExpect(jsonPath("$.content[0].as1").value("**시"))
                .andExpect(jsonPath("$.content[0].as2").value("**구"))
                .andExpect(jsonPath("$.content[0].as3").value("**읍"))
                .andExpect(jsonPath("$.content[0].as4").value("**동"))
                .andExpect(jsonPath("$.content[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$.content[0].feature[1]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$.content[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$.content[1].name").value("아파트2"))
                .andExpect(jsonPath("$.content[1].as1").value("**시"))
                .andExpect(jsonPath("$.content[1].as2").value("**구"))
                .andExpect(jsonPath("$.content[1].as3").value("**읍"))
                .andExpect(jsonPath("$.content[1].as4").value("**동"))
                .andExpect(jsonPath("$.content[1].feature[0]").value(COUPANG_ROCKET.name()))
                .andExpect(jsonPath("$.content[1].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("성공-아파트 시,군,구별 검색")
    void getApartmentByAs2_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build(),
                        RealEstateDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(COUPANG_ROCKET, NEAR_STATION))
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByAs2(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/apt?searchKey=as2&searchValue=**구"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$.content[0].name").value("아파트1"))
                .andExpect(jsonPath("$.content[0].as1").value("**시"))
                .andExpect(jsonPath("$.content[0].as2").value("**구"))
                .andExpect(jsonPath("$.content[0].as3").value("**읍"))
                .andExpect(jsonPath("$.content[0].as4").value("**동"))
                .andExpect(jsonPath("$.content[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$.content[0].feature[1]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$.content[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$.content[1].name").value("아파트2"))
                .andExpect(jsonPath("$.content[1].as1").value("**시"))
                .andExpect(jsonPath("$.content[1].as2").value("**구"))
                .andExpect(jsonPath("$.content[1].as3").value("**읍"))
                .andExpect(jsonPath("$.content[1].as4").value("**동"))
                .andExpect(jsonPath("$.content[1].feature[0]").value(COUPANG_ROCKET.name()))
                .andExpect(jsonPath("$.content[1].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("성공-아파트 읍,면별 검색")
    void getApartmentByAs3_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build(),
                        RealEstateDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(COUPANG_ROCKET, NEAR_STATION))
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByAs3(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/apt?searchKey=as3&searchValue=**읍"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$.content[0].name").value("아파트1"))
                .andExpect(jsonPath("$.content[0].as1").value("**시"))
                .andExpect(jsonPath("$.content[0].as2").value("**구"))
                .andExpect(jsonPath("$.content[0].as3").value("**읍"))
                .andExpect(jsonPath("$.content[0].as4").value("**동"))
                .andExpect(jsonPath("$.content[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$.content[0].feature[1]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$.content[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$.content[1].name").value("아파트2"))
                .andExpect(jsonPath("$.content[1].as1").value("**시"))
                .andExpect(jsonPath("$.content[1].as2").value("**구"))
                .andExpect(jsonPath("$.content[1].as3").value("**읍"))
                .andExpect(jsonPath("$.content[1].as4").value("**동"))
                .andExpect(jsonPath("$.content[1].feature[0]").value(COUPANG_ROCKET.name()))
                .andExpect(jsonPath("$.content[1].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("성공-아파트 동,리별 검색")
    void getApartmentByAs4_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build(),
                        RealEstateDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(COUPANG_ROCKET, NEAR_STATION))
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByAs4(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/apt?searchKey=as4&searchValue=**동"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$.content[0].name").value("아파트1"))
                .andExpect(jsonPath("$.content[0].as1").value("**시"))
                .andExpect(jsonPath("$.content[0].as2").value("**구"))
                .andExpect(jsonPath("$.content[0].as3").value("**읍"))
                .andExpect(jsonPath("$.content[0].as4").value("**동"))
                .andExpect(jsonPath("$.content[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$.content[0].feature[1]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$.content[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$.content[1].name").value("아파트2"))
                .andExpect(jsonPath("$.content[1].as1").value("**시"))
                .andExpect(jsonPath("$.content[1].as2").value("**구"))
                .andExpect(jsonPath("$.content[1].as3").value("**읍"))
                .andExpect(jsonPath("$.content[1].as4").value("**동"))
                .andExpect(jsonPath("$.content[1].feature[0]").value(COUPANG_ROCKET.name()))
                .andExpect(jsonPath("$.content[1].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("성공-아파트 특징별 검색")
    void getApartmentByFeature_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(NEAR_STATION, GOOD_SCHOOL))
                                .build(),
                        RealEstateDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(NEAR_STATION, COUPANG_ROCKET))
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByFeature(any(RealEstateFeature.class), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/apt?searchKey=feature&searchValue=NEAR_STATION"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$.content[0].name").value("아파트1"))
                .andExpect(jsonPath("$.content[0].as1").value("**시"))
                .andExpect(jsonPath("$.content[0].as2").value("**구"))
                .andExpect(jsonPath("$.content[0].as3").value("**읍"))
                .andExpect(jsonPath("$.content[0].as4").value("**동"))
                .andExpect(jsonPath("$.content[0].feature[0]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$.content[0].feature[1]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$.content[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$.content[1].name").value("아파트2"))
                .andExpect(jsonPath("$.content[1].as1").value("**시"))
                .andExpect(jsonPath("$.content[1].as2").value("**구"))
                .andExpect(jsonPath("$.content[1].as3").value("**읍"))
                .andExpect(jsonPath("$.content[1].as4").value("**동"))
                .andExpect(jsonPath("$.content[1].feature[0]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$.content[1].feature[1]").value(COUPANG_ROCKET.name()));
    }

    @Test
    @DisplayName("성공-아파트 상세 조회")
    void getApartmentDetail_Success() throws Exception {
        //given
        given(realEstateService.getRealEstateDetail(anyString()))
                .willReturn(RealEstateInfo.builder()
                        .aptCode("sampleCode")
                        .name("아파트1")
                        .as1("**시")
                        .as2("**구")
                        .as3("**읍")
                        .as4("**동")
                        .drmAddress("도로명주소1")
                        .apprvDate(LocalDate.of(2001, 1, 1))
                        .dongNo(10)
                        .houseNo(500)
                        .parkingSpaceNo(1000)
                        .bjdCode("sampleBjdCode")
                        .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                        .build());
        //when
        //then
        mockMvc.perform(get("/apt/detail?aptCode=sampleCode"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aptCode").value("sampleCode"))
                .andExpect(jsonPath("$.name").value("아파트1"))
                .andExpect(jsonPath("$.as1").value("**시"))
                .andExpect(jsonPath("$.as2").value("**구"))
                .andExpect(jsonPath("$.as3").value("**읍"))
                .andExpect(jsonPath("$.as4").value("**동"))
                .andExpect(jsonPath("$.drmAddress").value("도로명주소1"))
                .andExpect(jsonPath("$.apprvDate").value("2001-01-01"))
                .andExpect(jsonPath("$.dongNo").value(10))
                .andExpect(jsonPath("$.houseNo").value(500))
                .andExpect(jsonPath("$.parkingSpaceNo").value(1000))
                .andExpect(jsonPath("$.bjdCode").value("sampleBjdCode"))
                .andExpect(jsonPath("$.feature").isArray())
                .andExpect(jsonPath("$.feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$.feature[1]").value(NEAR_STATION.name()))
                .andDo(print());
    }

    @Test
    @DisplayName("실패-아파트 상세 조회-존재하지 않는 아파트")
    void getApartmentDetail_Fail() throws Exception {
        //given
        given(realEstateService.getRealEstateDetail(anyString()))
                .willThrow(new RealEstateException(APARTMENT_NOT_FOUND));

        //when
        //then
        mockMvc.perform(get("/apt/detail?aptCode=sampleCode"))
                .andDo(print())
                .andExpect(jsonPath("$.errorCode")
                        .value("APARTMENT_NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("아파트가 없습니다"))
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("실패-아파트 특징 검색-존재하지 않는 특징")
    void getApartmentByFeature_Fail() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/apt?searchKey=feature&searchValue=FEATURE_NOT_EXIST"))
                .andDo(print())
                .andExpect(jsonPath("$.errorCode")
                        .value("INVALID_REQUEST"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("잘못된 요청입니다."))
                .andExpect(status().isOk());
    }

}
