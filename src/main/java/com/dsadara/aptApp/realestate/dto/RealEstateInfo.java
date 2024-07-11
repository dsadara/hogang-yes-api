package com.dsadara.aptApp.realestate.dto;

import com.dsadara.aptApp.realestate.entity.RealEstate;
import com.dsadara.aptApp.realestate.type.RealEstateFeature;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealEstateInfo {
    // client와 controller간의 정보를 주고 받는 응답 dto

    @ApiModelProperty(value = "단지코드", example = "sampleCode")
    private String aptCode;

    @ApiModelProperty(value = "최근 거래금액", example = "90000L")
    private Long amountRecent;

    @ApiModelProperty(value = "주간 조회수", example = "0")
    private Integer viewWeek;

    @ApiModelProperty(value = "전체 조회수", example = "0")
    private Integer viewTotal;

    @ApiModelProperty(value = "단지명", example = "아파트1")
    private String name;

    @ApiModelProperty(value = "시, 도", example = "**시")
    private String as1;

    @ApiModelProperty(value = "시, 군, 구", example = "**구")
    private String as2;

    @ApiModelProperty(value = "읍, 면", example = "**읍")
    private String as3;

    @ApiModelProperty(value = "동, 리", example = "**동")
    private String as4;

    @ApiModelProperty(value = "도로명주소", example = "도로명주소1")
    private String drmAddress;

    @ApiModelProperty(value = "사용승인일", example = "2001-01-01")
    private LocalDate apprvDate;

    @ApiModelProperty(value = "동수", example = "10")
    private Integer dongNo;

    @ApiModelProperty(value = "세대수", example = "500")
    private Integer houseNo;

    @ApiModelProperty(value = "총 주차대수", example = "1000")
    private Integer parkingSpaceNo;

    @ApiModelProperty(value = "법정동코드", example = "법정동코드1")
    private String bjdCode;

    @ApiModelProperty(value = "특징", example = "[NEAR_STATION, GOOD_SCHOOL, NEAR_RIVER]")
    private List<RealEstateFeature> feature;   // 특징

    public static RealEstateInfo fromEntity(RealEstate realEstate) {
        return RealEstateInfo.builder()
                .aptCode(realEstate.getAptCode())
                .amountRecent(realEstate.getAmountRecent())
                .viewWeek(realEstate.getViewWeek())
                .viewTotal(realEstate.getViewTotal())
                .name(realEstate.getName())
                .as1(realEstate.getAs1())
                .as2(realEstate.getAs2())
                .as3(realEstate.getAs3())
                .as4(realEstate.getAs4())
                .drmAddress(realEstate.getDrmAddress())
                .apprvDate(realEstate.getApprvDate())
                .dongNo(realEstate.getDongNo())
                .houseNo(realEstate.getHouseNo())
                .parkingSpaceNo(realEstate.getParkingSpaceNo())
                .bjdCode(realEstate.getBjdCode())
                .feature(realEstate.getFeature())
                .build();
    }
}
