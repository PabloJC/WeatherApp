package com.pabji.wheatherapp.data.bbdd.History;

import android.provider.BaseColumns;

public class HistoryCityTable implements BaseColumns {
    public static final String TABLE_NAME = "historyCity";
    public static final String COLUMN_NAME_EAST = "east";
    public static final String COLUMN_NAME_SOUTH = "south";
    public static final String COLUMN_NAME_NORTH = "north";
    public static final String COLUMN_NAME_WEST = "west";

    public static final String COLUMN_NAME_LATITUDE = "latitude";
    public static final String COLUMN_NAME_LONGITUDE = "longitude";

    public static final String COLUMN_NAME_NAME = "name";
}
