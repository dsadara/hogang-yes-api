package com.dsadara.aptApp.agent.entity;

import com.dsadara.aptApp.common.entity.BaseEntity;
import com.dsadara.aptApp.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentAgent extends BaseEntity {
    @Lob
    private String content;     // 후기 내용
    private Integer rating;     // 평점

    @ManyToOne
    @JoinColumn(name="ID_MEMBER")
    private Member member;      // 회원번호
    @ManyToOne
    @JoinColumn(name="ID_AGENT")
    private Agent agent;        // 중개사번호
}
