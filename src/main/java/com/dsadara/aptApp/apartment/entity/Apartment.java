package com.dsadara.aptApp.apartment.entity;

import com.dsadara.aptApp.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Apartment extends BaseEntity {
    private String aptCode;         // 단지코드
    private String amountRecent;    // 최근 거래금액
    private Integer viewWeek;     // 주간 조회수
    private Integer viewTotal;      // 전체 조회수
    private String name;            // 단지명
    private String as1;             // 시, 도
    private String as2;             // 시, 군, 구
    private String as3;             // 읍, 면
    private String as4;             // 동, 리
    private String drmAddress;          // 도로명주소
    private LocalDateTime apprvDate;    // 사용승인일
    private Integer dongNo;             // 동수
    private Integer houseNo;            // 세대수
    private Integer parkingSpaceNo;     // 총 주차대수
    private String bjdCode;             // 법정동코드

    @Type(type="json")
    @Column(columnDefinition = "json")
    private List<String> feature;   // 특징
}
