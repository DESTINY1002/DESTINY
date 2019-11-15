package com.jiangconglin.spring.bean.wiring;

import com.jiangconglin.spring.bean.wiring.bean.CompactDisc;
import com.jiangconglin.spring.bean.wiring.bean.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public CompactDisc getCompactDisc()
    {
        return new SgtPeppers("123");
    }
}
