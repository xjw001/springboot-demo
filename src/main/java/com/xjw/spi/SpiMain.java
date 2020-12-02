package com.xjw.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiMain {

    public static void main(String[] args) {
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iterator = load.iterator();
        while (iterator.hasNext()){
            SpiService spiService = iterator.next();
            spiService.saveHello();
        }
    }
}
