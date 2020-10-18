package com.temp.server;

import com.temp.server.base.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class App extends AppConfig {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String... args) {
        SpringApplication.run(App.class, args);
    }
}
