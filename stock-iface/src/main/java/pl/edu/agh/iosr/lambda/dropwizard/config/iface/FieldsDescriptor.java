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
