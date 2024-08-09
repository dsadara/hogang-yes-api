package com.dsadara.hogangYesApi.realestate.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RealEstateFeature {
    NEAR_STATION("역세권"),
    GOOD_SCHOOL("학군지역"),
    NEAR_RIVER("강변"),
    NEAR_STREAM("하천"),
    NEAR_PARK("공원"),
    NEAR_SUPERMARKET("대형마트"),
    COUPANG_ROCKET("로켓배송");

    private String krName;
}
