package Rest;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/home")
public class HomeRest {
    static final Gson gson = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(){
        return Response.ok(gson.toJson("Home"), MediaType.APPLICATION_JSON).build();
    }

}
