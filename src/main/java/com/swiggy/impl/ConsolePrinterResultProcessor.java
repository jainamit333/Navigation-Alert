package com.swiggy.impl;

import com.swiggy.pojo.Direction;
import com.swiggy.service.ResultProcessor;
import org.springframework.stereotype.Service;

/**
 * Created by amit on 16/1/17.
 */
@Service("consolePrinterResultProcessor")
public class ConsolePrinterResultProcessor implements ResultProcessor {

    @Override
    public void process(Direction direction) {
        System.out.println(direction.toString());
    }
}
