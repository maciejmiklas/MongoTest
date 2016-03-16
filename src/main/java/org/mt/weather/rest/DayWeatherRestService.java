package org.mt.weather.rest;

import org.mt.weather.model.DayWeather;
import org.mt.weather.service.WeatherService;

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

    // curl http://localhost:8080/rest/weather/day/one/cc/MUC/dd/2007-12-03
    @GET
    @Path("one/cc/{countryCode}/dd/{date}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DayWeather getForecastForCountryNow(@PathParam("countryCode") String countryCode,
                                               @PathParam("date") String date) {
        DayWeather weather = weatherService.findWeatherForDay(countryCode, LocalDate.parse(date));
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
