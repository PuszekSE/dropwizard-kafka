package pl.edu.agh.iosr.lambda.dropwizard.demo.config;

import com.yammer.dropwizard.config.Configuration;

import javax.validation.Valid;

public class ExampleServiceConfiguration extends Configuration {

    @Valid
    private MessagesConfiguration messages;

    public MessagesConfiguration getMessages() {
        return messages;
    }

    public void setMessages(MessagesConfiguration messages) {
        this.messages = messages;
    }

    @Valid
    private MoneyConfiguration money;

    public MoneyConfiguration getMoney(){
        return money;
    }

    public void  setMoney(MoneyConfiguration money){
        this.money = money;
    }
}