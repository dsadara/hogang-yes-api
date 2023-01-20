package com.dsadara.aptApp.apartment.dto;

import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentDto {
    private String aptCode;         // 단지코드
    private String name;            // 단지명
    private String as1;             // 시, 도
    private String as2;             // 시, 군, 구
    private String as3;             // 읍, 면
    private String as4;             // 동, 리

    private List<ApartmentFeature> feature;   // 특징

    public static ApartmentDto fromEntity(Apartment apartment) {
        return ApartmentDto.builder()
                .aptCode(apartment.getAptCode())
                .name(apartment.getName())
                .as1(apartment.getAs1())
                .as2(apartment.getAs2())
                .as3(apartment.getAs3())
                .as4(apartment.getAs4())
                .feature(apartment.getFeature())
                .build();
    }
}
