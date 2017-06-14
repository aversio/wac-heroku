package service;

import model.Country;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by lap on 25-4-2017.
 */
@Path("/countries")
public class WorldResource {
    @GET
    @Produces("application/json")
    public String getOrders() {
        WorldService service = ServiceProvider.getWorldService();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Country c : service.getAllCountries()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", c.getCode());
            job.add("country", c.getName());
            job.add("continent", c.getContinent());
            job.add("region", c.getRegion());
            job.add("surface", c.getSurface());
            job.add("population", c.getPopulation());
            job.add("latitude", c.getLatitude());
            job.add("longitude", c.getLongitude());

            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }


    @GET
    @Path("{code}")
    @Produces("application/json")
    public String getCountryInfo(@PathParam("code") String code) {
        WorldService service = ServiceProvider.getWorldService();
        Country country = service.getCountryByCode(code);

        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("code", country.getCode());
        job.add("country", country.getName());

        return job.build().toString();
    }

    @GET
    @Path("/telOp")
    @Produces("application/json")
    public String getByLargestSurfaces(@QueryParam("getal1") String getal1, @QueryParam("getal2") String getal2) {

        int getalA = Integer.parseInt(getal1);
        int getalB = Integer.parseInt(getal2);

        JsonObjectBuilder job = Json.createObjectBuilder();

        job.add("resultaat", getalA+getalB);
        return job.build().toString();
    }

    @GET
    @Path("/telOp2/{getal1}/{getal2}")
    @Produces("application/json")
    public String getNumbers(@PathParam("getal1") String getal1, @PathParam("getal2") String getal2) {

        int getalA = Integer.parseInt(getal1);
        int getalB = Integer.parseInt(getal2);

        JsonObjectBuilder job = Json.createObjectBuilder();

        job.add("resultaat", getalA+getalB);
        return job.build().toString();
    }

    @GET
    @Path("/largestsurfaces")
    @Produces("application/json")
    public String getByLargestSurfaces() {
        WorldService service = ServiceProvider.getWorldService();
        List<Country> country = service.get10LargestSurfaces();
        //JsonArrayBuilder jab = Json.createArrayBuilder();

        JsonObjectBuilder job = Json.createObjectBuilder();

        for (Country c : service.getAllCountries()) {
            //JsonObjectBuilder job =
            job.add("country", c.getName());
            job.add("surface", c.getSurface());
            //jab.add(job);
        }

        return job.build().toString();
    }

    @GET
    @Path("/largestpopulations")
    @Produces("application/json")
    public String getByLargestPopulations() {
        WorldService service = ServiceProvider.getWorldService();
        List<Country> country = service.get10LargestPopulations();
        //JsonArrayBuilder jab = Json.createArrayBuilder();

        JsonObjectBuilder job = Json.createObjectBuilder();
        for (Country c : service.getAllCountries()) {
            //JsonObjectBuilder job =
            job.add("country", c.getName());
            job.add("surface", c.getPopulation());
            //jab.add(job);
        }

        return job.build().toString();
    }
}
