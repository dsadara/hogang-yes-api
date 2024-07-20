package com.dsadara.aptApp.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // RealEstate
    REAL_ESTATE_NOT_FOUND("부동산 데이터가 없습니다."),
    RENT_NOT_FOUND("전월세 데이터가 없습니다."),
    SALE_NOT_FOUND("매매 데이터가 없습니다."),

    // Common
    INVALID_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다.");

    private String description;
}
