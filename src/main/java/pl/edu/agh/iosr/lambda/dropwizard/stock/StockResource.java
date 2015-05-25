package pl.edu.agh.iosr.lambda.dropwizard.stock;


import org.json.JSONObject;
import pl.edu.agh.iosr.lambda.dropwizard.config.StockConfiguration;
import pl.edu.agh.iosr.lambda.dropwizard.config.StockFieldsContainer;
import pl.edu.agh.iosr.lambda.dropwizard.kafka.KafkaApplication;
import pl.edu.agh.iosr.lambda.dropwizard.kafka.TridentTupleGenerator;
import storm.trident.tuple.TridentTuple;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.logging.Logger;

@Path(value = "/")
public class StockResource {

    Logger logger = Logger.getLogger(this.getClass().getName());

    private final StockConfiguration conf;
    private static StockFieldsContainer stockFieldsContainer = new StockFieldsContainer();

    public StockResource(StockConfiguration conf) {
        this.conf = conf;
    }

    private TridentTupleGenerator tridentGenerator = new TridentTupleGenerator(stockFieldsContainer.stockFields);

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

        TridentTuple tridentTuple = tridentGenerator.tupleInstance(jsonBody);

        if(tridentTuple==null){
            logger.warning(FAILED);
            return FAILED;
        }

        kafkaApplication.sendTuple(tridentTuple);
        logger.info("Tuple sent");
        return ACCEPTED;

    }

}