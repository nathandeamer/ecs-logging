package com.nathandeamer.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    private static Logger log = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/logging")
    public ResponseEntity<String> logging() {
        log.info("Hello world");
        log.warn("This is a warning");
        log.error("This is an error");

        try {
            throw new Exception("This is an exception");
        } catch (Exception e) {
            log.error("This is an error with an exception", e);
        }
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
