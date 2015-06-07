package pl.edu.agh.iosr.lambda.dropwizard.config.iface;

/**
 * Created by Puszek_SE on 2015-06-07.
 */
public interface Resource<T extends FieldsDescriptor> {
    public String inputMethod(String body);
}