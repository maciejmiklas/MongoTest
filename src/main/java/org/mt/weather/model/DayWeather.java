package org.mt.weather.model;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "day")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DayWeather {

    @Id
    private String id;

    private String countryCode;

    private LocalDate date;

    private Map<LocalTime, Weather> weatherByTime = new HashMap<>();

    public DayWeather() {
    }

    public DayWeather(String countryCode, LocalDate date) {
	this.id = countryCode + "_" + date;
    }

    public Map<LocalTime, Weather> getWeatherByTime() {
	return weatherByTime;
    }

    @Override
    public String toString() {
	return MoreObjects.toStringHelper(this)
		.add("id", id)
		.add("countryCode", countryCode)
		.add("date", date)
		.add("weatherByTime", weatherByTime)
		.toString();
    }
}
