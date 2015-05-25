package pl.edu.agh.iosr.lambda.dropwizard.demo;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import pl.edu.agh.iosr.lambda.dropwizard.demo.config.ExampleServiceConfiguration;
import pl.edu.agh.iosr.lambda.dropwizard.demo.hello.HelloResource;
import pl.edu.agh.iosr.lambda.dropwizard.demo.money.MoneyResource;

public class ExampleService extends Service<ExampleServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ExampleService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ExampleServiceConfiguration> bootstrap) {
        bootstrap.setName("dropwizard-example");
    }

    @Override
    public void run(ExampleServiceConfiguration conf, Environment env) throws Exception {
        env.addResource(new HelloResource(conf.getMessages()));
        env.addResource(new MoneyResource(conf.getMoney()));
    }

}