package com.dsadara.aptApp.realestate.dto;

import com.dsadara.aptApp.realestate.entity.Sale;
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
public class SaleInfo {

    @ApiModelProperty(value = "해제사유 발생일", example = "")
    private String CancelDealDay;
    @ApiModelProperty(value = "해제 여부", example = "")
    private String CancelDealType;
    @ApiModelProperty(value = "중개사 소재지", example = "")
    private String agentAddress;
    @ApiModelProperty(value = "거래금액", example = "")
    private BigDecimal dealAmount;
    @ApiModelProperty(value = "거래유형", example = "")
    private String dealType;

    public static SaleInfo fromEntity(Sale sale) {
        return SaleInfo.builder()
                .CancelDealDay(sale.getCancelDealDay())
                .CancelDealType(sale.getCancelDealType())
                .agentAddress(sale.getAgentAddress())
                .dealAmount(sale.getDealAmount())
                .dealType(sale.getDealType())
                .build();
    }
}
