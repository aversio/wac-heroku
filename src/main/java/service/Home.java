package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by lap on 11-5-2017.
 */
@Path("/")
public class Home {
    @GET
    @Produces("text/html")
    public String giveText() {
        return "This is the homepage!<br> " +
                "Click <a href='./rest/countries'>here</a> to go to the countries<br>"+
                "Click <a href='./rest/countries/largestsurfaces'>here</a> to see the largest surfaces.<br>" +
                "Click <a href='./rest/countries/largestpopulations'>here</a> to see the largest populations.";
    }
}
