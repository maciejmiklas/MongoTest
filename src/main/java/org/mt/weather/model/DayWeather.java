package org.mt.weather.model;

import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "day")
@CompoundIndexes({ @CompoundIndex(name = "cityCodeIdx", def = "{'city.code':1, 'date':1}"),
	@CompoundIndex(name = "cityLocationIdx", def = "{'city.location':'2dsphere', 'date':1}") })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DayWeather {

    @Id
    private String id;

    private City city;

    private LocalDate date;

    private Map<LocalTime, Weather> weatherByTime = new HashMap<>();

    public DayWeather() {
    }

    public DayWeather(City city, LocalDate date) {
	this.id = city.getCode() + "_" + date;
	this.city = city;
	this.date = date;
    }

    public Map<LocalTime, Weather> getWeatherByTime() {
	return weatherByTime;
    }

    public String getId() {
	return id;
    }

    public City getCity() {
	return city;
    }

    public LocalDate getDate() {
	return date;
    }

    @Override
    public String toString() {
	return MoreObjects.toStringHelper(this)
		.add("id", id)
		.add("city", city)
		.add("date", date)
		.add("weatherByTime", weatherByTime)
		.toString();
    }
}
