package pl.edu.agh.iosr.lambda.dropwizard.demo.hello;


import org.json.JSONObject;
import pl.edu.agh.iosr.lambda.dropwizard.demo.config.MessagesConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.logging.Logger;

@Path(value = "/hello")
public class HelloResource {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private final MessagesConfiguration conf;

    public HelloResource(MessagesConfiguration conf) {
        this.conf = conf;
    }

    @GET
    public String sayHello() {
        return conf.getHello();
    }

    @POST
    @Path("/{id}")
    public String inputMethod(String body,
                              @PathParam("id") long id,
                              @QueryParam("foo") String foo,
                              @HeaderParam("X-Auth-Token") String token,
                              @Context HttpServletRequest request) {
        String response;
        response  = "id = " + id + "\n";
        response += "body = " + body + "\n";
        response += "foo = " + foo + "\n";
        response += "token = " + token + "\n";
        response += "ip = " + request.getRemoteAddr() + "\n";
        JSONObject jsonBody = new JSONObject(body);
        logger.info(jsonBody.keySet().toString());
        return response;
    }
}