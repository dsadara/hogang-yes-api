package com.dsadara.aptApp.realestate.controller;

import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.exception.RealEstateException;
import com.dsadara.aptApp.realestate.service.RealEstateService;
import com.dsadara.aptApp.realestate.type.RealEstateType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.dsadara.aptApp.common.type.ErrorCode.REAL_ESTATE_NOT_FOUND;
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
    @DisplayName("성공-부동산 이름 검색")
    void getRealEstateByName_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Collections.singletonList(
                        RealEstateDto.builder()
                                .beopJeongDongCode("11110")
                                .beopJeongDong("염창동")
                                .name("강변힐스테이트아파트")
                                .parcelNumber("299")
                                .realEstateType(RealEstateType.APT_TRADE)
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByName(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/realestate?searchKey=name&searchValue=강변힐스테이트아파트"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("강변힐스테이트아파트"))
                .andExpect(jsonPath("$.content[0].beopJeongDongCode").value("11110"))
                .andExpect(jsonPath("$.content[0].beopJeongDong").value("염창동"))
                .andExpect(jsonPath("$.content[0].parcelNumber").value("299"))
                .andExpect(jsonPath("$.content[0].realEstateType").value("APT_TRADE"));
    }

    @Test
    @DisplayName("성공-부동산 법정동 코드 검색")
    void getRealEstateBybeopJeongDongCode_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .beopJeongDongCode("11110")
                                .beopJeongDong("염창동")
                                .name("강변힐스테이트아파트")
                                .parcelNumber("299")
                                .realEstateType(RealEstateType.APT_TRADE)
                                .build(),
                        RealEstateDto.builder()
                                .beopJeongDongCode("11110")
                                .beopJeongDong("염창동")
                                .name("강변힐스테이트아파트")
                                .parcelNumber("299")
                                .realEstateType(RealEstateType.APT_RENT)
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByBeopJeongDongCode(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/realestate?searchKey=beopJeongDongCode&searchValue=11110"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("강변힐스테이트아파트"))
                .andExpect(jsonPath("$.content[0].beopJeongDongCode").value("11110"))
                .andExpect(jsonPath("$.content[0].beopJeongDong").value("염창동"))
                .andExpect(jsonPath("$.content[0].parcelNumber").value("299"))
                .andExpect(jsonPath("$.content[0].realEstateType").value("APT_TRADE"))
                .andExpect(jsonPath("$.content[1].name").value("강변힐스테이트아파트"))
                .andExpect(jsonPath("$.content[1].beopJeongDongCode").value("11110"))
                .andExpect(jsonPath("$.content[1].beopJeongDong").value("염창동"))
                .andExpect(jsonPath("$.content[1].parcelNumber").value("299"))
                .andExpect(jsonPath("$.content[1].realEstateType").value("APT_RENT"));
    }

    @Test
    @DisplayName("성공-부동산 법정동 검색")
    void getRealEstateBybeopJeongDong_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .beopJeongDongCode("11110")
                                .beopJeongDong("염창동")
                                .name("강변힐스테이트아파트")
                                .parcelNumber("299")
                                .realEstateType(RealEstateType.APT_TRADE)
                                .build(),
                        RealEstateDto.builder()
                                .beopJeongDongCode("11110")
                                .beopJeongDong("염창동")
                                .name("강변힐스테이트아파트")
                                .parcelNumber("299")
                                .realEstateType(RealEstateType.APT_RENT)
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByBeopJeongDong(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/realestate?searchKey=beopJeongDong&searchValue=염창동"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("강변힐스테이트아파트"))
                .andExpect(jsonPath("$.content[0].beopJeongDongCode").value("11110"))
                .andExpect(jsonPath("$.content[0].beopJeongDong").value("염창동"))
                .andExpect(jsonPath("$.content[0].parcelNumber").value("299"))
                .andExpect(jsonPath("$.content[0].realEstateType").value("APT_TRADE"))
                .andExpect(jsonPath("$.content[1].name").value("강변힐스테이트아파트"))
                .andExpect(jsonPath("$.content[1].beopJeongDongCode").value("11110"))
                .andExpect(jsonPath("$.content[1].beopJeongDong").value("염창동"))
                .andExpect(jsonPath("$.content[1].parcelNumber").value("299"))
                .andExpect(jsonPath("$.content[1].realEstateType").value("APT_RENT"));
    }

    @Test
    @DisplayName("성공-부동산 지번 검색")
    void getRealEstateByParcelNumber_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .beopJeongDongCode("11110")
                                .beopJeongDong("염창동")
                                .name("강변힐스테이트아파트")
                                .parcelNumber("299")
                                .realEstateType(RealEstateType.APT_TRADE)
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByParcelNumber(anyString(), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/realestate?searchKey=parcelNumber&searchValue=299"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("강변힐스테이트아파트"))
                .andExpect(jsonPath("$.content[0].beopJeongDongCode").value("11110"))
                .andExpect(jsonPath("$.content[0].beopJeongDong").value("염창동"))
                .andExpect(jsonPath("$.content[0].parcelNumber").value("299"))
                .andExpect(jsonPath("$.content[0].realEstateType").value("APT_TRADE"));
    }

    @Test
    @DisplayName("성공-부동산 타입 검색")
    void getRealEstateByRealEstateType_Success() throws Exception {
        //given
        List<RealEstateDto> realEstateDtos =
                Arrays.asList(
                        RealEstateDto.builder()
                                .beopJeongDongCode("11110")
                                .beopJeongDong("염창동")
                                .name("강변힐스테이트아파트")
                                .parcelNumber("299")
                                .realEstateType(RealEstateType.APT_RENT)
                                .build()
                );
        Page<RealEstateDto> pageResponse = new PageImpl<>(realEstateDtos);
        given(realEstateService.getRealEstateByRealEstateType(any(RealEstateType.class), any(Pageable.class)))
                .willReturn(pageResponse);

        //when
        //then
        mockMvc.perform(get("/realestate?searchKey=realEstateType&searchValue=APT_RENT"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("강변힐스테이트아파트"))
                .andExpect(jsonPath("$.content[0].beopJeongDongCode").value("11110"))
                .andExpect(jsonPath("$.content[0].beopJeongDong").value("염창동"))
                .andExpect(jsonPath("$.content[0].parcelNumber").value("299"))
                .andExpect(jsonPath("$.content[0].realEstateType").value("APT_RENT"));
    }

    @Test
    @DisplayName("성공-아파트 상세 조회")
    void getApartmentDetail_Success() throws Exception {
        //given
        given(realEstateService.getRealEstateDetail(anyString()))
                .willReturn(RealEstateInfo.builder()
                        .constructYear(Short.valueOf("2020"))
                        .contractYear(Short.valueOf("2021"))
                        .contractDay(Short.valueOf("22"))
                        .contractMonth(Short.valueOf("1"))
                        .floor("12")
                        .jeonYongArea("74")
                        .beopJeongDong("염창동")
                        .name("강변힐스테이트아파트")
                        .parcelNumber("299")
                        .beopJeongDongCode("11500")
                        .realEstateType(RealEstateType.APT_TRADE)
                        .build());
        //when
        //then
        mockMvc.perform(get("/realestate/detail?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constructYear").value("2020"))
                .andExpect(jsonPath("$.contractYear").value("2021"))
                .andExpect(jsonPath("$.contractDay").value("22"))
                .andExpect(jsonPath("$.contractMonth").value("1"))
                .andExpect(jsonPath("$.floor").value("12"))
                .andExpect(jsonPath("$.jeonYongArea").value("74"))
                .andExpect(jsonPath("$.beopJeongDong").value("염창동"))
                .andExpect(jsonPath("$.name").value("강변힐스테이트아파트"))
                .andExpect(jsonPath("$.parcelNumber").value("299"))
                .andExpect(jsonPath("$.beopJeongDongCode").value("11500"))
                .andExpect(jsonPath("$.realEstateType").value(RealEstateType.APT_TRADE.name()))
                .andDo(print());
    }

    @Test
    @DisplayName("실패-아파트 상세 조회-존재하지 않는 아파트")
    void getApartmentDetail_Fail() throws Exception {
        //given
        given(realEstateService.getRealEstateDetail(anyString()))
                .willThrow(new RealEstateException(REAL_ESTATE_NOT_FOUND));

        //when
        //then
        mockMvc.perform(get("/realestate/detail?id=1"))
                .andDo(print())
                .andExpect(jsonPath("$.errorCode")
                        .value("REAL_ESTATE_NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("부동산이 없습니다"))
                .andExpect(status().isOk());

    }

}
