package com.dsadara.aptApp.realestate.dto;

import com.dsadara.aptApp.realestate.type.RealEstateType;
import io.swagger.annotations.ApiModelProperty;
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
public class RealEstateInfoSimple {
    // client와 controller간의 정보를 주고 받는 응답 dto

    @ApiModelProperty(value = "id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "이름", example = "염창강변힐스테이트아파트")
    private String name;

    @ApiModelProperty(value = "법정동 코드", example = "11110")
    private Integer beopJeongDongCode;

    @ApiModelProperty(value = "법정동", example = "염창동")
    private String beopJeongDong;

    @ApiModelProperty(value = "지번", example = "299")
    private String parcelNumber;

    @ApiModelProperty(value = "부동산 타입", example = "APT_RENT")
    private RealEstateType realEstateType;   // 특징

    public static RealEstateInfoSimple fromDto(RealEstateDto realEstateDto) {
        return RealEstateInfoSimple.builder()
                .id(realEstateDto.getId())
                .name(realEstateDto.getName())
                .beopJeongDongCode(realEstateDto.getBeopJeongDongCode())
                .beopJeongDong(realEstateDto.getBeopJeongDong())
                .parcelNumber(realEstateDto.getParcelNumber())
                .realEstateType(realEstateDto.getRealEstateType())
                .build();
    }
}