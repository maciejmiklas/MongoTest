package org.mt.weather.service.intern.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.util.Date;

public class LocalDateWriteConverter implements Converter<LocalDate, Date> {

    @Override
    public Date convert(LocalDate source) {
        return DateUtils.asDate(source);
    }
}
