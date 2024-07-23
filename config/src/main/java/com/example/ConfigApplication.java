package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */

@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConfigApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        int i=0;
        while (i<100){
            i++;
            System.out.println("host="+environment.getProperty("spring.redis.host"));
            System.out.println("timeout="+environment.getProperty("spring.redis.timeout"));
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
