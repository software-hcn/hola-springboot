package com.murdock.examples.holaspringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @project hola-springboot
 * @description 
 * @author Asher.H
 * @date 2024/4/8 15:26:30
 * @version 1.0
 */

@RestController
@RequestMapping("/api")
public class HolaRestControllerV1 {

    @RequestMapping(method = RequestMethod.GET, value = "/holaV1", produces = "text/plain")
    public String hola() throws UnknownHostException {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost()
                    .getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return "Hola Spring Boot @ " + hostname;
    }
}

