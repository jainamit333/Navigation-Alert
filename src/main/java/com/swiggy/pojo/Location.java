package com.swiggy.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * Created by amit on 16/1/17.
 */
@Data
@Builder
public class Location {

    private String latitude;
    private String longitude;

}
