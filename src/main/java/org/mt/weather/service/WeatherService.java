package org.mt.weather.service;

import org.mt.weather.model.DayWeather;
import org.mt.weather.service.intern.converter.DateUtils;
import org.mt.weather.service.intern.repository.WeatherRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Named
public class WeatherService {

    @Inject
    private MongoTemplate template;

    @Inject
    private WeatherGenerator generator;

    // @Inject
    private WeatherRepository weatherRepository;

    private String[] COUNTRY_CODES = {"MUC", "KRA", "WAR", "WRO", "CAN", "ONT", "WAS", "RMD", "MFO", "GHT", "IKL", "LMN",
            "KUI", "KAI", "OHI", "KAR", "MOR", "SAC", "HHI", "HMB", "STU", "MON", "ORI", "MIO", "LLK", "KKL", "OPN"};

    public DayWeather findWeatherForDay(String countryCode, LocalDate date) {
        DayWeather found = template.findOne(query(where("countryCode").is(countryCode).
                and("date").gte(DateUtils.asDate(date))), DayWeather.class);
        return found;
    }

    public void generate() {
        LocalDate startDate = LocalDate.now().minusDays(100);
        for (String countryCode : COUNTRY_CODES) {
            LocalDate date = startDate;
            for (int startDay = 1; startDay < 360; startDay++) {
                date = date.plusDays(1);
                DayWeather day = new DayWeather(countryCode, date);
                for (int hour = 0; hour <= 23; hour++) {
                    day.getWeatherByTime().put(LocalTime.of(hour, 0, 0), generator.generateWeather());
                }
                template.save(day);
            }
        }
    }
}
