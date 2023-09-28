package com.econovation.overflow.common.support;

import com.econovation.overflow.common.marker.AbstractResponseDto;

public interface AbstractUseCase<T, R extends AbstractResponseDto> {
	R execute(T t);
}
