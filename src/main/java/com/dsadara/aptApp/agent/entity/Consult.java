package com.dsadara.aptApp.agent.entity;

import com.dsadara.aptApp.common.entity.BaseEntity;
import com.dsadara.aptApp.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consult extends BaseEntity {
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String chat_log;    // 채팅기록
    private Boolean finish_yn;  // 종료여부

    @ManyToOne
    @JoinColumn(name="ID_MEMBER")
    private Member member;      // 회원번호
    @ManyToOne
    @JoinColumn(name="ID_AGENT")
    private Agent agent;        // 중개사번호
}