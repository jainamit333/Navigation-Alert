package com.swiggy.navapi.api;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by amit on 16/1/17.
 */
@Service
public class GeoContextBuilder {


    @Value("${google.api.key}")
    private String apiKey;

    public GeoApiContext createInstance(){

        return new GeoApiContext().setApiKey(apiKey);
        //return new GeoApiContext(new GaeRequestHandler()).setApiKey(apiKey);

    }


}
