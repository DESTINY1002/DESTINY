package com.jiangconglin.spring.bean.wiring.bean;

import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc {
    private String test;

    public SgtPeppers(String t)
    {
        test = t;
    }
    @Override
    public void play() {
        System.out.println("playing..." + test);
    }
}
