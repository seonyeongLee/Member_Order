package com.api.idus.common.exception;

import java.util.List;

import com.api.idus.common.dto.ErrorFieldDto;
import com.api.idus.common.utility.ErrorUtility;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1000100000000000000L;

    private ErrorUtility.EXCEPTION_TYPE exceptionType;
    private String errorcode;
    private String message;
    private List<ErrorFieldDto> fieldErrorList;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }
    public BaseException(String message,String errorcode) {
        super(message);
        this.message = message;
        this.errorcode = errorcode;
    }

    public BaseException(ErrorUtility.EXCEPTION_TYPE exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public BaseException(ErrorUtility.EXCEPTION_TYPE exceptionType, String message, List<ErrorFieldDto> fieldErrorList) {
        super(message);
        this.exceptionType = exceptionType;
        this.message = message;
        this.fieldErrorList = fieldErrorList;
    }
}
