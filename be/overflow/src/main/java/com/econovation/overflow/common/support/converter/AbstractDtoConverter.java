package com.econovation.overflow.common.support.converter;

import com.econovation.overflow.common.marker.AbstractDto;
import com.econovation.overflow.common.marker.AbstractModel;

public interface AbstractDtoConverter<T extends AbstractDto, R extends AbstractModel> {
	R from(T t);
}
