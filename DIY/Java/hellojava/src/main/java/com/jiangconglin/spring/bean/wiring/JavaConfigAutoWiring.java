package com.jiangconglin.spring.bean.wiring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.jiangconglin.spring.bean.wiring.bean"})
public class JavaConfigAutoWiring {
}
