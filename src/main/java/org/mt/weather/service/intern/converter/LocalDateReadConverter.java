package org.mt.weather.service.intern.converter;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.util.Date;

public class LocalDateReadConverter implements Converter<Date, LocalDate> {

    @Override
    public LocalDate convert(Date source) {
        return DateUtils.asLocalDate(source);
    }
}
