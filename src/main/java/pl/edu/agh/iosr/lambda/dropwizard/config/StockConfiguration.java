package pl.edu.agh.iosr.lambda.dropwizard.config;

import javax.validation.constraints.NotNull;

/**
 * Created by Puszek_SE on 2015-05-25.
 */
public class StockConfiguration {

    @NotNull
    private String stock;

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}
