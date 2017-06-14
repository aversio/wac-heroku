package model;

/**
 * Created by lap on 28-4-2017.
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/test")
public class Main {
    @GET
    @Produces("text/plain")
    public String getText() {
        return "test1337";
    }
}
