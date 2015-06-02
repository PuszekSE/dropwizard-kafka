# dropwizard-kafka
Dropwizard and Kafka integration module

Parts of the system:
- stock market data stream
- data descriptors
- datą validator and serializer
- standalone Dropwizard instance with stock market reader and Kafka producer

Main data descriptor interface:
package pl.edu.agh.iosr.lambda.dropwizard.config.iface;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Puszek_SE on 2015-05-30.
 */
public interface FieldsDescriptor {

    public String getIdColumnName();

    public String getTimestamp();

    public String[] getAllFields();

    public Map<String,String> extractMapFromJson(String body);

}

It contains full description of the data stream.
List of all required fields, information about timestamp and Id fields and converter from json data to K,V map.

TridentValidator, based on descriptor interface
package pl.edu.agh.iosr.lambda.dropwizard.kafka;

import org.json.JSONObject;
import pl.edu.agh.iosr.lambda.dropwizard.config.iface.FieldsDescriptor;

import java.util.logging.Logger;

/**
 * Created by Puszek_SE on 2015-05-25.
 */
public class TridentValidator {

    Logger logger = Logger.getLogger(this.getClass().getName());


    FieldsDescriptor fields;

    public TridentValidator(FieldsDescriptor fields){
        this.fields = fields;
    }

    public boolean invalidJson(JSONObject body){

        if(null == body){
            return true;
        }

        for(String field : fields.getAllFields()){
            if(null == body.get(field)){
                logger.warning("Missing attribute: "+field);
                return true;
            }
        }
        return false;
    }

}


Dropwizard

To change the input dataset, user has to provide own implementation of dropwizard configuration according to one used for stock exchange along with implementation of FieldsDescriptor interface.

Key components of one used for market data:

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

KafkaApplication - Kafka producer:

public class KafkaApplication {


    private static final String topicName = "tuple";

    Logger logger = Logger.getLogger(this.getClass().getName());

    String broker = "localhost:9092";

    Producer<String,String> producer;

    public KafkaApplication(){
        Properties props = new Properties();

        props.put("metadata.broker.list",broker);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");



        ProducerConfig config = new ProducerConfig(props);

        producer = new Producer<>(config);

    }

    public boolean sendData(String validatedJsonBody){
        logger.info("Sending message");
        try{
            producer.send(new KeyedMessage<String, String>(topicName,validatedJsonBody));
        }catch (Exception e){
            logger.warning("Message failed: "+e);
            return false;
        }
        return true;
    }
}

It may be required to configure own brokers and topic. 
