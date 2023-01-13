package com.dsadara.aptApp.member.entity;

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
public class Member extends BaseEntity {
    private String email;                   // 이메일
    private String password;                // 비밀번호
    private String name;                    // 이름
    private String emailAuthKey;            // 이메일인증키
    private LocalDateTime emailAuthDueDate; // 이메일인증키 유효기간
    private Boolean emailAuthYn;            // 이메일인증여부
    private String accessToken;             // 액세스 토큰
}
