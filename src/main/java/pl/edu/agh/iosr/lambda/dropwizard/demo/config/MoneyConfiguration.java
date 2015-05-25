package pl.edu.agh.iosr.lambda.dropwizard.demo.config;

import javax.validation.constraints.NotNull;

public class MoneyConfiguration {

    @NotNull
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}