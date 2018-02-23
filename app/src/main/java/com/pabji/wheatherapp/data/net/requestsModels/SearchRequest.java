package com.pabji.wheatherapp.data.net.requestsModels;

import java.util.HashMap;
import java.util.Map;


public class SearchRequest extends BaseRequest{

    public static final String LANGUAGE = "en";
    public static final Boolean IS_NAME_REQUIRED = true;
    public static final String STYLE = "FULL";

    private String cityName;
    private Integer maxRows = 20;
    private Integer startRow = 0;

    public SearchRequest(String cityName) {
        this.cityName = cityName;
    }

    public SearchRequest(String cityName, Integer maxRows, Integer startRow) {
        this.cityName = cityName;
        this.maxRows = maxRows;
        this.startRow = startRow;
    }

    @Override
    public Map<String, String> toQueryMap() {
        Map<String, String> data = new HashMap<>();
        data.put("q", cityName);
        data.put("maxRows", String.valueOf(maxRows));
        data.put("startRow", String.valueOf(startRow));
        data.put("lang", LANGUAGE);
        data.put("isNameRequired:", String.valueOf(IS_NAME_REQUIRED));
        data.put("style", STYLE);
        data.put("username", getUsername());

        return data;
    }
}
