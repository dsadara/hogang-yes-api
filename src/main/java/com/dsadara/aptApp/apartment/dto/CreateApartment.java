package com.dsadara.aptApp.apartment.dto;

import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import io.swagger.annotations.ApiModelProperty;
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

        @ApiModelProperty(value = "도로명주소", example = "도로명주소0")
        private String drmAddress;

        @ApiModelProperty(value = "사용승인일", example = "2001-01-01")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate apprvDate;

        @ApiModelProperty(value = "동수", example = "10")
        private Integer dongNo;

        @ApiModelProperty(value = "세대수", example = "500")
        private Integer houseNo;

        @ApiModelProperty(value = "총 주차대수", example = "1000")
        private Integer parkingSpaceNo;

        @ApiModelProperty(value = "법정동코드", example = "법정동코드0")
        private String bjdCode;

        @ApiModelProperty(value = "특징", example = "[COUPANG_ROCKET, NEAR_STATION]")
        private List<ApartmentFeature> feature;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
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
        private List<ApartmentFeature> feature;

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
