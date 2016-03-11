package org.mt.weather.service.intern.repository;


import org.mt.weather.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherRepository extends MongoRepository<Weather, String> {

    Weather findById();
}
