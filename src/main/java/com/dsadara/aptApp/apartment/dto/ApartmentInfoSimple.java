package com.dsadara.aptApp.apartment.dto;

import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentInfoSimple {
    // client와 controller간의 정보를 주고 받는 응답 dto

    private String aptCode;         // 단지코드
    private String name;            // 단지명
    private String as1;             // 시, 도
    private String as2;             // 시, 군, 구
    private String as3;             // 읍, 면
    private String as4;             // 동, 리

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
