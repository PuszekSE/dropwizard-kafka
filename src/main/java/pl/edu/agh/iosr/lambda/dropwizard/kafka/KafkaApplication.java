package pl.edu.agh.iosr.lambda.dropwizard.kafka;

import com.yammer.dropwizard.json.ObjectMapperFactory;
import kafka.javaapi.producer.Producer;


import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import storm.trident.tuple.TridentTuple;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Puszek_SE on 2015-05-23.
 */
public class KafkaApplication {


    private static final String topicName = "tuple";

    Logger logger = Logger.getLogger(this.getClass().getName());

    String broker = "localhost:9092";

    Producer<String,byte[]> producer;

    public KafkaApplication(){
        Properties props = new Properties();

        props.put("metadata.broker.list",broker);
        props.put("request.required.acks","1");
        props.put("partitioner.class","pl.edu.agh.iosr.lambda.dropwizard.kafka.TridentPartitioner");


        ProducerConfig config = new ProducerConfig(props);

        producer = new Producer<>(config);
        ObjectMapperFactory factory = new ObjectMapperFactory();

    }

    public boolean sendTuple(TridentTuple tuple){
        try{
            producer.send(new KeyedMessage<String, TridentTuple>("tuple",tuple);
        }catch (Exception e){
            logger.warning("Message failed: "+e);
            return false;
        }
        return true;
    }
}
