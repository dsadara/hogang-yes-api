package com.dsadara.aptApp.realestate.dto;

import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.type.RealEstateType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealEstateInfo {
    // client와 controller간의 정보를 주고 받는 응답 dto

    @ApiModelProperty(value = "건축년도", example = "2020")
    private Short constructYear;
    @ApiModelProperty(value = "계약년", example = "2023")
    private Short contractYear;
    @ApiModelProperty(value = "계약일", example = "25")
    private Short contractDay;
    @ApiModelProperty(value = "층", example = "12")
    private Short contractMonth;
    @ApiModelProperty(value = "이름", example = "강변힐스테이트아파트")
    private Short floor;
    @ApiModelProperty(value = "전용면적", example = "102")
    private String jeonYongArea;
    @ApiModelProperty(value = "법정동", example = "염창동")
    private String beopJeongDong;
    @ApiModelProperty(value = "계약월", example = "12")
    private String name;
    @ApiModelProperty(value = "지번", example = "299")
    private String parcelNumber;
    @ApiModelProperty(value = "법정동 코드", example = "11110")
    private Integer beopJeongDongCode;
    @ApiModelProperty(value = "부동산 타입", example = "APT_RENT")
    private RealEstateType realEstateType;

    public static RealEstateInfo fromEntity(RealEstate realEstate) {
        return RealEstateInfo.builder()
                .constructYear(realEstate.getConstructYear())
                .contractYear(realEstate.getContractYear())
                .contractDay(realEstate.getContractDay())
                .contractMonth(realEstate.getContractMonth())
                .floor(realEstate.getFloor())
                .jeonYongArea(realEstate.getJeonYongArea())
                .beopJeongDong(realEstate.getBeopJeongDong())
                .name(realEstate.getName())
                .parcelNumber(realEstate.getParcelNumber())
                .beopJeongDongCode(realEstate.getBeopJeongDongCode())
                .realEstateType(realEstate.getRealEstateType())
                .build();
    }
}