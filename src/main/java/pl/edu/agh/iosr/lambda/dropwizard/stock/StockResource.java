package pl.edu.agh.iosr.lambda.dropwizard.stock;


import org.json.JSONObject;
import pl.edu.agh.iosr.lambda.dropwizard.Resource;
import pl.edu.agh.iosr.lambda.dropwizard.config.StockConfiguration;
import pl.edu.agh.iosr.lambda.dropwizard.config.StockFieldsDescriptor;
import pl.edu.agh.iosr.lambda.dropwizard.config.iface.FieldsDescriptor;
import pl.edu.agh.iosr.lambda.dropwizard.kafka.KafkaApplication;
import pl.edu.agh.iosr.lambda.dropwizard.kafka.TridentValidator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.logging.Logger;

@Path(value = "/")
public class StockResource extends Resource<StockFieldsDescriptor> {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private final StockConfiguration conf;

    public StockResource(StockConfiguration conf) {
        fieldsDescriptor = new StockFieldsDescriptor();
        tridentValidator = new TridentValidator(fieldsDescriptor);
        kafkaApplication = new KafkaApplication("tuple");
        this.conf = conf;
    }

    private static final String ACCEPTED = "OK";

    private static final String FAILED = "FAIL";

    @GET
    public String rootStock(){
        return conf.getStock();
    }

    @GET
    @Path(value = "stock")
    public String sayStock() {
        return conf.getStock();
    }

    @POST
    @Path(value = "stock")
    @Override
    public String inputMethod(String body) {

        logger.info("BODY: "+body);

        JSONObject jsonBody = new JSONObject(body);

        if(tridentValidator.invalidJson(jsonBody)){
            logger.warning("DATA INVALID");
            return FAILED;
        }

        if(kafkaApplication.sendData(body)) {
            logger.info("Tuple sent");
        }else{
            logger.warning("Failed to send tuple");
            return FAILED;
        }
        return ACCEPTED;

    }

}