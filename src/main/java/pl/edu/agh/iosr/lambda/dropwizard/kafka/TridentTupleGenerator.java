package pl.edu.agh.iosr.lambda.dropwizard.kafka;

import backtype.storm.tuple.Fields;
import org.json.JSONObject;
import pl.edu.agh.iosr.lambda.dropwizard.stock.StockResource;
import storm.trident.tuple.TridentTuple;
import storm.trident.tuple.TridentTupleView;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Puszek_SE on 2015-05-25.
 */
public class TridentTupleGenerator {

    Logger logger = Logger.getLogger(this.getClass().getName());


    Fields fields;

    public TridentTupleGenerator(Fields fields){
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

    public TridentTuple tupleInstance(JSONObject body){

        logger.info("Creating tuple");
        if(incorrectJson(body)){
            return null;
        }

        List<Object> values = new LinkedList<>();
        for(String field : fields){
            values.add(body.get(field));
        }

        TridentTuple tridentTuple = TridentTupleView.createFreshTuple(fields,values);
        if(tridentTuple == null){
            logger.warning("CREATED EMPTY TUPLE!");
        }

        return  tridentTuple;
    }
}
