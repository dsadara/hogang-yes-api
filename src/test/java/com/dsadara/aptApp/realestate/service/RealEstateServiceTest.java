package com.dsadara.aptApp.realestate.service;

import com.dsadara.aptApp.common.type.ErrorCode;
import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.dto.RentInfo;
import com.dsadara.aptApp.realestate.dto.SaleInfo;
import com.dsadara.aptApp.realestate.exception.RealEstateException;
import com.dsadara.aptApp.realestate.type.RealEstateType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class RealEstateServiceTest {

    @Autowired
    private RealEstateService realEstateService;

    @Test
    @DisplayName("성공-getRealEstateByName()")
    void getRealEstateByName_Success() {
        //given
        String name = "강변힐스테이트아파트";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> realEstatePages = realEstateService.getRealEstateByName(name, pageable);

        //then
        assertEquals(name, realEstatePages.getContent().get(0).getName());
    }

    @Test
    @DisplayName("성공-getRealEstateByBeopJeongDongCode()")
    void getRealEstateByBeopJeongDongCode_Success() {
        //given
        String beopJeongDongCode = "11500";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> realEstatePages = realEstateService.getRealEstateByBeopJeongDongCode(beopJeongDongCode, pageable);

        //then
        assertEquals(Integer.valueOf(beopJeongDongCode), realEstatePages.getContent().get(0).getBeopJeongDongCode());
    }

    @Test
    @DisplayName("성공-getRealEstateByBeopJeongDong()")
    void getRealEstateByBeopJeongDong_Success() {
        //given
        String beopJeongDong = "염창동";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> realEstatePages = realEstateService.getRealEstateByBeopJeongDong(beopJeongDong, pageable);

        //then
        assertEquals(beopJeongDong, realEstatePages.getContent().get(0).getBeopJeongDong());
    }

    @Test
    @DisplayName("성공-getRealEstateByParcelNumber()")
    void getRealEstateByParcelNumber_Success() {
        //given
        String parcelNumber = "299";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> realEstatePages = realEstateService.getRealEstateByParcelNumber(parcelNumber, pageable);

        //then
        assertEquals(parcelNumber, realEstatePages.getContent().get(0).getParcelNumber());
    }

    @Test
    @DisplayName("성공-getRealEstateByRealEstateType()")
    void getRealEstateByRealEstateType_Success() {
        //given
        RealEstateType realEstateType = RealEstateType.APT_TRADE;

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstateDto> realEstatePages = realEstateService.getRealEstateByRealEstateType(realEstateType, pageable);

        //then
        assertEquals(realEstateType, realEstatePages.getContent().get(0).getRealEstateType());
    }

    @Test
    @DisplayName("성공-getRealEstateDetail()")
    void getRealEstateDetail_Success() {
        //given
        String id = "1";

        //when
        RealEstateInfo realEstateInfo = realEstateService.getRealEstateDetail(id);

        //then
        assertEquals("강변힐스테이트아파트", realEstateInfo.getName());
    }

    @Test
    @DisplayName("실패-getRealEstateDetail()")
    void getRealEstateDetail_Fail_RealEstateNotFound() {
        //given
        String id = "0";

        //when
        RealEstateException exception = assertThrows(RealEstateException.class,
                () -> realEstateService.getRealEstateDetail(id));

        //then
        assertEquals(ErrorCode.REAL_ESTATE_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("성공-getRentDetail()")
    void getRentDetail_Success() {
        //given
        String id = "2";

        //when
        RentInfo rentInfo = realEstateService.getRentDetail(id);

        //then
        assertEquals("23.08~25.08", rentInfo.getContractPeriod());
    }

    @Test
    @DisplayName("성공-getSaleDetail()")
    void getSaleDetail_Success() {
        //given
        String id = "1";

        //when
        SaleInfo saleInfo = realEstateService.getSaleDetail(id);

        //then
        assertEquals("10.02.01", saleInfo.getCancelDealDay());
    }

}
