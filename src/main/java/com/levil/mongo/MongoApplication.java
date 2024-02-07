package com.levil.mongo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(scanBasePackages = "com.levil",exclude={DataSourceAutoConfiguration.class})
public class MongoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MongoApplication.class, args);


    }

}
