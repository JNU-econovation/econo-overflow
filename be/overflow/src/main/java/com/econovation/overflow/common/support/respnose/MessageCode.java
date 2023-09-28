package com.econovation.overflow.common.support.respnose;

public enum MessageCode {
	CREATE("200", "생성 성공");
	private final String code;
	private final String message;

	MessageCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
