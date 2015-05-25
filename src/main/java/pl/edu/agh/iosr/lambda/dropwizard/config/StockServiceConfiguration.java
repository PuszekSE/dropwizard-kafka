package pl.edu.agh.iosr.lambda.dropwizard.config;

import com.yammer.dropwizard.config.Configuration;

import javax.validation.Valid;

public class StockServiceConfiguration extends Configuration {

    @Valid
    private StockConfiguration stock;

    public StockConfiguration getStock() {
        return stock;
    }

    public void setStock(StockConfiguration stock) {
        this.stock = stock;
    }

}