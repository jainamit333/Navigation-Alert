package com.swiggy.execution;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.swiggy.constants.StringContants;
import com.swiggy.pojo.Direction;
import com.swiggy.service.ResultProcessor;
import com.swiggy.util.CalculatorUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amit on 16/1/17.
 */

public class Navigator implements Runnable {



    private static final Logger LOGGER = LoggerFactory.getLogger(Navigator.class);
    //speed should be in meter/second
    private double speed;
    private List<DirectionsResult> directionsResults;
    private ResultProcessor resultProcessor;

    public Navigator(double speed, List<DirectionsResult> directionsResultList,
                     ResultProcessor resultProcessor) {

        this.speed = speed;
        this.directionsResults = directionsResultList;
        this.resultProcessor = resultProcessor;
    }


    @Override
    public void run() {

        List<DirectionsStep> directionsSteps = directionsResults.stream()
                .flatMap(directionsResult -> Arrays.stream(directionsResult.routes))
                .flatMap(directionsRoute -> Arrays.stream(directionsRoute.legs))
                .flatMap(directionsLeg -> Arrays.stream(directionsLeg.steps))
                .collect(Collectors.toList());
        int currentStep = 0;

        while (currentStep < directionsSteps.size()){

            DirectionsStep step = directionsSteps.get(currentStep);
            String message = step.htmlInstructions;
            long distance = step.distance.inMeters;
            long timeInMilliSeconds = CalculatorUtil.timeInMilliSeconds(distance,speed);
            resultProcessor.process(Direction.builder()
                                             .message(message).timeStamp(DateTime.now()).build());
            try {
                Thread.sleep(timeInMilliSeconds);
            } catch (InterruptedException e) {
                LOGGER.error("Error while ",e);
            }
            currentStep++;
        }
        resultProcessor.process(Direction.builder().timeStamp(DateTime.now())
        .message(StringContants.FINAL_MESSAGE).build());
    }
}
