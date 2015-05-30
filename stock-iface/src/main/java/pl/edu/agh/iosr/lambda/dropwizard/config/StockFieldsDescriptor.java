package pl.edu.agh.iosr.lambda.dropwizard.config;

import org.json.JSONObject;
import pl.edu.agh.iosr.lambda.dropwizard.config.iface.FieldsDescriptor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Puszek_SE on 2015-05-25.
 */
public class StockFieldsDescriptor implements FieldsDescriptor {

    public static String TIMESTAMP = "Timestamp";
    public static String SYMBOL = "Symbol";
    public static String NAME = "Name";
    public static String PRICE = "LastPrice";

    public static String[] stockFields = {SYMBOL,NAME,PRICE,TIMESTAMP};

    @Override
    public String getIdColumnName(){
        return SYMBOL;
    }

    @Override
    public String getTimestamp(){
        return TIMESTAMP;
    }

    @Override
    public String[] getAllFields() {
        return stockFields;
    }

    @Override
    public Map<String,String> extractMapFromJson(String body){
        JSONObject jsonObject = new JSONObject(body);

        Map<String,String> map = new HashMap<String, String>();
        for(String field : stockFields){
            map.put(field,String.valueOf(jsonObject.get(field)));
        }
        return map;
    }

}
