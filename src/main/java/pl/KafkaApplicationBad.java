package pl;

import backtype.storm.tuple.Values;

import kafka.javaapi.producer.Producer;


import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Puszek_SE on 2015-05-23.
 */
public class KafkaApplicationBad {

    private static Logger logger = Logger.getLogger(KafkaApplicationBad.class.getName());

    private static final String topicName = "tuple";


    String broker = "localhost:9092";
    Producer <String,Values> producer;

    public KafkaApplicationBad(){

        Properties props = new Properties();

        props.put("metadata.broker.list",broker);
        props.put("request.required.acks","1");
        props.put("partitioner.class","pl.edu.agh.iosr.lambda.dropwizard.kafka.TridentPartitioner");
        props.put("value.serializer","org.apache.kafka.common.serialization.ByteArraySerializer");

        ProducerConfig config = new ProducerConfig(props);

        producer = new Producer<>(config);

    }

    public boolean sendTuple(Values values){
        try{
            producer.send(new KeyedMessage<String, Values>(topicName,values));
        }catch (Exception e){
            logger.warning("Message failed: "+ e.getMessage());
            return false;
        }
        return true;
    }
}
