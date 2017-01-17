package com.swiggy.execution;

import com.google.maps.model.DirectionsResult;
import com.swiggy.exception.LocationEvaluationException;
import com.swiggy.navapi.api.DirectionApi;
import com.swiggy.pojo.Location;
import com.swiggy.pojo.NavigationRequest;
import com.swiggy.service.NavigatorService;
import com.swiggy.service.ResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amit on 16/1/17.
 */
@Service("navigatorServiceImpl")
public class NavigatorServiceImpl implements NavigatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigatorServiceImpl.class);

    @Autowired
    private LatLongCsvReader latLongReader;
    @Autowired
    private DirectionApi directionApi;
    @Autowired
    @Qualifier("consolePrinterResultProcessor")
    private ResultProcessor resultProcessor;

    @Override
    public void computeNavigation(NavigationRequest navigationRequest) {

        try{
            List<Location> locationData = latLongReader.getData(navigationRequest.getFilePath());
            List<DirectionsResult> directionsResultList = directionApi.getDirectionData(locationData);
            Thread navigation = new Thread(new Navigator(navigationRequest.getSpeed(),directionsResultList,
                    resultProcessor));
            navigation.start();

        }catch (LocationEvaluationException e){
            LOGGER.error("Error while getting Latitude/Longitude From CSV File",e);
        }catch (Exception e){
            LOGGER.error("Error while getting getting navigation",e);
        }

    }
}
