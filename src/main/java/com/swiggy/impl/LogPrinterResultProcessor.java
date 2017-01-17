package com.swiggy.impl;

import com.swiggy.pojo.Direction;
import com.swiggy.service.ResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by amit on 16/1/17.
 */
@Service("logPrinterResultProcessor")
public class LogPrinterResultProcessor implements ResultProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogPrinterResultProcessor.class);

    @Override
    public void process(Direction direction) {

        LOGGER.info(direction.toString());
    }
}
