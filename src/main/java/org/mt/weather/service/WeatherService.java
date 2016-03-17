package org.mt.weather.service;

import org.mt.weather.model.City;
import org.mt.weather.model.DayWeather;
import org.mt.weather.service.intern.converter.DateUtils;
import org.mt.weather.service.intern.repository.WeatherRepository;
import org.springframework.data.geo.Point;
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

    private City[] CITIES = { City.ofCoordinates("MUC", 48.122101, 11.604309),
	    City.ofCoordinates("ERD", 48.312428, 11.928406),
	    City.ofCoordinates("FRS", 48.396385, 11.747131),
	    City.ofCoordinates("DAC", 48.246626, 11.439514),
	    City.ofCoordinates("STA", 47.982568, 11.351624),
	    City.ofCoordinates("BAD", 47.735629, 11.455994),
	    City.ofCoordinates("TNS", 47.706065, 11.769104),
	    City.ofCoordinates("LNB", 48.041365, 10.846252),
	    City.ofCoordinates("BAD", 47.931066, 10.615540),
	    City.ofCoordinates("KEM", 47.687579, 10.346375),
	    City.ofCoordinates("MEN", 48.000950, 10.148621),
	    City.ofCoordinates("ULM", 48.374497, 9.967346),
	    City.ofCoordinates("ASB", 48.803246, 11.966858),
	    City.ofCoordinates("NRD", 48.843028, 10.439758),
	    City.ofCoordinates("WUR", 49.781264, 9.950867),
	    City.ofCoordinates("LPZ", 51.340907, 12.367859),
	    City.ofCoordinates("SLZ", 52.845912, 11.197815),
	    City.ofCoordinates("WAR", 53.543572, 12.642517),
	    City.ofCoordinates("SET", 53.422628, 14.510193),
	    City.ofCoordinates("NWS", 55.178868, 11.796570)
    };

    public DayWeather findWeatherForDayNear(String countryCode, LocalDate date) {
	DayWeather found = template.findOne(query(where("city.code").is(countryCode).
		and("date").gte(DateUtils.asDate(date))), DayWeather.class);
	return found;
    }

    public DayWeather findWeatherForDayNear(Point location, LocalDate date) {
	LocalDate prevDay = date.minusDays(1);

	DayWeather found = template.findOne(
		query(where("date").gte(DateUtils.asDate(prevDay)).lte(DateUtils.asDate(date))
			.and("city.location")
			.near(location))
		,
		DayWeather.class);
	return found;
    }

    public City findCityNear(Point location) {
	DayWeather found = template.findOne(query(where("city.location").near(location)), DayWeather.class);
	if (found == null) {
	    return null;
	}
	return found.getCity();
    }

    public void generate() {
	LocalDate startDate = LocalDate.now().minusDays(100);
	for (City city : CITIES) {
	    LocalDate date = startDate;
	    for (int startDay = 1; startDay < 360; startDay++) {
		date = date.plusDays(1);
		DayWeather day = new DayWeather(city, date);
		for (int hour = 0; hour <= 23; hour++) {
		    day.getWeatherByTime().put(LocalTime.of(hour, 0, 0), generator.generateWeather());
		}
		template.save(day);
	    }
	}
    }
}
