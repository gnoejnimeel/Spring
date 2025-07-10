package com.spring.study.typeconverter.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

public interface ConversionService {
    //컨버팅이 가능한지 여부
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);
    boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor typeDescriptor);

    //컨버팅 기능
    <T> T convert(@Nullable Object source, Class<T> targetType);
    Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType);
}
