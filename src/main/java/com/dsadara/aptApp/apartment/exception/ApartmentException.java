package com.dsadara.aptApp.apartment.exception;

import com.dsadara.aptApp.common.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;

    public ApartmentException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
