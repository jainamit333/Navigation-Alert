package com.swiggy.execution;

import com.swiggy.exception.FileReadException;
import com.swiggy.exception.LocationEvaluationException;
import com.swiggy.impl.CsvReader;
import com.swiggy.pojo.LatLong;
import com.swiggy.pojo.Location;
import com.swiggy.service.FileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by amit on 16/1/17.
 */
@Service
public class LatLongCsvReader {

    private final int NUMBER_OF_COLUMNS = 2;
    private static final String[] COLUMN_NAMES = {LatLong.LATITUDE.name(),LatLong.LONGITUDE.name()};

    @Autowired
    @Qualifier("csvReader")
    private FileReaderService csvReader;

    public List<Location> getData(String filePath) throws LocationEvaluationException {

        try{
            Map<String,ArrayList<String>> data = csvReader.getData(filePath,NUMBER_OF_COLUMNS,COLUMN_NAMES);
            List<Location> locations = new ArrayList<>();
            int[] counter = {0};
            data.get(LatLong.LATITUDE.name()).forEach(latitude -> {
                String longitude = data.get(LatLong.LONGITUDE.name()).get(counter[0]++);
                locations.add(Location.builder().latitude(latitude).longitude(longitude).build());
            });
            return locations;
        }catch (FileReadException e){
            throw new LocationEvaluationException(e);
        }

    }
}
