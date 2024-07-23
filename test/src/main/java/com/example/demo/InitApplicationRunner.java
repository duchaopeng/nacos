package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class InitApplicationRunner implements ApplicationRunner {

    @Value("${server.port}")
    private String port;

    @Override
    public void run(ApplicationArguments var1) throws Exception {
        System.out.println("*********InitApplicationRunner!");
        System.out.println("*********InitApplicationRunner" + port);

    }


}
