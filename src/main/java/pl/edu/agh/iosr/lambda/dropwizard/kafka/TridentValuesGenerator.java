package pl.edu.agh.iosr.lambda.dropwizard.kafka;

import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Puszek_SE on 2015-05-25.
 */
public class TridentValuesGenerator {

    Logger logger = Logger.getLogger(this.getClass().getName());


    Fields fields;

    public TridentValuesGenerator(Fields fields){
        this.fields = fields;
    }

    private boolean incorrectJson(JSONObject body){

        if(null == body){
            return false;
        }

        for(String field : fields){
            if(null == body.get(field)){
                logger.warning("Missing attribute: "+field);
                return true;
            }
        }

        return false;
    }

    public Values valuesInstance(JSONObject body){

        logger.info("Creating tuple");
        if(incorrectJson(body)){
            return null;
        }

        Values values = new Values();
        for(String field : fields){
            values.add(String.valueOf(body.get(field)));
        }

        return  values;
    }
}
