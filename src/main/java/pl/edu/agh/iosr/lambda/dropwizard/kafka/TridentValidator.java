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
