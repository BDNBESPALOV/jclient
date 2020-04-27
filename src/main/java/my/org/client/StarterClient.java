package my.org.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class StarterClient {
    public static void main(String ... args){
        ConfigurableApplicationContext configurableApplicationContext =  SpringApplication.run(StarterClient.class,args);
        // s.getBean("clientController",ClientController.class).ClientController1();
    }
}
