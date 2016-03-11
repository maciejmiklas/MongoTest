package org.mt.weather.service;

import org.mt.weather.model.Condition;
import org.mt.weather.model.Weather;

import javax.inject.Named;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

@Named
public class WeatherGenerator {

    SecureRandom random = new SecureRandom();

    public Weather generateWeather() {
	Weather weather = new Weather();
	weather.setHumidity(genInt(0, 100));
	weather.setPressure(genInt(900, 1200));
	int tempMin = genInt(-20, 20);
	weather.setTempMin(tempMin);
	weather.setTempMax(genInt(tempMin, 50));
	weather.setCondition(Condition.values()[Math.max(0, random.nextInt(5))]);
	return weather;
    }

    private int genInt(int min, int max) {
	return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
