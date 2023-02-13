package com.dsadara.aptApp.apartment.service;

import com.dsadara.aptApp.apartment.dto.ApartmentDto;
import com.dsadara.aptApp.apartment.dto.ApartmentInfo;
import com.dsadara.aptApp.apartment.dto.CreateApartment;
import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.exception.ApartmentException;
import com.dsadara.aptApp.apartment.repository.ApartmentRepository;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.dsadara.aptApp.apartment.type.ApartmentFeature.GOOD_SCHOOL;
import static com.dsadara.aptApp.apartment.type.ApartmentFeature.NEAR_STATION;
import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_ALREADY_EXIST;
import static com.dsadara.aptApp.common.type.ErrorCode.APARTMENT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ApartmentServiceMockTest {
    @Mock
    private ApartmentRepository apartmentRepository;

    @InjectMocks
    private ApartmentService apartmentService;

    Apartment apartmentSample;

    @BeforeEach
    void beforeEach() {
        apartmentSample = Apartment.builder()
                .aptCode("sampleCode")
                .amountRecent(0L)
                .viewWeek(0)
                .viewTotal(0)
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
                .build();
    }

    @Test
    @DisplayName("성공-아파트저장")
    void createApartmentSuccess() {
        //given
        given(apartmentRepository.existsByAptCode(anyString()))
                .willReturn(Boolean.FALSE);

        given(apartmentRepository.existsByName(anyString()))
                .willReturn(Boolean.FALSE);

        given(apartmentRepository.save(any(Apartment.class)))
                .willReturn(apartmentSample);

        //when
        ApartmentDto apartmentDto = apartmentService.createApartment(
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
                        .build());

        //then
        verify(apartmentRepository, times(1)).existsByName(anyString());
        verify(apartmentRepository, times(1)).existsByAptCode(anyString());
        assertEquals(apartmentSample.getAptCode(), apartmentDto.getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDto.getName());
        assertEquals(apartmentSample.getAs1(), apartmentDto.getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDto.getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDto.getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDto.getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDto.getFeature());
    }

    @Test
    @DisplayName("성공-아파트이름검색")
    void getApartmentByNameSuccess() {
        //given
        given(apartmentRepository.findByName(anyString()))
                .willReturn(Collections.singletonList(apartmentSample));
        //when
        List<ApartmentDto> apartmentDtos = apartmentService.getApartmentByName("아파트1");

        //then
        verify(apartmentRepository, times(1)).findByName(anyString());
        assertEquals(apartmentSample.getAptCode(), apartmentDtos.get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtos.get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtos.get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtos.get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtos.get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtos.get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtos.get(0).getFeature());
    }

    @Test
    @DisplayName("성공-아파트시도검색")
    void getApartmentByAs1Success() {
        //given
        given(apartmentRepository.findByAs1(anyString()))
                .willReturn(Collections.singletonList(apartmentSample));
        //when
        List<ApartmentDto> apartmentDtos = apartmentService.getApartmentByAs1("**시");

        //then
        verify(apartmentRepository, times(1)).findByAs1(anyString());
        assertEquals(apartmentSample.getAptCode(), apartmentDtos.get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtos.get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtos.get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtos.get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtos.get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtos.get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtos.get(0).getFeature());
    }

    @Test
    @DisplayName("성공-아파트시군구검색")
    void getApartmentByAs2Success() {
        //given
        given(apartmentRepository.findByAs2(anyString()))
                .willReturn(Collections.singletonList(apartmentSample));
        //when
        List<ApartmentDto> apartmentDtos = apartmentService.getApartmentByAs2("**구");

        //then
        verify(apartmentRepository, times(1)).findByAs2(anyString());
        assertEquals(apartmentSample.getAptCode(), apartmentDtos.get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtos.get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtos.get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtos.get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtos.get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtos.get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtos.get(0).getFeature());
    }

    @Test
    @DisplayName("성공-아파트읍면검색")
    void getApartmentByAs3Success() {
        //given
        given(apartmentRepository.findByAs3(anyString()))
                .willReturn(Collections.singletonList(apartmentSample));
        //when
        List<ApartmentDto> apartmentDtos = apartmentService.getApartmentByAs3("**읍");

        //then
        verify(apartmentRepository, times(1)).findByAs3(anyString());
        assertEquals(apartmentSample.getAptCode(), apartmentDtos.get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtos.get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtos.get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtos.get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtos.get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtos.get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtos.get(0).getFeature());
    }

    @Test
    @DisplayName("성공-아파트동리검색")
    void getApartmentByAs4Success() {
        //given
        given(apartmentRepository.findByAs4(anyString()))
                .willReturn(Collections.singletonList(apartmentSample));
        //when
        List<ApartmentDto> apartmentDtos = apartmentService.getApartmentByAs4("**동");

        //then
        verify(apartmentRepository, times(1)).findByAs4(anyString());
        assertEquals(apartmentSample.getAptCode(), apartmentDtos.get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtos.get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtos.get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtos.get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtos.get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtos.get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtos.get(0).getFeature());
    }

    @Test
    @DisplayName("성공-아파트특징검색")
    void getApartmentByFeatureSuccess() {
        //given
        given(apartmentRepository.findByFeature(any(ApartmentFeature.class)))
                .willReturn(Collections.singletonList(apartmentSample));
        //when
        List<ApartmentDto> apartmentDtos = apartmentService.getApartmentByFeature(GOOD_SCHOOL);

        //then
        verify(apartmentRepository, times(1)).findByFeature(any(ApartmentFeature.class));
        assertEquals(apartmentSample.getAptCode(), apartmentDtos.get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtos.get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtos.get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtos.get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtos.get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtos.get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtos.get(0).getFeature());
    }

    @Test
    @DisplayName("성공-아파트상세조회")
    void getApartmentDetailSuccess() {
        //given
        given(apartmentRepository.findByAptCode(anyString()))
                .willReturn(Optional.of(apartmentSample));
        //when
        ApartmentInfo apartmentInfo = apartmentService.getApartmentDetail("sampleCode");

        //then
        verify(apartmentRepository, times(1)).findByAptCode(anyString());
        assertEquals(apartmentSample.getAptCode(), apartmentInfo.getAptCode());
        assertEquals(apartmentSample.getAmountRecent(), apartmentInfo.getAmountRecent());
        assertEquals(apartmentSample.getViewWeek(), apartmentInfo.getViewWeek());
        assertEquals(apartmentSample.getViewTotal(), apartmentInfo.getViewTotal());
        assertEquals(apartmentSample.getName(), apartmentInfo.getName());
        assertEquals(apartmentSample.getAs1(), apartmentInfo.getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentInfo.getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentInfo.getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentInfo.getAs4());
        assertEquals(apartmentSample.getDrmAddress(), apartmentInfo.getDrmAddress());
        assertEquals(apartmentSample.getApprvDate(), apartmentInfo.getApprvDate());
        assertEquals(apartmentSample.getDongNo(), apartmentInfo.getDongNo());
        assertEquals(apartmentSample.getHouseNo(), apartmentInfo.getHouseNo());
        assertEquals(apartmentSample.getParkingSpaceNo(), apartmentInfo.getParkingSpaceNo());
        assertEquals(apartmentSample.getBjdCode(), apartmentInfo.getBjdCode());
        assertEquals(apartmentSample.getFeature(), apartmentInfo.getFeature());

    }

    @Test
    @DisplayName("실패-아파트상세조회")
    void getApartmentDetailFail() {
        //given
        given(apartmentRepository.findByAptCode(anyString()))
                .willThrow(new ApartmentException(APARTMENT_NOT_FOUND));

        //when
        ApartmentException exception = assertThrows(ApartmentException.class,
                () -> apartmentService.getApartmentDetail("aptCodeNotExist"));

        //then
        verify(apartmentRepository, times(1)).findByAptCode(anyString());
        assertEquals(APARTMENT_NOT_FOUND, exception.getErrorCode());
        assertEquals("아파트가 없습니다", exception.getErrorMessage());

    }

    @Test
    @DisplayName("실패-아파트저장-중복코드존재")
    void createApartmentFail_duplicateAptCode() {
        //given
        given(apartmentRepository.existsByAptCode(anyString()))
                .willThrow(new ApartmentException(APARTMENT_ALREADY_EXIST));

        //when
        ApartmentException exception = assertThrows(ApartmentException.class,
                () -> apartmentService.createApartment(
                        CreateApartment.Request.builder()
                                .aptCode("duplicateAptCode")
                                .build()
                ));

        //then
        verify(apartmentRepository, times(1)).existsByAptCode(anyString());
        assertEquals(APARTMENT_ALREADY_EXIST, exception.getErrorCode());
        assertEquals("이미 아파트가 존재합니다", exception.getErrorMessage());
    }

    @Test
    @DisplayName("실패-아파트저장-중복이름존재")
    void createApartmentFail_duplicateName() {
        //given
        given(apartmentRepository.existsByName(anyString()))
                .willThrow(new ApartmentException(APARTMENT_ALREADY_EXIST));

        //when
        ApartmentException exception = assertThrows(ApartmentException.class,
                () -> apartmentService.createApartment(
                        CreateApartment.Request.builder()
                                .name("duplicateName")
                                .build()
                ));

        //then
        verify(apartmentRepository, times(1)).existsByName(anyString());
        assertEquals(APARTMENT_ALREADY_EXIST, exception.getErrorCode());
        assertEquals("이미 아파트가 존재합니다", exception.getErrorMessage());
    }
}
