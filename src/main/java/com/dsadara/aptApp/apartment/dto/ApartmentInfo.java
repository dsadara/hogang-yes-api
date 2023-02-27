package com.dsadara.aptApp.apartment.dto;

import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentInfo {
    // client와 controller간의 정보를 주고 받는 응답 dto

    @ApiModelProperty(example = "단지코드")
    private String aptCode;

    @ApiModelProperty(example = "최근 거래금액")
    private Long amountRecent;

    @ApiModelProperty(example = "주간 조회수")
    private Integer viewWeek;

    @ApiModelProperty(example = "전체 조회수")
    private Integer viewTotal;

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
    private List<ApartmentFeature> feature;   // 특징

    public static ApartmentInfo fromEntity(Apartment apartment) {
        return ApartmentInfo.builder()
                .aptCode(apartment.getAptCode())
                .amountRecent(apartment.getAmountRecent())
                .viewWeek(apartment.getViewWeek())
                .viewTotal(apartment.getViewTotal())
                .name(apartment.getName())
                .as1(apartment.getAs1())
                .as2(apartment.getAs2())
                .as3(apartment.getAs3())
                .as4(apartment.getAs4())
                .drmAddress(apartment.getDrmAddress())
                .apprvDate(apartment.getApprvDate())
                .dongNo(apartment.getDongNo())
                .houseNo(apartment.getHouseNo())
                .parkingSpaceNo(apartment.getParkingSpaceNo())
                .bjdCode(apartment.getBjdCode())
                .feature(apartment.getFeature())
                .build();
    }
}
