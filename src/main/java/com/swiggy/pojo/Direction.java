package com.swiggy.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

/**
 * Created by amit on 16/1/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Direction {

    private DateTime timeStamp;
    private String message;

    @Override
    public String toString(){

        return this.getTimeStamp().toString()+" :- " + message;
    }

}
