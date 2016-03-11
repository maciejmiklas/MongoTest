package org.mt.weather.service.intern.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDateWriteConverter implements Converter<LocalDate, String> {

    @Override
    public String convert(LocalDate source) {
	return source.toString();
    }
}
