package com.swiggy.navapi.api;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.DirectionsResult;
import com.swiggy.pojo.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amit on 16/1/17.
 */
@Service
public class DirectionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectionApi.class);

    public static final int BATCH_SIZE = 23;

    @Autowired
    private GeoContextBuilder geoContext;

    public List<DirectionsResult> getDirectionData(List<Location> locations){

        List<DirectionsResult> results = new ArrayList<>();

        int low = 0;
        int dataSize = locations.size();
        int high = dataSize > BATCH_SIZE ? BATCH_SIZE-1 : dataSize-1;

        while (high < dataSize){

            String[] wayPointLatitude = new String[high-low-2];
            String[] wayPointLongitude = new String[high-low-2];
            wayPointLatitude = locations.subList(low+1,high).stream()
                                        .map(Location::getLatitude)
                                        .collect(Collectors.toList())
                                        .toArray(wayPointLatitude);
            wayPointLongitude = locations.subList(low+1,high).stream()
                                         .map(Location::getLongitude)
                                         .collect(Collectors.toList())
                                         .toArray(wayPointLongitude);

            DirectionsApiRequest request = new DirectionApiReq()
                    .createInstance(geoContext)
                    .origin(locations.get(low).getLatitude(),locations.get(low).getLongitude())
                    .destination(locations.get(high).getLatitude(),locations.get(high).getLongitude())
                    .wayPoints(wayPointLatitude, wayPointLongitude)
                    .build();
            try {
                DirectionsResult directionsResult = request.await();
                results.add(directionsResult);

            } catch (Exception e) {
                LOGGER.error("Error while getting data from google API",e);
            }finally {
                low = low+ BATCH_SIZE-1;
                high = high+BATCH_SIZE-1;
            }
        }
        return results;
    }
}
