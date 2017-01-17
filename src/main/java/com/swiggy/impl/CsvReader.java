package com.swiggy.impl;

import com.google.common.base.Verify;
import com.swiggy.exception.FileReadException;
import com.swiggy.exception.MethodNotImplementedException;
import com.swiggy.service.FileReaderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Created by amit on 16/1/17.
 */
@Service("csvReader")
public class CsvReader implements FileReaderService{

    private final String DEFAULT_SEPARATOR = ",";


    @Override
    public Map<String, ArrayList<String>> getData(String filePath, int columnCount, String... keys)
            throws FileReadException {

        Verify.verify(!StringUtils.isEmpty(filePath));
        if(columnCount<1){
            return Collections.emptyMap();
        }
        Verify.verify(columnCount==keys.length);

        String line = "";
        Map<String,ArrayList<String>> result = new HashMap<>();
        initialiseMap(result,keys);
        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while (( line = br.readLine())!=null){
                String[] data = line.split(DEFAULT_SEPARATOR);
                if(data.length!=columnCount){
                    continue;
                }
                putValueInMap(result,data,keys);

            }

            return result;
        }catch (Exception e){
            throw new FileReadException(e);
        }
    }

    @Override
    public String getData(String filePath) {
        throw new MethodNotImplementedException();
    }

    private void putValueInMap(Map<String, ArrayList<String>> result, String[] data, String[] keys) {

        for(int i = 0;i<data.length;i++){
            result.get(keys[i]).add(data[i]);
        }
    }

    private void initialiseMap(Map<String, ArrayList<String>> result, String[] keys) {

        Arrays.stream(keys).forEach(key -> result.put(key,new ArrayList<>()));

    }

}
