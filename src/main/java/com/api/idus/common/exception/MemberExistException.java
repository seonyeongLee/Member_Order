package com.api.idus.common.exception;

import com.api.idus.common.dto.ErrorFieldDto;
import com.api.idus.common.utility.ErrorUtility;

import java.util.List;

public class MemberExistException extends BaseException {
	private static final long serialVersionUID = 1000100000000000002L;

	public MemberExistException() {
		super();
	}

	public MemberExistException(String message) {
		super(ErrorUtility.EXCEPTION_TYPE.USER_DATA_CONFLICT, message);
	}

	public MemberExistException(ErrorUtility.EXCEPTION_TYPE exceptionType, String message) {
		super(exceptionType, message);
	}

	public MemberExistException(ErrorUtility.EXCEPTION_TYPE exceptionType, String message, List<ErrorFieldDto> fieldErrorList) {
		super(exceptionType, message, fieldErrorList);
	}
}