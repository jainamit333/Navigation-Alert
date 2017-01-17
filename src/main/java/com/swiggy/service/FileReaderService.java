package com.swiggy.service;

import com.swiggy.exception.FileReadException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by amit on 16/1/17.
 */
public interface FileReaderService {

    Map<String,ArrayList<String>> getData(String filePath,int columnCount,String ... keys) throws FileReadException;

    String getData(String filePath);
}
