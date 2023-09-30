package com.econovation.overflow.common.support.converter;

import com.econovation.overflow.common.BaseEntity;
import com.econovation.overflow.common.marker.AbstractModel;

public interface AbstractEntityConverter<T extends BaseEntity, R extends AbstractModel> {
	R from(T t);

	T toEntity(R t);
}
