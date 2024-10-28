package com.cofjus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cofjus
 * @date 10/28/24
 */
@SpringBootApplication(scanBasePackages = "com.cofjus")
@Slf4j
public class Boot {

    public static void main(String... args) {
        try {
            SpringApplication.run(Boot.class, args);
        } catch (Exception e) {
            log.error("Failed to start app", e);
        }
    }
}
