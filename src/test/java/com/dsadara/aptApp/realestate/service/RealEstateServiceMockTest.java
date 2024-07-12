package com.dsadara.aptApp.realestate.service;

import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.repository.RealEstateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RealEstateServiceMockTest {
    @Mock
    private RealEstateRepository realEstateRepository;

    @InjectMocks
    private RealEstateService realEstateService;

    private RealEstate realEstateSample;

    @BeforeEach
    void beforeEach() {
//        realEstateSample = RealEstate.builder()
//                .aptCode("sampleCode")
//                .amountRecent(0L)
//                .viewWeek(0)
//                .viewTotal(0)
//                .name("아파트1")
//                .as1("**시")
//                .as2("**구")
//                .as3("**읍")
//                .as4("**동")
//                .drmAddress("도로명주소1")
//                .apprvDate(LocalDate.of(2001, 1, 1))
//                .dongNo(10)
//                .houseNo(500)
//                .parkingSpaceNo(1000)
//                .bjdCode("sampleBjdCode")
//                .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
//                .build();
    }

    @Test
    @DisplayName("성공-createApartment()")
    void createApartment_Success() {
//        //given
//        given(realEstateRepository.existsByAptCode(anyString()))
//                .willReturn(Boolean.FALSE);
//
//        given(realEstateRepository.existsByName(anyString()))
//                .willReturn(Boolean.FALSE);
//
//        given(realEstateRepository.save(any(RealEstate.class)))
//                .willReturn(realEstateSample);
//
//        //when
//        RealEstateDto realEstateDto = realEstateService.createRealEstate(
//                CreateRealEstate.Request.builder()
//                        .aptCode("sampleCode")
//                        .name("아파트1")
//                        .as1("**시")
//                        .as2("**구")
//                        .as3("**읍")
//                        .as4("**동")
//                        .drmAddress("도로명주소1")
//                        .apprvDate(LocalDate.of(2001, 1, 1))
//                        .dongNo(10)
//                        .houseNo(500)
//                        .parkingSpaceNo(1000)
//                        .bjdCode("sampleBjdCode")
//                        .feature(Arrays.asList(GOOD_SCHOOL, NEAR_STATION))
//                        .build());
//
//        //then
//        verify(realEstateRepository, times(1)).existsByName(anyString());
//        verify(realEstateRepository, times(1)).existsByAptCode(anyString());
//        assertEquals(realEstateSample.getAptCode(), realEstateDto.getAptCode());
//        assertEquals(realEstateSample.getName(), realEstateDto.getName());
//        assertEquals(realEstateSample.getAs1(), realEstateDto.getAs1());
//        assertEquals(realEstateSample.getAs2(), realEstateDto.getAs2());
//        assertEquals(realEstateSample.getAs3(), realEstateDto.getAs3());
//        assertEquals(realEstateSample.getAs4(), realEstateDto.getAs4());
//        assertEquals(realEstateSample.getFeature(), realEstateDto.getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByName()")
    void getApartmentByName_Success() {
//        //given
//        given(realEstateRepository.findByName(anyString(), any(Pageable.class)))
//                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByName("아파트1", pageable);
//
//        //then
//        verify(realEstateRepository, times(1)).findByName(anyString(), any(Pageable.class));
//        assertEquals(realEstateSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
//        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
//        assertEquals(realEstateSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
//        assertEquals(realEstateSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
//        assertEquals(realEstateSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
//        assertEquals(realEstateSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
//        assertEquals(realEstateSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByAs1()")
    void getApartmentByAs1_Success() {
//        //given
//        given(realEstateRepository.findByAs1(anyString(), any(Pageable.class)))
//                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByAs1("**시", pageable);
//
//        //then
//        verify(realEstateRepository, times(1)).findByAs1(anyString(), any(Pageable.class));
//        assertEquals(realEstateSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
//        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
//        assertEquals(realEstateSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
//        assertEquals(realEstateSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
//        assertEquals(realEstateSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
//        assertEquals(realEstateSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
//        assertEquals(realEstateSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByAs2()")
    void getApartmentByAs2_Success() {
//        //given
//        given(realEstateRepository.findByAs2(anyString(), any(Pageable.class)))
//                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByAs2("**구", pageable);
//
//        //then
//        verify(realEstateRepository, times(1)).findByAs2(anyString(), any(Pageable.class));
//        assertEquals(realEstateSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
//        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
//        assertEquals(realEstateSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
//        assertEquals(realEstateSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
//        assertEquals(realEstateSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
//        assertEquals(realEstateSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
//        assertEquals(realEstateSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByAs3()")
    void getApartmentByAs3_Success() {
//        //given
//        given(realEstateRepository.findByAs3(anyString(), any(Pageable.class)))
//                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByAs3("**읍", pageable);
//
//        //then
//        verify(realEstateRepository, times(1)).findByAs3(anyString(), any(Pageable.class));
//        assertEquals(realEstateSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
//        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
//        assertEquals(realEstateSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
//        assertEquals(realEstateSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
//        assertEquals(realEstateSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
//        assertEquals(realEstateSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
//        assertEquals(realEstateSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByAs4()")
    void getApartmentByAs4_Success() {
//        //given
//        given(realEstateRepository.findByAs4(anyString(), any(Pageable.class)))
//                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByAs4("**동", pageable);
//
//        //then
//        verify(realEstateRepository, times(1)).findByAs4(anyString(), any(Pageable.class));
//        assertEquals(realEstateSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
//        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
//        assertEquals(realEstateSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
//        assertEquals(realEstateSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
//        assertEquals(realEstateSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
//        assertEquals(realEstateSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
//        assertEquals(realEstateSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentByFeature()")
    void getApartmentByFeature_Success() {
//        //given
//        given(realEstateRepository.findByFeature(any(RealEstateFeature.class), any(Pageable.class)))
//                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));
//
//        //when
//        Pageable pageable = PageRequest.of(0, 5);
//        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByFeature(GOOD_SCHOOL, pageable);
//
//        //then
//        verify(realEstateRepository, times(1)).findByFeature(any(RealEstateFeature.class), any(Pageable.class));
//        assertEquals(realEstateSample.getAptCode(), apartmentDtoPages.getContent().get(0).getAptCode());
//        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
//        assertEquals(realEstateSample.getAs1(), apartmentDtoPages.getContent().get(0).getAs1());
//        assertEquals(realEstateSample.getAs2(), apartmentDtoPages.getContent().get(0).getAs2());
//        assertEquals(realEstateSample.getAs3(), apartmentDtoPages.getContent().get(0).getAs3());
//        assertEquals(realEstateSample.getAs4(), apartmentDtoPages.getContent().get(0).getAs4());
//        assertEquals(realEstateSample.getFeature(), apartmentDtoPages.getContent().get(0).getFeature());
    }

    @Test
    @DisplayName("성공-getApartmentDetail()")
    void getApartmentDetail_Success() {
//        //given
//        given(realEstateRepository.findByAptCode(anyString()))
//                .willReturn(Optional.of(realEstateSample));
//        //when
//        RealEstateInfo realEstateInfo = realEstateService.getRealEstateDetail("sampleCode");
//
//        //then
//        verify(realEstateRepository, times(1)).findByAptCode(anyString());
//        assertEquals(realEstateSample.getAptCode(), realEstateInfo.getAptCode());
//        assertEquals(realEstateSample.getAmountRecent(), realEstateInfo.getAmountRecent());
//        assertEquals(realEstateSample.getViewWeek(), realEstateInfo.getViewWeek());
//        assertEquals(realEstateSample.getViewTotal(), realEstateInfo.getViewTotal());
//        assertEquals(realEstateSample.getName(), realEstateInfo.getName());
//        assertEquals(realEstateSample.getAs1(), realEstateInfo.getAs1());
//        assertEquals(realEstateSample.getAs2(), realEstateInfo.getAs2());
//        assertEquals(realEstateSample.getAs3(), realEstateInfo.getAs3());
//        assertEquals(realEstateSample.getAs4(), realEstateInfo.getAs4());
//        assertEquals(realEstateSample.getDrmAddress(), realEstateInfo.getDrmAddress());
//        assertEquals(realEstateSample.getApprvDate(), realEstateInfo.getApprvDate());
//        assertEquals(realEstateSample.getDongNo(), realEstateInfo.getDongNo());
//        assertEquals(realEstateSample.getHouseNo(), realEstateInfo.getHouseNo());
//        assertEquals(realEstateSample.getParkingSpaceNo(), realEstateInfo.getParkingSpaceNo());
//        assertEquals(realEstateSample.getBjdCode(), realEstateInfo.getBjdCode());
//        assertEquals(realEstateSample.getFeature(), realEstateInfo.getFeature());

    }

    @Test
    @DisplayName("실패-getApartmentDetail()")
    void getApartmentDetail_Fail() {
//        //given
//        given(realEstateRepository.findByAptCode(anyString()))
//                .willThrow(new RealEstateException(APARTMENT_NOT_FOUND));
//
//        //when
//        RealEstateException exception = assertThrows(RealEstateException.class,
//                () -> realEstateService.getRealEstateDetail("aptCodeNotExist"));
//
//        //then
//        verify(realEstateRepository, times(1)).findByAptCode(anyString());
//        assertEquals(APARTMENT_NOT_FOUND, exception.getErrorCode());
//        assertEquals("아파트가 없습니다", exception.getErrorMessage());

    }

    @Test
    @DisplayName("실패-createApartment()-중복코드존재")
    void createApartment_Fail_duplicateAptCode() {
//        //given
//        given(realEstateRepository.existsByAptCode(anyString()))
//                .willThrow(new RealEstateException(APARTMENT_ALREADY_EXIST));
//
//        //when
//        RealEstateException exception = assertThrows(RealEstateException.class,
//                () -> realEstateService.createRealEstate(
//                        CreateRealEstate.Request.builder()
//                                .aptCode("duplicateAptCode")
//                                .build()
//                ));
//
//        //then
//        verify(realEstateRepository, times(1)).existsByAptCode(anyString());
//        assertEquals(APARTMENT_ALREADY_EXIST, exception.getErrorCode());
//        assertEquals("이미 아파트가 존재합니다", exception.getErrorMessage());
    }

    @Test
    @DisplayName("실패-createApartment()-중복이름존재")
    void createApartment_Fail_duplicateName() {
//        //given
//        given(realEstateRepository.existsByName(anyString()))
//                .willThrow(new RealEstateException(APARTMENT_ALREADY_EXIST));
//
//        //when
//        RealEstateException exception = assertThrows(RealEstateException.class,
//                () -> realEstateService.createRealEstate(
//                        CreateRealEstate.Request.builder()
//                                .name("duplicateName")
//                                .build()
//                ));
//
//        //then
//        verify(realEstateRepository, times(1)).existsByName(anyString());
//        assertEquals(APARTMENT_ALREADY_EXIST, exception.getErrorCode());
//        assertEquals("이미 아파트가 존재합니다", exception.getErrorMessage());
    }
}
