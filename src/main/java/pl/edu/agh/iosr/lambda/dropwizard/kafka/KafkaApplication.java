package pl.edu.agh.iosr.lambda.dropwizard.kafka;

import com.yammer.dropwizard.json.ObjectMapperFactory;
import kafka.javaapi.producer.Producer;


import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Puszek_SE on 2015-05-23.
 */
public class KafkaApplication {

    Logger logger = Logger.getLogger(this.getClass().getName());

    Producer<String,String> producer;
    private String topicName;


    public KafkaApplication (String topicName){
        this.topicName = topicName;
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream("configuration.prop");
            properties.load(inputStream);
            ProducerConfig config = new ProducerConfig(properties);
            producer = new Producer<>(config);
            logger.info("Broker address: "+(String)properties.get("metadata.broker.list"));
        }catch (IOException e){
            logger.warning(e.toString());
        }
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
