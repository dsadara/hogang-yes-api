package com.dsadara.aptApp.realestate.exception;

import com.dsadara.aptApp.common.type.ErrorCode;
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
public class RealEstateException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;

    public RealEstateException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
