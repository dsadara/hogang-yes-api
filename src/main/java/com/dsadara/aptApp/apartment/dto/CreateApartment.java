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
        @ApiModelProperty(example = "단지코드")
        private String aptCode;

        @ApiModelProperty(example = "단지명")
        private String name;

        @ApiModelProperty(example = "시, 도")
        private String as1;

        @ApiModelProperty(example = "시, 군, 구")
        private String as2;

        @ApiModelProperty(example = "읍, 면")
        private String as3;

        @ApiModelProperty(example = "동, 리")
        private String as4;

        @ApiModelProperty(example = "도로명주소")
        private String drmAddress;

        @ApiModelProperty(example = "사용승인일")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate apprvDate;

        @ApiModelProperty(example = "동수")
        private Integer dongNo;

        @ApiModelProperty(example = "세대수")
        private Integer houseNo;

        @ApiModelProperty(example = "총 주차대수")
        private Integer parkingSpaceNo;

        @ApiModelProperty(example = "법정동코드")
        private String bjdCode;

        @ApiModelProperty(example = "특징")
        private List<ApartmentFeature> feature;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        @ApiModelProperty(example = "단지코드")
        private String aptCode;

        @ApiModelProperty(example = "단지명")
        private String name;

        @ApiModelProperty(example = "시, 도")
        private String as1;

        @ApiModelProperty(example = "시, 군, 구")
        private String as2;

        @ApiModelProperty(example = "읍, 면")
        private String as3;

        @ApiModelProperty(example = "동, 리")
        private String as4;

        @ApiModelProperty(example = "특징")
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
