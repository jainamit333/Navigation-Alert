package com.swiggy.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by amit on 16/1/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NavigationResponse {

    private ArrayList<Direction> direction;


}
