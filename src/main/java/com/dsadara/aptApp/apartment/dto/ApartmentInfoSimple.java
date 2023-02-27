package com.dsadara.aptApp.apartment.dto;

import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentInfoSimple {
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
    private List<ApartmentFeature> feature;   // 특징

    public static ApartmentInfoSimple fromDto(ApartmentDto apartmentDto) {
        return ApartmentInfoSimple.builder()
                .aptCode(apartmentDto.getAptCode())
                .name(apartmentDto.getName())
                .as1(apartmentDto.getAs1())
                .as2(apartmentDto.getAs2())
                .as3(apartmentDto.getAs3())
                .as4(apartmentDto.getAs4())
                .feature(apartmentDto.getFeature())
                .build();
    }
}
