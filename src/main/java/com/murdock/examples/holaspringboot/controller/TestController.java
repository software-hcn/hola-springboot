package com.murdock.examples.holaspringboot.controller;

import com.murdock.examples.holaspringboot.service.PrometheusCustomMonitor;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @project hola-springboot
 * @description 
 * @author Asher.H
 * @date 2024/4/11 15:38:47
 * @version 1.0
 */

@RestController
public class TestController {

    @Resource
    private PrometheusCustomMonitor monitor;

    //....

    @RequestMapping("/order")
    public String order(@RequestParam(defaultValue = "0") String flag) throws Exception {
        // 统计下单次数
        monitor.getOrderCount().increment();
        if ("1".equals(flag)) {
            throw new Exception("出错啦");
        }
        Random random = new Random();
        int amount = random.nextInt(100);
        // 统计金额
        monitor.getAmountSum().record(amount);
        return "下单成功, 金额: " + amount;
    }
}

