package com.dsadara.aptApp.transaction.entity;

import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransactionReal extends BaseEntity {
    private Long amount;            // 거래금액
    private String aptName;         // 단지명
    private Double areaExclusive;   // 전용면적
    private Integer floor;          // 층
    private LocalDateTime contractDate;     // 계약일
    private String bjdCode;                 // 법정동코드

    @ManyToOne
    @JoinColumn(name="ID_APT")
    private Apartment apartment;            // 아파트ID
    @OneToOne
    @JoinColumn(name="ID_TRANSACTION")
    private Transaction transaction;        // 매물ID
}
