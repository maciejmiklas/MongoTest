package org.mt.weather.rest;

import org.mt.weather.model.DayWeather;
import org.mt.weather.service.WeatherService;
import org.springframework.data.geo.Point;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/weather/day")
@Named
public class DayWeatherRestService {

    @Inject
    private WeatherService weatherService;

    // curl http://localhost:8080/rest/weather/day/one/2016-02-03/cc/MUC
    @GET
    @Path("one/{date}/cc/{countryCode}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public DayWeather getForecastForCountryNow(
	    @PathParam("date") String date,
	    @PathParam("countryCode") String countryCode
	    ) {
	DayWeather weather = weatherService.findWeatherForDayNear(countryCode, LocalDate.parse(date));
	return weather;
    }

    // curl http://localhost:8080/rest/weather/day/one/2016-02-03/near/lat/47.706065/lon/11.769104
    @GET
    @Path("one/{date}/near/lat/{lat}/lon/{lon}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public DayWeather getWeatherForDayNear(
	    @PathParam("date") String date, @PathParam("lat") double lat, @PathParam("lon") double lon) {
	DayWeather weather = weatherService.findWeatherForDayNear(new Point(lat, lon), LocalDate.parse(date));
	return weather;
    }

    // curl -X POST http://localhost:8080/rest/weather/day/gen
    @POST
    @Path("gen")
    public Response generate() {
	long start = System.currentTimeMillis();
	weatherService.generate();
	return Response.ok("OK - generation took: " + (System.currentTimeMillis() - start) + " ms\n").build();
    }
}
