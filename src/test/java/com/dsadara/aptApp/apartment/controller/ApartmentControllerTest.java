package com.dsadara.aptApp.apartment.controller;

import com.dsadara.aptApp.apartment.dto.ApartmentDto;
import com.dsadara.aptApp.apartment.dto.ApartmentInfo;
import com.dsadara.aptApp.apartment.dto.CreateApartment;
import com.dsadara.aptApp.apartment.exception.ApartmentException;
import com.dsadara.aptApp.apartment.service.ApartmentService;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.dsadara.aptApp.apartment.type.ApartmentFeature.*;
import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_ALREADY_EXIST;
import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_NOT_FOUND;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApartmentController.class)
public class ApartmentControllerTest {

    @MockBean
    private ApartmentService apartmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("아파트 저장 성공")
    void successCreateApartment() throws Exception {
        //given
        given(apartmentService.createApartment(any(CreateApartment.Request.class)))
                .willReturn(ApartmentDto.builder()
                        .aptCode("sampleCode")
                        .name("아파트1")
                        .as1("**시")
                        .as2("**구")
                        .as3("**읍")
                        .as4("**동")
                        .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                        .build());

        //when
        //then
        mockMvc.perform(post("/apt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                CreateApartment.Request.builder()
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
                                        .build()
                        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aptCode").value("sampleCode"))
                .andExpect(jsonPath("$.name").value("아파트1"))
                .andExpect(jsonPath("$.as1").value("**시"))
                .andExpect(jsonPath("$.as2").value("**구"))
                .andExpect(jsonPath("$.as3").value("**읍"))
                .andExpect(jsonPath("$.as4").value("**동"))
                .andExpect(jsonPath("$.feature").isArray())
                .andExpect(jsonPath("$.feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$.feature[1]").value(NEAR_STATION.name()))
                .andDo(print());
    }

    @Test
    @DisplayName("아파트 이름 검색 성공")
    void successGetApartmentByName() throws Exception {
        //given
        List<ApartmentDto> apartmentDtos =
                Collections.singletonList(
                        ApartmentDto.builder()
                                .aptCode("sampleCode")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build()
                );
        given(apartmentService.getApartmentByName(anyString()))
                .willReturn(apartmentDtos);

        //when
        //then
        mockMvc.perform(get("/apt?name=아파트1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aptCode").value("sampleCode"))
                .andExpect(jsonPath("$[0].as1").value("**시"))
                .andExpect(jsonPath("$[0].as2").value("**구"))
                .andExpect(jsonPath("$[0].as3").value("**읍"))
                .andExpect(jsonPath("$[0].as4").value("**동"))
                .andExpect(jsonPath("$[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$[0].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("아파트 시도별 검색 성공")
    void successGetApartmentByAs1() throws Exception {
        //given
        List<ApartmentDto> apartmentDtos =
                Arrays.asList(
                        ApartmentDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build(),
                        ApartmentDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(COUPANG_ROCKET, NEAR_STATION))
                                .build()
                );
        given(apartmentService.getApartmentByAs1(anyString()))
                .willReturn(apartmentDtos);

        //when
        //then
        mockMvc.perform(get("/apt?as1=**시"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$[0].as1").value("**시"))
                .andExpect(jsonPath("$[0].as2").value("**구"))
                .andExpect(jsonPath("$[0].as3").value("**읍"))
                .andExpect(jsonPath("$[0].as4").value("**동"))
                .andExpect(jsonPath("$[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$[0].feature[1]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$[1].as1").value("**시"))
                .andExpect(jsonPath("$[1].as2").value("**구"))
                .andExpect(jsonPath("$[1].as3").value("**읍"))
                .andExpect(jsonPath("$[1].as4").value("**동"))
                .andExpect(jsonPath("$[1].feature[0]").value(COUPANG_ROCKET.name()))
                .andExpect(jsonPath("$[1].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("아파트 시군구별 검색 성공")
    void successGetApartmentByAs2() throws Exception {
        //given
        List<ApartmentDto> apartmentDtos =
                Arrays.asList(
                        ApartmentDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build(),
                        ApartmentDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(COUPANG_ROCKET, NEAR_STATION))
                                .build()
                );
        given(apartmentService.getApartmentByAs2(anyString()))
                .willReturn(apartmentDtos);

        //when
        //then
        mockMvc.perform(get("/apt?as2=**구"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$[0].as1").value("**시"))
                .andExpect(jsonPath("$[0].as2").value("**구"))
                .andExpect(jsonPath("$[0].as3").value("**읍"))
                .andExpect(jsonPath("$[0].as4").value("**동"))
                .andExpect(jsonPath("$[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$[0].feature[1]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$[1].as1").value("**시"))
                .andExpect(jsonPath("$[1].as2").value("**구"))
                .andExpect(jsonPath("$[1].as3").value("**읍"))
                .andExpect(jsonPath("$[1].as4").value("**동"))
                .andExpect(jsonPath("$[1].feature[0]").value(COUPANG_ROCKET.name()))
                .andExpect(jsonPath("$[1].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("아파트 읍면별 검색 성공")
    void successGetApartmentByAs3() throws Exception {
        //given
        List<ApartmentDto> apartmentDtos =
                Arrays.asList(
                        ApartmentDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build(),
                        ApartmentDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(COUPANG_ROCKET, NEAR_STATION))
                                .build()
                );
        given(apartmentService.getApartmentByAs3(anyString()))
                .willReturn(apartmentDtos);

        //when
        //then
        mockMvc.perform(get("/apt?as3=**읍"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$[0].as1").value("**시"))
                .andExpect(jsonPath("$[0].as2").value("**구"))
                .andExpect(jsonPath("$[0].as3").value("**읍"))
                .andExpect(jsonPath("$[0].as4").value("**동"))
                .andExpect(jsonPath("$[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$[0].feature[1]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$[1].as1").value("**시"))
                .andExpect(jsonPath("$[1].as2").value("**구"))
                .andExpect(jsonPath("$[1].as3").value("**읍"))
                .andExpect(jsonPath("$[1].as4").value("**동"))
                .andExpect(jsonPath("$[1].feature[0]").value(COUPANG_ROCKET.name()))
                .andExpect(jsonPath("$[1].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("아파트 동리별 검색 성공")
    void successGetApartmentByAs4() throws Exception {
        //given
        List<ApartmentDto> apartmentDtos =
                Arrays.asList(
                        ApartmentDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
                                .build(),
                        ApartmentDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(COUPANG_ROCKET, NEAR_STATION))
                                .build()
                );
        given(apartmentService.getApartmentByAs4(anyString()))
                .willReturn(apartmentDtos);

        //when
        //then
        mockMvc.perform(get("/apt?as4=**동"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$[0].as1").value("**시"))
                .andExpect(jsonPath("$[0].as2").value("**구"))
                .andExpect(jsonPath("$[0].as3").value("**읍"))
                .andExpect(jsonPath("$[0].as4").value("**동"))
                .andExpect(jsonPath("$[0].feature[0]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$[0].feature[1]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$[1].as1").value("**시"))
                .andExpect(jsonPath("$[1].as2").value("**구"))
                .andExpect(jsonPath("$[1].as3").value("**읍"))
                .andExpect(jsonPath("$[1].as4").value("**동"))
                .andExpect(jsonPath("$[1].feature[0]").value(COUPANG_ROCKET.name()))
                .andExpect(jsonPath("$[1].feature[1]").value(NEAR_STATION.name()));
    }

    @Test
    @DisplayName("아파트 특징별 검색 성공")
    void successGetApartmentByFeature() throws Exception {
        //given
        List<ApartmentDto> apartmentDtos =
                Arrays.asList(
                        ApartmentDto.builder()
                                .aptCode("sampleCode1")
                                .name("아파트1")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(NEAR_STATION, GOOD_SCHOOL))
                                .build(),
                        ApartmentDto.builder()
                                .aptCode("sampleCode2")
                                .name("아파트2")
                                .as1("**시")
                                .as2("**구")
                                .as3("**읍")
                                .as4("**동")
                                .feature(Arrays.asList(NEAR_STATION, COUPANG_ROCKET))
                                .build()
                );
        given(apartmentService.getApartmentByFeature(any(ApartmentFeature.class)))
                .willReturn(apartmentDtos);

        //when
        //then
        mockMvc.perform(get("/apt?feature=NEAR_STATION"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].aptCode").value("sampleCode1"))
                .andExpect(jsonPath("$[0].as1").value("**시"))
                .andExpect(jsonPath("$[0].as2").value("**구"))
                .andExpect(jsonPath("$[0].as3").value("**읍"))
                .andExpect(jsonPath("$[0].as4").value("**동"))
                .andExpect(jsonPath("$[0].feature[0]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$[0].feature[1]").value(GOOD_SCHOOL.name()))
                .andExpect(jsonPath("$[1].aptCode").value("sampleCode2"))
                .andExpect(jsonPath("$[1].as1").value("**시"))
                .andExpect(jsonPath("$[1].as2").value("**구"))
                .andExpect(jsonPath("$[1].as3").value("**읍"))
                .andExpect(jsonPath("$[1].as4").value("**동"))
                .andExpect(jsonPath("$[1].feature[0]").value(NEAR_STATION.name()))
                .andExpect(jsonPath("$[1].feature[1]").value(COUPANG_ROCKET.name()));
    }

    @Test
    @DisplayName("아파트 상세 조회 성공")
    void successGetApartmentDetail() throws Exception {
        //given
        given(apartmentService.getApartmentDetail(anyString()))
                .willReturn(ApartmentInfo.builder()
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
    @DisplayName("아파트 저장 실패 - 중복 아파트 존재")
    void failCreateApartment() throws Exception {
        //given
        given(apartmentService.createApartment(any(CreateApartment.Request.class)))
                .willThrow(new ApartmentException(APARTMENT_ALREADY_EXIST));

        //when
        //then
        mockMvc.perform(post("/apt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                CreateApartment.Request.builder()
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
                                        .build()
                        )))
                .andDo(print())
                .andExpect(jsonPath("$.errorCode")
                        .value("APARTMENT_ALREADY_EXIST"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("이미 아파트가 존재합니다"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("아파트 상세 조회 실패 - 존재하지 않는 아파트")
    void failGetApartmentDetail() throws Exception {
        //given
        given(apartmentService.getApartmentDetail(anyString()))
                .willThrow(new ApartmentException(APARTMENT_NOT_FOUND));

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
    @DisplayName("아파트 검색 실패 - 존재하지 않는 특징")
    void failGetApartmentByFeature() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(get("/apt?feature=NEAR_STARBUCKS"))
                .andDo(print())
                .andExpect(jsonPath("$.errorCode")
                        .value("INVALID_REQUEST"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("잘못된 요청입니다."))
                .andExpect(status().isOk());
    }
}
