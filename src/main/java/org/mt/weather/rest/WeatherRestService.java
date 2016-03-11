package org.mt.weather.rest;

import org.mt.weather.model.Weather;
import org.mt.weather.service.WeatherService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/weather")
@Named
public class WeatherRestService {

    @Inject
    private WeatherService weatherService;

    // curl http://localhost:15121/rest/weather/now/cc/MUC
    @GET
    @Path("now/cc/{countryCode}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Weather getForecastForCountryNow(@PathParam("countryCode") String countryCode) {
	Weather weather = weatherService.findWeather(null, null);
	return weather;
    }

    @POST
    @Path("gen")
    public Response generate() {
	weatherService.geenrate();
	return Response.ok().build();
    }
}
