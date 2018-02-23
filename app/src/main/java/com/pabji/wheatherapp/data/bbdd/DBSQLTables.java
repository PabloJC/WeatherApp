package com.pabji.wheatherapp.data.bbdd;

import com.pabji.wheatherapp.data.bbdd.History.HistoryCityTable;


public class DBSQLTables {

    private static final String SQL_CREATE_HISTORY_TABLE =
            "CREATE TABLE " + HistoryCityTable.TABLE_NAME + " (" +
                    HistoryCityTable._ID + " INTEGER PRIMARY KEY," +
                    HistoryCityTable.COLUMN_NAME_EAST + " REAL," +
                    HistoryCityTable.COLUMN_NAME_NORTH + " REAL," +
                    HistoryCityTable.COLUMN_NAME_SOUTH + " REAL," +
                    HistoryCityTable.COLUMN_NAME_WEST + " REAL," +
                    HistoryCityTable.COLUMN_NAME_LATITUDE + " REAL," +
                    HistoryCityTable.COLUMN_NAME_LONGITUDE + " REAL," +
                    HistoryCityTable.COLUMN_NAME_NAME + " TEXT)";

    private static final String SQL_DELETE_HISTORY_TABLE =
            "DROP TABLE IF EXISTS " + HistoryCityTable.TABLE_NAME;

    public static final String[] SQL_CREATE_TABLES = {SQL_CREATE_HISTORY_TABLE};
    public static final String[] SQL_DELETE_TABLES = {SQL_DELETE_HISTORY_TABLE};

}
