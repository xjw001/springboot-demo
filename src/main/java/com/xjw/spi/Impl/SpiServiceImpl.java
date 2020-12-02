package com.xjw.spi.Impl;

import com.xjw.spi.SpiService;

public class SpiServiceImpl implements SpiService {
    @Override
    public void saveHello() {
        System.out.println("实现类saveHello方法");
    }
}
