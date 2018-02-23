package com.pabji.wheatherapp.data.parsers;

import android.content.ContentValues;
import android.database.Cursor;

import com.pabji.wheatherapp.data.bbdd.History.HistoryCityTable;
import com.pabji.wheatherapp.data.models.CityModel;

import java.util.ArrayList;
import java.util.List;


public class DBParsers {

    public static ContentValues toContentValues(CityModel model){
        ContentValues values = new ContentValues();

        values.put(HistoryCityTable.COLUMN_NAME_NAME, model.getName());
        values.put(HistoryCityTable.COLUMN_NAME_LATITUDE, model.getLatitude());
        values.put(HistoryCityTable.COLUMN_NAME_LONGITUDE, model.getLongitude());
        values.put(HistoryCityTable.COLUMN_NAME_EAST, model.getEast());
        values.put(HistoryCityTable.COLUMN_NAME_WEST, model.getWest());
        values.put(HistoryCityTable.COLUMN_NAME_NORTH, model.getNorth());
        values.put(HistoryCityTable.COLUMN_NAME_SOUTH, model.getSouth());

        return values;
    }

    public static List<CityModel> toListCity(Cursor cursor){
        List<CityModel> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                CityModel model = new CityModel();

                model.setName(cursor.getString(cursor.getColumnIndex(HistoryCityTable.COLUMN_NAME_NAME)));
                model.setLatitude(cursor.getDouble(cursor.getColumnIndex(HistoryCityTable.COLUMN_NAME_LATITUDE)));
                model.setLongitude(cursor.getDouble(cursor.getColumnIndex(HistoryCityTable.COLUMN_NAME_LONGITUDE)));
                model.setEast(cursor.getDouble(cursor.getColumnIndex(HistoryCityTable.COLUMN_NAME_EAST)));
                model.setWest(cursor.getDouble(cursor.getColumnIndex(HistoryCityTable.COLUMN_NAME_WEST)));
                model.setNorth(cursor.getDouble(cursor.getColumnIndex(HistoryCityTable.COLUMN_NAME_NORTH)));
                model.setSouth(cursor.getDouble(cursor.getColumnIndex(HistoryCityTable.COLUMN_NAME_SOUTH)));

                list.add(model);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
