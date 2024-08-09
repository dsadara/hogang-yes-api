package com.dsadara.hogangYesApi.realestate.dto;

import com.dsadara.hogangYesApi.realestate.entity.RealEstate;
import com.dsadara.hogangYesApi.realestate.type.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RealEstateDto {

    private Integer id;                          // 식별자
    private Integer beopJeongDongCode;           // 법정동 코드
    private String beopJeongDong;               // 법정동
    private String name;                        // 이름
    private String parcelNumber;                // 지번
    private RealEstateType realEstateType;              // 부동산 타입

    public static RealEstateDto fromEntity(RealEstate realEstate) {
        return RealEstateDto.builder()
                .id(realEstate.getId())
                .beopJeongDongCode(realEstate.getBeopJeongDongCode())
                .beopJeongDong(realEstate.getBeopJeongDong())
                .name(realEstate.getName())
                .parcelNumber(realEstate.getParcelNumber())
                .realEstateType(realEstate.getRealEstateType())
                .build();
    }
}
