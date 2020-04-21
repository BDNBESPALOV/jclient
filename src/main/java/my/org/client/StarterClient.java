package my.org.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StarterClient {
    public static void main(String ... args){
        ConfigurableApplicationContext s=  SpringApplication.run(StarterClient.class,args);
        s.getBean("clientController",ClientController.class).ClientController1();
    }
}
