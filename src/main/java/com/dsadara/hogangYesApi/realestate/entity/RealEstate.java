package com.dsadara.hogangYesApi.realestate.entity;

import com.dsadara.hogangYesApi.realestate.type.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Comment("건축년도")
    private Short constructYear;
    @Comment("계약년")
    private Short contractYear;
    @Comment("계약일")
    private Short contractDay;
    @Comment("층")
    private Short floor;
    @Comment("전용면적, 계약면적")
    private String jeonYongArea;
    @Comment("법정동")
    private String beopJeongDong;
    @Comment("계약월")
    private Short contractMonth;
    @Comment("이름")
    private String name;
    @Comment("지번")
    private String parcelNumber;
    @Comment("부동산 종류")
    @Enumerated(EnumType.STRING)
    private RealEstateType realEstateType;
    @Comment("법정동 코드")
    private Integer beopJeongDongCode;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
