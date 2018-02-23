package com.pabji.wheatherapp.data.net.requestsModels;

import java.util.Map;


public abstract class BaseRequest {
    public static final String[] DEFAULT_USERNAMES = {"ilgeonamessample","demo"};

    private String username = DEFAULT_USERNAMES[0];

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    protected abstract Map<String,String> toQueryMap();
}
