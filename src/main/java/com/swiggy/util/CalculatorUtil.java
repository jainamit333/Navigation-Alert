package com.swiggy.util;

/**
 * Created by amit on 16/1/17.
 */
public class CalculatorUtil {



    public static long timeInMilliSeconds(long distanceInMeter,double speedInMeterPerSecond){

        return (long)(distanceInMeter/speedInMeterPerSecond)*1000;
    }





}
