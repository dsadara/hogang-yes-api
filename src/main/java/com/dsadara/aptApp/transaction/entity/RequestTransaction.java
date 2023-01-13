package com.dsadara.aptApp.transaction.entity;

import com.dsadara.aptApp.agent.entity.Agent;
import com.dsadara.aptApp.common.entity.BaseEntity;
import com.dsadara.aptApp.member.entity.Member;
import com.dsadara.aptApp.transaction.type.RequestStatus;
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
public class RequestTransaction extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;    // 신청상태

    @ManyToOne
    @JoinColumn(name="ID_TRANSACTION")
    private Transaction transaction;        // 매물 ID
    @ManyToOne
    @JoinColumn(name="ID_MEMBER")
    private Member member;                  // 회원번호
    @ManyToOne
    @JoinColumn(name="ID_AGENT")
    private Agent agent;                    // 중개사번호

}
