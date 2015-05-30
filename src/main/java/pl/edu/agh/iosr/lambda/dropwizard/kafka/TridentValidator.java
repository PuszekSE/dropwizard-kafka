package pl.edu.agh.iosr.lambda.dropwizard.kafka;

import backtype.storm.tuple.Fields;
import org.json.JSONObject;
import pl.edu.agh.iosr.lambda.dropwizard.config.iface.FieldsDescriptor;
import pl.edu.agh.iosr.lambda.dropwizard.stock.StockResource;
import storm.trident.tuple.TridentTuple;
import storm.trident.tuple.TridentTupleView;

import java.util.LinkedList;
import java.util.List;
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

    public boolean validateJson(JSONObject body){

        if(null == body){
            return false;
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
