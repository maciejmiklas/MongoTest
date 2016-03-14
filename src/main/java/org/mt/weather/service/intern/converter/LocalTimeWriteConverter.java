package org.mt.weather.service.intern.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;


public class LocalTimeWriteConverter implements Converter<LocalTime, String> {
    @Override
    public String convert(LocalTime source) {
        return source.toString();
    }
}
