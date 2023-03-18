package com.penfriendhub.recorderlark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author unickcheng
 * @since 2023-03-19
 */

@RestController
public class HelloWorldController {

    @GetMapping("ping")
    public String ping () {
        return "pong";
    }
}
