package pl.edu.agh.iosr.lambda.dropwizard.config;

import backtype.storm.tuple.Fields;

/**
 * Created by Puszek_SE on 2015-05-25.
 */
public class StockFieldsContainer {

    public Fields stockFields;

    public static String TIMESTAMP = "Timestamp";
    public static String SYMBOL = "Symbol";
    public static String NAME = "Name";
    public static String PRICE = "LastPrice";

    public StockFieldsContainer(){
        stockFields = new Fields(TIMESTAMP,SYMBOL,NAME,PRICE);
    }

}
