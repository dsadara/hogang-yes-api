package com.dsadara.aptApp.realestate.entity;

import com.dsadara.aptApp.realestate.type.RealEstateFeature;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<RealEstateFeature> feature;   // 특징
}
