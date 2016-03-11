package org.mt.weather.service.intern.converter;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDateReadConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
	return LocalDate.parse(source);
    }
}
