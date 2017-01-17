package com.swiggy.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by amit on 16/1/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NavigationRequest {


    private double speed;
    private String filePath;

}
