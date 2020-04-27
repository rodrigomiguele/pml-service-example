package rmiguele.transaction.validation.resource

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/")
class BaseResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun helloWorld(): String {
        return "{\"status\": \"UP\"}"
    }
}