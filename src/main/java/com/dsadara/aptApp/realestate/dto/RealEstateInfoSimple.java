package com.dsadara.aptApp.realestate.dto;

import com.dsadara.aptApp.realestate.type.RealEstateFeature;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealEstateInfoSimple {
    // client와 controller간의 정보를 주고 받는 응답 dto

    @ApiModelProperty(value = "단지코드", example = "아파트코드0")
    private String aptCode;

    @ApiModelProperty(value = "단지명", example = "아파트0")
    private String name;

    @ApiModelProperty(value = "시, 도", example = "**시")
    private String as1;

    @ApiModelProperty(value = "시, 군, 구", example = "**구")
    private String as2;

    @ApiModelProperty(value = "읍, 면", example = "**읍")
    private String as3;

    @ApiModelProperty(value = "동, 리", example = "**동")
    private String as4;

    @ApiModelProperty(value = "특징", example = "[COUPANG_ROCKET, NEAR_STATION]")
    private List<RealEstateFeature> feature;   // 특징

    public static RealEstateInfoSimple fromDto(RealEstateDto realEstateDto) {
        return RealEstateInfoSimple.builder()
                .aptCode(realEstateDto.getAptCode())
                .name(realEstateDto.getName())
                .as1(realEstateDto.getAs1())
                .as2(realEstateDto.getAs2())
                .as3(realEstateDto.getAs3())
                .as4(realEstateDto.getAs4())
                .feature(realEstateDto.getFeature())
                .build();
    }
}
