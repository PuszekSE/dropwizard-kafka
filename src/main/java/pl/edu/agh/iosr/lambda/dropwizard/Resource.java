package pl.edu.agh.iosr.lambda.dropwizard;

import pl.edu.agh.iosr.lambda.dropwizard.config.iface.FieldsDescriptor;
import pl.edu.agh.iosr.lambda.dropwizard.kafka.KafkaApplication;
import pl.edu.agh.iosr.lambda.dropwizard.kafka.TridentValidator;

/**
 * Created by Puszek_SE on 2015-06-09.
 */
public abstract class Resource<T extends FieldsDescriptor>{

    protected TridentValidator tridentValidator;
    protected KafkaApplication kafkaApplication;
    protected T fieldsDescriptor;
    
    public abstract String inputMethod(String body);
}
