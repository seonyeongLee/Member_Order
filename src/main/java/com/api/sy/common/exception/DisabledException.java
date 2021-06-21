package com.api.sy.common.exception;


import com.api.sy.common.utility.ErrorUtility;

public class DisabledException extends BaseException {
	private static final long serialVersionUID = 1000100000000000002L;

	public DisabledException(String message) {
		super(ErrorUtility.EXCEPTION_TYPE.USER_DATA_CONFLICT, message);
	}

}