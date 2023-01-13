package com.dsadara.aptApp.agent.entity;

import com.dsadara.aptApp.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agent extends BaseEntity {
    private String email;               // 이메일
    private String password;            // 비밀번호
    private String name;                // 이름
    private Double rating_avg;          // 평점 평균
    private String profile_picture_url; // 프로필사진
    private String phone;               // 연락처
    private String as4;                 // 담당 동리
    private String email_auth_key;      // 이메일 인증키
    private LocalDateTime email_auth_key_due_date; // 이메일 인증키 유효기간
    private Boolean email_auth_yn;      // 이메일 인증여부
    private String accessToken;         // 액세스 토큰
}
