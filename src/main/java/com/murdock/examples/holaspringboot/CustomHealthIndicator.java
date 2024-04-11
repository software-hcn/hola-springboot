package com.murdock.examples.holaspringboot;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @project hola-springboot
 * @description 自定义健康监测器
 * @author Asher.H
 * @date 2024/4/9 18:00:57
 * @version 1.0
 */

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // 使用 builder 来创建健康状态信息
        // 如果你throw 了一个 exception，那么status 就会被置为DOWN，异常信息会被记录下来
        builder.up()
                .withDetail("app", "CustomHealthIndicator 向你报告：项目很健康哦！")
                .withDetail("error", "CustomHealthIndicator 向你报告：项目有点问题哦！");
    }
}
