package com.dsadara.aptApp.transaction.entity;

import com.dsadara.aptApp.agent.entity.Agent;
import com.dsadara.aptApp.apartment.entity.Apartment;
import com.dsadara.aptApp.common.entity.BaseEntity;
import com.dsadara.aptApp.member.entity.Member;
import com.dsadara.aptApp.transaction.type.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction extends BaseEntity {
    private Long amount;                // 거래금액
    private String aptName;             // 단지명
    private String pictures_url;        // 내부사진주소
    private Double areaExclusive;       // 전용면적
    private Integer floor;              // 층

    @Lob
    private String description;         // 추가설명
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;    // 거래진행상황
    private Long buyerId;                           // 거래신청 회원번호

    @ManyToOne
    @JoinColumn(name="ID_APT")
    private Apartment apartment;                    // 아파트 ID
    @ManyToOne
    @JoinColumn(name="ID_AGENT")
    private Agent agent;                            // 중개사번호
    @ManyToOne
    @JoinColumn(name="ID_MEMBER")
    private Member member;                          // 회원번호
}
