package com.swiggy.navapi.api;

import com.google.common.base.Verify;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

/**
 * Created by amit on 16/1/17.
 */

public class DirectionApiReq {


    private final TravelMode DEFAULT_TRAVEL_MODE = TravelMode.DRIVING;
    private final Unit DEFAULT_UNIT = Unit.IMPERIAL;
    private final int WAYPOINTS_LIMIT = 23 - 2;
    private final String WAY_POINT_SEPERATOR = ",";



    private DirectionsApiRequest directionsApiRequest;

    public  DirectionApiReq createInstance(GeoContextBuilder geoContext){

        directionsApiRequest = DirectionsApi.newRequest(geoContext.createInstance());
        directionsApiRequest.mode(DEFAULT_TRAVEL_MODE)
        .units(DEFAULT_UNIT);
        return this;
    }

    public DirectionApiReq origin(String latitude, String longitude){

        this.directionsApiRequest.origin(getLatLng(latitude,longitude));
        return this;

    }

    public DirectionApiReq destination(String latitude, String longitude){

        this.directionsApiRequest.destination(getLatLng(latitude,longitude));
        return this;
    }

    public DirectionApiReq wayPoints(String latitudes[],String[] longitudes){

        Verify.verifyNotNull(latitudes);
        Verify.verifyNotNull(longitudes);
        Verify.verify(latitudes.length==longitudes.length);
        Verify.verify(latitudes.length<=WAYPOINTS_LIMIT);

        String[] wayPoints = new String[latitudes.length];
        for (int i = 0;i<latitudes.length;i++){
            wayPoints[i] = latitudes[i] + WAY_POINT_SEPERATOR + longitudes[i];
        }

        this.directionsApiRequest.waypoints(wayPoints);
        return this;

    }

    public DirectionsApiRequest build(){
        return directionsApiRequest;
    }


    private LatLng getLatLng(String latitude,String longitude){

        return new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
    }

}
