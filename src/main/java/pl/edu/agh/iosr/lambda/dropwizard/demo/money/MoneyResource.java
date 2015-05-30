package pl.edu.agh.iosr.lambda.dropwizard.demo.money;


import pl.edu.agh.iosr.lambda.dropwizard.demo.config.MoneyConfiguration;

import javax.ws.rs.*;
import java.util.logging.Logger;

@Path(value = "/money")
public class MoneyResource {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private final MoneyConfiguration conf;

    public MoneyResource(MoneyConfiguration conf) {
        this.conf = conf;
    }

    @GET
    public String sayMoney() {
        return conf.getMoney();
    }

    @POST
    public String inputMethod(String body) {
        String response;
        response  = "Money rates received! = true";
        /*JSONObject jsonBody = new JSONObject(body);
        for(String key : jsonBody.keySet()){
            logger.info(key+":"+jsonBody.getString(key));
        }*/

        return response;
    }
}