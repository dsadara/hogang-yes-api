package com.dsadara.aptApp.apartment.dto;

import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.apartment.type.ApartmentFeature;
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

    private String aptCode;         // 단지코드
    private Long amountRecent;    // 최근 거래금액
    private Integer viewWeek;     // 주간 조회수
    private Integer viewTotal;      // 전체 조회수
    private String name;            // 단지명
    private String as1;             // 시, 도
    private String as2;             // 시, 군, 구
    private String as3;             // 읍, 면
    private String as4;             // 동, 리
    private String drmAddress;          // 도로명주소
    private LocalDate apprvDate;    // 사용승인일
    private Integer dongNo;             // 동수
    private Integer houseNo;            // 세대수
    private Integer parkingSpaceNo;     // 총 주차대수
    private String bjdCode;             // 법정동코드

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
                .build();
    }
}
