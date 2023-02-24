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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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

    private Apartment apartmentSample;

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
    @DisplayName("성공-createApartment()")
    void createApartment_Success() {
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
    @DisplayName("성공-getApartmentByName()")
    void getApartmentByName_Success() {
        //given
        given(apartmentRepository.findByName(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(apartmentSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<ApartmentDto> apartmentDtoPages = apartmentService.getApartmentByName("아파트1", pageable);

        //then
        verify(apartmentRepository, times(1)).findByName(anyString(), any(Pageable.class));
        assertEquals(apartmentSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByAs1()")
    void getApartmentByAs1_Success() {
        //given
        given(apartmentRepository.findByAs1(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(apartmentSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<ApartmentDto> apartmentDtoPages = apartmentService.getApartmentByAs1("**시", pageable);

        //then
        verify(apartmentRepository, times(1)).findByAs1(anyString(), any(Pageable.class));
        assertEquals(apartmentSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByAs2()")
    void getApartmentByAs2_Success() {
        //given
        given(apartmentRepository.findByAs2(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(apartmentSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<ApartmentDto> apartmentDtoPages = apartmentService.getApartmentByAs2("**구", pageable);

        //then
        verify(apartmentRepository, times(1)).findByAs2(anyString(), any(Pageable.class));
        assertEquals(apartmentSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByAs3()")
    void getApartmentByAs3_Success() {
        //given
        given(apartmentRepository.findByAs3(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(apartmentSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<ApartmentDto> apartmentDtoPages = apartmentService.getApartmentByAs3("**읍", pageable);

        //then
        verify(apartmentRepository, times(1)).findByAs3(anyString(), any(Pageable.class));
        assertEquals(apartmentSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByAs4()")
    void getApartmentByAs4_Success() {
        //given
        given(apartmentRepository.findByAs4(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(apartmentSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<ApartmentDto> apartmentDtoPages = apartmentService.getApartmentByAs4("**동", pageable);

        //then
        verify(apartmentRepository, times(1)).findByAs4(anyString(), any(Pageable.class));
        assertEquals(apartmentSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByFeature()")
    void getApartmentByFeature_Success() {
        //given
        given(apartmentRepository.findByFeature(any(ApartmentFeature.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(apartmentSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<ApartmentDto> apartmentDtoPages = apartmentService.getApartmentByFeature(GOOD_SCHOOL, pageable);

        //then
        verify(apartmentRepository, times(1)).findByFeature(any(ApartmentFeature.class), any(Pageable.class));
        assertEquals(apartmentSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
        assertEquals(apartmentSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(apartmentSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
        assertEquals(apartmentSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
        assertEquals(apartmentSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
        assertEquals(apartmentSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
        assertEquals(apartmentSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentDetail()")
    void getApartmentDetail_Success() {
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
    @DisplayName("실패-getApartmentDetail()")
    void getApartmentDetail_Fail() {
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
    @DisplayName("실패-createApartment()-중복코드존재")
    void createApartment_Fail_duplicateAptCode() {
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
    @DisplayName("실패-createApartment()-중복이름존재")
    void createApartment_Fail_duplicateName() {
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
