package com.econovation.overflow.common.support.respnose;

import lombok.experimental.UtilityClass;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@UtilityClass
public class ApiResponseHeader<T> {
	public <T> MultiValueMap<String, T> setHeader(String key, T value) {
		MultiValueMap<String, T> map = new LinkedMultiValueMap<>();
		map.add(key, value);

		return map;
	}
}
