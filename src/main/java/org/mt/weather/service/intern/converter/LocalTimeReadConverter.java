package org.mt.weather.service.intern.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;


public class LocalTimeReadConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        return LocalTime.parse(source);
    }
}
