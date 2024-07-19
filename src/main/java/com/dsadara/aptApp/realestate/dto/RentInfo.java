package com.dsadara.aptApp.realestate.dto;

import com.dsadara.aptApp.realestate.entity.Rent;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentInfo {

    @ApiModelProperty(value = "갱신요구권사용", example = "")
    private String requestRenewalRight;
    @ApiModelProperty(value = "계약구분", example = "")
    private String contractType;
    @ApiModelProperty(value = "계약기간", example = "")
    private String contractPeriod;
    @ApiModelProperty(value = "보증금", example = "")
    private BigDecimal deposit;
    @ApiModelProperty(value = "종전 계약 보증금", example = "")
    private BigDecimal depositBefore;
    @ApiModelProperty(value = "월세", example = "")
    private BigDecimal monthlyRent;
    @ApiModelProperty(value = "종전 계약 월세", example = "")
    private BigDecimal monthlyRentBefore;
    @ApiModelProperty(value = "시군구", example = "")
    private String siGunGu;

    public static RentInfo fromEntity(Rent rent) {
        return RentInfo.builder()
                .requestRenewalRight(rent.getRequestRenewalRight())
                .contractType(rent.getContractType())
                .contractPeriod(rent.getContractPeriod())
                .deposit(rent.getDeposit())
                .depositBefore(rent.getDepositBefore())
                .monthlyRent(rent.getMonthlyRent())
                .monthlyRentBefore(rent.getMonthlyRentBefore())
                .siGunGu(rent.getSiGunGu())
                .build();
    }
}
