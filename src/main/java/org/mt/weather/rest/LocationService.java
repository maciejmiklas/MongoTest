package org.mt.weather.rest;

import org.mt.weather.model.City;
import org.mt.weather.service.WeatherService;
import org.springframework.data.geo.Point;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/location")
@Named
public class LocationService {

    @Inject
    private WeatherService weatherService;

    // curl http://localhost:8080/rest/location/city/near/lat/47.706065/lon/11.769104
    @GET
    @Path("city/near/lat/{lat}/lon/{lon}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public City getCityCodeNear(@PathParam("lat") double lat, @PathParam("lon") double lon) {
	City weather = weatherService.findCityNear(new Point(lat, lon));
	return weather;
    }
}
