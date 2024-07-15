package com.dsadara.aptApp.realestate.controller;

import com.dsadara.aptApp.realestate.dto.RealEstateDto;
import com.dsadara.aptApp.realestate.dto.RealEstateInfo;
import com.dsadara.aptApp.realestate.exception.RealEstateException;
import com.dsadara.aptApp.realestate.service.RealEstateService;
import com.dsadara.aptApp.realestate.type.RealEstateType;
import org.junit.jupiter.api.Disabled;
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
import static com.dsadara.aptApp.realestate.type.RealEstateFeature.GOOD_SCHOOL;
import static com.dsadara.aptApp.realestate.type.RealEstateFeature.NEAR_STATION;
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

    @Disabled
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
        mockMvc.perform(get("/realestate/detail?aptCode=sampleCode"))
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

    @Disabled
    @Test
    @DisplayName("실패-아파트 상세 조회-존재하지 않는 아파트")
    void getApartmentDetail_Fail() throws Exception {
        //given
        given(realEstateService.getRealEstateDetail(anyString()))
                .willThrow(new RealEstateException(APARTMENT_NOT_FOUND));

        //when
        //then
        mockMvc.perform(get("/realestate/detail?aptCode=sampleCode"))
                .andDo(print())
                .andExpect(jsonPath("$.errorCode")
                        .value("APARTMENT_NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage")
                        .value("아파트가 없습니다"))
                .andExpect(status().isOk());

    }

}
