package com.dsadara.aptApp.realestate.dto;

import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.type.RealEstateFeature;
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
public class RealEstateDto {
    private String aptCode;         // 단지코드
    private String name;            // 단지명
    private String as1;             // 시, 도
    private String as2;             // 시, 군, 구
    private String as3;             // 읍, 면
    private String as4;             // 동, 리

    private List<RealEstateFeature> feature;   // 특징

    public static RealEstateDto fromEntity(RealEstate realEstate) {
        return RealEstateDto.builder()
                .aptCode(realEstate.getAptCode())
                .name(realEstate.getName())
                .as1(realEstate.getAs1())
                .as2(realEstate.getAs2())
                .as3(realEstate.getAs3())
                .as4(realEstate.getAs4())
                .feature(realEstate.getFeature())
                .build();
    }
}
