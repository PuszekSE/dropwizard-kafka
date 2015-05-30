package pl.edu.agh.iosr.lambda.dropwizard.stock;


import org.json.JSONObject;
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
public class StockResource {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private final StockConfiguration conf;
    private FieldsDescriptor stockFieldsDescriptor = new StockFieldsDescriptor();

    public StockResource(StockConfiguration conf) {
        this.conf = conf;
    }

    //private TridentValuesGenerator valuesGenerator = new TridentValuesGenerator(stockFieldsDescriptor.stockFields);
    private TridentValidator tridentValidator = new TridentValidator(stockFieldsDescriptor);

    private KafkaApplication kafkaApplication = new KafkaApplication();

    private String ACCEPTED = "OK";

    private String FAILED = "FAIL";

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
    public String inputMethod(String body) {

        logger.info("BODY: "+body);

        JSONObject jsonBody = new JSONObject(body);

        /*Values tridentValues = valuesGenerator.valuesInstance(jsonBody);

        if(tridentValues==null){
            logger.warning(FAILED);
            return FAILED;
        }*/

        /*TridentTuple tridentTuple = tridentValidator.tupleInstance(jsonBody);
        if(tridentTuple==null){
            logger.warning(FAILED);
            return FAILED;
        }*/

        if(tridentValidator.validateJson(jsonBody) && kafkaApplication.sendData(body)) {
            logger.info("Tuple sent");
        }else{
            logger.warning("Failed to send tuple");
        }
        return ACCEPTED;

    }

}