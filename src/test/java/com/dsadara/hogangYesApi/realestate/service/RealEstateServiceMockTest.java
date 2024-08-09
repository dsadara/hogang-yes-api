package com.dsadara.hogangYesApi.realestate.service;

import com.dsadara.hogangYesApi.realestate.dto.RealEstateDto;
import com.dsadara.hogangYesApi.realestate.dto.RealEstateInfo;
import com.dsadara.hogangYesApi.realestate.dto.RentInfo;
import com.dsadara.hogangYesApi.realestate.dto.SaleInfo;
import com.dsadara.hogangYesApi.realestate.entity.RealEstate;
import com.dsadara.hogangYesApi.realestate.entity.Rent;
import com.dsadara.hogangYesApi.realestate.entity.Sale;
import com.dsadara.hogangYesApi.realestate.repository.RealEstateRepository;
import com.dsadara.hogangYesApi.realestate.repository.RentRepository;
import com.dsadara.hogangYesApi.realestate.repository.SaleRepository;
import com.dsadara.hogangYesApi.realestate.type.RealEstateType;
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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RealEstateServiceMockTest {
    @Mock
    private RealEstateRepository realEstateRepository;
    @Mock
    private RentRepository rentRepository;
    @Mock
    private SaleRepository saleRepository;
    @InjectMocks
    private RealEstateService realEstateService;
    private RealEstate realEstateSample;
    private Rent rentSample;
    private Sale saleSample;

    @BeforeEach
    void beforeEach() {
        realEstateSample = RealEstate.builder()
                .id(1)
                .beopJeongDong("염창동")
                .beopJeongDongCode(11500)
                .constructYear(Short.valueOf("2005"))
                .contractDay(Short.valueOf("22"))
                .contractMonth(Short.valueOf("1"))
                .contractYear(Short.valueOf("2011"))
                .floor(Short.valueOf("12"))
                .jeonYongArea("74")
                .name("강변힐스테이트")
                .parcelNumber("299")
                .realEstateType(RealEstateType.APT_TRADE)
                .build();

        rentSample = Rent.builder()
                .contractPeriod("23.08~25.08")
                .contractType("갱신")
                .deposit(new BigDecimal(10000))
                .depositBefore(new BigDecimal(9000))
                .monthlyRent(new BigDecimal(200))
                .monthlyRentBefore(new BigDecimal(185))
                .requestRenewalRight("사용")
                .siGunGu("강서구")
                .build();

        saleSample = Sale.builder()
                .CancelDealDay("10.02.01")
                .CancelDealType("O")
                .agentAddress("서울 강서구")
                .dealAmount(new BigDecimal("110000"))
                .dealType("중개거래")
                .build();
    }

    @Test
    @DisplayName("성공-getRealEstateByName()")
    void getRealEstateByName_Success() {
        //given
        given(realEstateRepository.findByName(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByName("강변힐스테이트", pageable);

        //then
        verify(realEstateRepository, times(1)).findByName(anyString(), any(Pageable.class));
        assertEquals(realEstateSample.getId(), apartmentDtoPages.getContent().get(0).getId());
        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(realEstateSample.getBeopJeongDong(), apartmentDtoPages.getContent().get(0).getBeopJeongDong());
        assertEquals(realEstateSample.getBeopJeongDongCode(), apartmentDtoPages.getContent().get(0).getBeopJeongDongCode());
        assertEquals(realEstateSample.getParcelNumber(), apartmentDtoPages.getContent().get(0).getParcelNumber());
        assertEquals(realEstateSample.getRealEstateType(), apartmentDtoPages.getContent().get(0).getRealEstateType());
    }

    @Test
    @DisplayName("성공-getRealEstateBybeopJeongDongCode()")
    void getRealEstateBybeopJeongDongCode_Success() {
        //given
        given(realEstateRepository.findBybeopJeongDongCode(anyInt(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByBeopJeongDongCode("11500", pageable);

        //then
        verify(realEstateRepository, times(1)).findBybeopJeongDongCode(anyInt(), any(Pageable.class));
        assertEquals(realEstateSample.getId(), apartmentDtoPages.getContent().get(0).getId());
        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(realEstateSample.getBeopJeongDong(), apartmentDtoPages.getContent().get(0).getBeopJeongDong());
        assertEquals(realEstateSample.getBeopJeongDongCode(), apartmentDtoPages.getContent().get(0).getBeopJeongDongCode());
        assertEquals(realEstateSample.getParcelNumber(), apartmentDtoPages.getContent().get(0).getParcelNumber());
        assertEquals(realEstateSample.getRealEstateType(), apartmentDtoPages.getContent().get(0).getRealEstateType());
    }

    @Test
    @DisplayName("성공-getRealEstateBybeopJeongDong()")
    void getRealEstateBybeopJeongDong_Success() {
        //given
        given(realEstateRepository.findByBeopJeongDong(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByBeopJeongDong("염창동", pageable);

        //then
        verify(realEstateRepository, times(1)).findByBeopJeongDong(anyString(), any(Pageable.class));
        assertEquals(realEstateSample.getId(), apartmentDtoPages.getContent().get(0).getId());
        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(realEstateSample.getBeopJeongDong(), apartmentDtoPages.getContent().get(0).getBeopJeongDong());
        assertEquals(realEstateSample.getBeopJeongDongCode(), apartmentDtoPages.getContent().get(0).getBeopJeongDongCode());
        assertEquals(realEstateSample.getParcelNumber(), apartmentDtoPages.getContent().get(0).getParcelNumber());
        assertEquals(realEstateSample.getRealEstateType(), apartmentDtoPages.getContent().get(0).getRealEstateType());
    }

    @Test
    @DisplayName("성공-getRealEstateByParcelNumber()")
    void getRealEstateByParcelNumber_Success() {
        //given
        given(realEstateRepository.findByParcelNumber(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(Collections.singletonList(realEstateSample)));

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> apartmentDtoPages = realEstateService.getRealEstateByParcelNumber("299", pageable);

        //then
        verify(realEstateRepository, times(1)).findByParcelNumber(anyString(), any(Pageable.class));
        assertEquals(realEstateSample.getId(), apartmentDtoPages.getContent().get(0).getId());
        assertEquals(realEstateSample.getName(), apartmentDtoPages.getContent().get(0).getName());
        assertEquals(realEstateSample.getBeopJeongDong(), apartmentDtoPages.getContent().get(0).getBeopJeongDong());
        assertEquals(realEstateSample.getBeopJeongDongCode(), apartmentDtoPages.getContent().get(0).getBeopJeongDongCode());
        assertEquals(realEstateSample.getParcelNumber(), apartmentDtoPages.getContent().get(0).getParcelNumber());
        assertEquals(realEstateSample.getRealEstateType(), apartmentDtoPages.getContent().get(0).getRealEstateType());
    }

    @Test
    @DisplayName("성공-getRealEstateDetail()")
    void getRealEstateDetail_Success() {
        //given
        given(realEstateRepository.findById(anyInt()))
                .willReturn(Optional.of(realEstateSample));

        //when
        RealEstateInfo realEstateDetail = realEstateService.getRealEstateDetail("1");

        //then
        verify(realEstateRepository, times(1)).findById(anyInt());
        assertEquals(realEstateSample.getName(), realEstateDetail.getName());
        assertEquals(realEstateSample.getBeopJeongDong(), realEstateDetail.getBeopJeongDong());
        assertEquals(realEstateSample.getBeopJeongDongCode(), realEstateDetail.getBeopJeongDongCode());
        assertEquals(realEstateSample.getParcelNumber(), realEstateDetail.getParcelNumber());
        assertEquals(realEstateSample.getRealEstateType(), realEstateDetail.getRealEstateType());
    }

    @Test
    @DisplayName("성공-getRentDetail()")
    void getRentDetail_Success() {
        //given
        given(rentRepository.findById(anyInt()))
                .willReturn(Optional.of(rentSample));

        //when
        RentInfo rentInfo = realEstateService.getRentDetail("1");

        //then
        verify(rentRepository, times(1)).findById(anyInt());
        assertEquals(rentSample.getRequestRenewalRight(), rentInfo.getRequestRenewalRight());
        assertEquals(rentSample.getContractType(), rentInfo.getContractType());
        assertEquals(rentSample.getContractPeriod(), rentInfo.getContractPeriod());
        assertEquals(rentSample.getDeposit(), rentInfo.getDeposit());
        assertEquals(rentSample.getDepositBefore(), rentInfo.getDepositBefore());
        assertEquals(rentSample.getMonthlyRent(), rentInfo.getMonthlyRent());
        assertEquals(rentSample.getMonthlyRentBefore(), rentInfo.getMonthlyRentBefore());
    }

    @Test
    @DisplayName("성공-getSaleDetail()")
    void getSaleDetail_Success() {
        //given
        given(saleRepository.findById(anyInt()))
                .willReturn(Optional.of(saleSample));
        //when
        SaleInfo saleInfo = realEstateService.getSaleDetail("1");

        //then
        verify(saleRepository, times(1)).findById(anyInt());
        assertEquals(saleSample.getCancelDealDay(), saleInfo.getCancelDealDay());
        assertEquals(saleSample.getCancelDealType(), saleInfo.getCancelDealType());
        assertEquals(saleSample.getAgentAddress(), saleInfo.getAgentAddress());
        assertEquals(saleSample.getDealAmount(), saleInfo.getDealAmount());
        assertEquals(saleSample.getDealType(), saleInfo.getDealType());
    }
}
