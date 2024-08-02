package com.dsadara.hogangYes.realestate.dto;

import com.dsadara.hogangYes.realestate.entity.Rent;
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

    @ApiModelProperty(value = "갱신요구권사용", example = "사용")
    private String requestRenewalRight;
    @ApiModelProperty(value = "계약구분", example = "갱신")
    private String contractType;
    @ApiModelProperty(value = "계약기간", example = "23.08~25.08")
    private String contractPeriod;
    @ApiModelProperty(value = "보증금", example = "10000")
    private BigDecimal deposit;
    @ApiModelProperty(value = "종전 계약 보증금", example = "9000")
    private BigDecimal depositBefore;
    @ApiModelProperty(value = "월세", example = "200")
    private BigDecimal monthlyRent;
    @ApiModelProperty(value = "종전 계약 월세", example = "185")
    private BigDecimal monthlyRentBefore;
    @ApiModelProperty(value = "시군구", example = "강서구")
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
