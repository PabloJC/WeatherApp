package com.pabji.wheatherapp.data.bbdd.History;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pabji.wheatherapp.data.bbdd.DBHelper;
import com.pabji.wheatherapp.data.constants.LogConstants;
import com.pabji.wheatherapp.data.constants.ResponseCodes;
import com.pabji.wheatherapp.data.parsers.DBParsers;
import com.pabji.wheatherapp.data.callbacks.ResponseCallback;
import com.pabji.wheatherapp.data.models.CityModel;

import java.util.List;


public class HistoryDBManager implements HistoryDB {

    private static final String TAG = HistoryDBManager.class.getName();

    private static HistoryDBManager instance;

    private DBHelper dbHelper;

    public HistoryDBManager(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    @Override
    public void getHistory(ResponseCallback<List<CityModel>> callback) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + HistoryCityTable.TABLE_NAME, null);


        if (cursor.getCount() == 0) {
            callback.error(ResponseCodes.CODE_EMPTY);
            Log.d(TAG, LogConstants.ERROR_EMPTY_RESPONSE);
        } else {
            List<CityModel> list = DBParsers.toListCity(cursor);
            callback.success(list);
            Log.d(TAG, list.toString());
        }

        cursor.close();
        db.close();
    }

    @Override
    public void saveSearch(CityModel cityModel, ResponseCallback<Boolean> callback) {
        // Gets the data repository in write mode

        if(!existCityModel(cityModel)){
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            long newRowId = db.insert(HistoryCityTable.TABLE_NAME, null, DBParsers.toContentValues(cityModel));

            Boolean saved = newRowId == -1;
            callback.success(saved);
            Log.d(TAG, String.format(LogConstants.SUCCESS_SAVED_BD, cityModel.getName(), saved));

            db.close();
        }else{
            callback.success(false);
            Log.d(TAG, String.format(LogConstants.SUCCESS_EXIST_IN_BD, cityModel.getName()));
        }

    }

    private Boolean existCityModel(CityModel cityModel){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + HistoryCityTable.TABLE_NAME + " where "+ HistoryCityTable.COLUMN_NAME_NAME + " = '" + cityModel.getName() + "'", null);

        Boolean exist = cursor.getCount() > 0;

        cursor.close();
        db.close();
        return  exist;
    }

    public static synchronized HistoryDBManager getInstance(Context context) {
        if (instance == null) {
            instance = new HistoryDBManager(context);
        }
        return instance;
    }

}
