package org.mt.weather.service;

import org.mt.weather.model.DayWeather;
import org.mt.weather.model.Weather;
import org.mt.weather.service.intern.repository.WeatherRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Named
public class WeatherService {

    @Inject
    private MongoTemplate template;

    @Inject
    private WeatherGenerator generator;

    // @Inject
    private WeatherRepository weatherRepository;

    public Weather findWeather(String countryCode, LocalDateTime date) {
	return null;
    }

    public void geenrate() {
	DayWeather day = new DayWeather("MUC", LocalDate.now());
	day.getWeatherByTime().put(LocalTime.of(11,12,0), generator.generateWeather());
	day.getWeatherByTime().put(LocalTime.of(12,00,0), generator.generateWeather());
	day.getWeatherByTime().put(LocalTime.of(13,00,0), generator.generateWeather());
	day.getWeatherByTime().put(LocalTime.of(14,00,50), generator.generateWeather());
	template.save(day);
    }
}
