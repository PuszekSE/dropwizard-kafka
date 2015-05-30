package pl.edu.agh.iosr.lambda.dropwizard.kafka;

import com.yammer.dropwizard.json.ObjectMapperFactory;
import kafka.javaapi.producer.Producer;


import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Puszek_SE on 2015-05-23.
 */
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
