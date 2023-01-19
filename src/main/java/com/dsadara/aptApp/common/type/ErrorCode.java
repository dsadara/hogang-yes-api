package com.dsadara.aptApp.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Apartment
    APARTMENT_NOT_FOUND("아파트가 없습니다"),
    APARTMENT_ALREADY_EXIST("이미 아파트가 존재합니다"),

    // Common
    INVALID_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다.");

    private String description;
}
