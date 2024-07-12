package com.dsadara.aptApp.realestate.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RealEstateType {

    APT_RENT("아파트전세"),
    APT_TRADE("아파트매매"),
    OFFICETEL_RENT("오피스텔전세"),
    DETACHEDHOUSE_RENT("단독다가구전세"),
    ROWHOUSE_RENT("연립다세대전세");

    private final String krName;

}