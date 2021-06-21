package com.api.sy.common.exception;

import com.api.sy.common.dto.ErrorFieldDto;
import com.api.sy.common.utility.ErrorUtility;

import java.util.List;

public class DataNotFoundException extends BaseException {
	private static final long serialVersionUID = 1000100000000000002L;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message) {
		super(ErrorUtility.EXCEPTION_TYPE.DATA_NOT_FOUND, message);
	}

	public DataNotFoundException(ErrorUtility.EXCEPTION_TYPE exceptionType, String message) {
		super(exceptionType, message);
	}

	public DataNotFoundException(ErrorUtility.EXCEPTION_TYPE exceptionType, String message, List<ErrorFieldDto> fieldErrorList) {
		super(exceptionType, message, fieldErrorList);
	}
}