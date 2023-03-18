package com.penfriendhub.recorderlark;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author unickcheng
 * @since 2023-03-18
 */

@Slf4j
@SpringBootApplication
public class RecorderLarkApplication {
    public static void main (String[] args) {
        SpringApplication.run(RecorderLarkApplication.class, args);
        log.info(">>> {}", RecorderLarkApplication.class.getSimpleName().toUpperCase() +
                " STARTING RecorderLarkApplication SUCCESS. See https://github.com/penfriendhub/recorder-lark for more information. ");
    }
}

