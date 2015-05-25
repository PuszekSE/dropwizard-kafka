package pl.edu.agh.iosr.lambda.dropwizard;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import pl.edu.agh.iosr.lambda.dropwizard.config.StockServiceConfiguration;
import pl.edu.agh.iosr.lambda.dropwizard.stock.StockResource;

/**
 * Created by Puszek_SE on 2015-05-25.
 */
public class StockService extends Service<StockServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new StockService().run(args);
    }

    @Override
    public void initialize(Bootstrap<StockServiceConfiguration> bootstrap) {
        bootstrap.setName("stock-service");
    }

    @Override
    public void run(StockServiceConfiguration stockConfig, Environment environment) throws Exception {
        environment.addResource(new StockResource(stockConfig.getStock()));
    }
}
