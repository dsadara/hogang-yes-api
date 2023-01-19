package com.dsadara.aptApp.apartment.dto;

import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class CreateApartment {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private String aptCode;         // 단지코드
        private String name;            // 단지명
        private String as1;             // 시, 도
        private String as2;             // 시, 군, 구
        private String as3;             // 읍, 면
        private String as4;             // 동, 리
        private String drmAddress;          // 도로명주소
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate apprvDate;    // 사용승인일
        private Integer dongNo;             // 동수
        private Integer houseNo;            // 세대수
        private Integer parkingSpaceNo;     // 총 주차대수
        private String bjdCode;             // 법정동코드
        private List<ApartmentFeature> feature;   // 특징
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String aptCode;         // 단지코드
        private String name;            // 단지명
        private String as1;             // 시, 도
        private String as2;             // 시, 군, 구
        private String as3;             // 읍, 면
        private String as4;             // 동, 리

        private List<ApartmentFeature> feature;   // 특징

        public static Response from(ApartmentDto apartmentDto) {
            return Response.builder()
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
}
