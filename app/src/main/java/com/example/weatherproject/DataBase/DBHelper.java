package com.example.weatherproject.DataBase;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.weatherproject.models.weatherPOJO.WeatherRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper singleton;

    private static final String DB_NAME = "Weather.db";
    private static final String TABLE = "weather";
    private static final int SCHEMA = 1;

    private static final String ID_COLUMN = "id";
    private static final String CITY_COLUMN = "city";
    private static final String LAT_COLUMN = "lat";
    private static final String LON_COLUMN = "lon";

    private Gson objectConverter = new Gson();

    private DBHelper(Context context){
        super(context,DB_NAME,null,SCHEMA);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String requestDB = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL, %s TEXT, %s TEXT);",
                TABLE,ID_COLUMN,CITY_COLUMN,LAT_COLUMN,LON_COLUMN);
        db.execSQL(requestDB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);

        onCreate(db);
    }

    public void add(WeatherRequest weatherRequest){

        Log.d("Internet", "In add method ");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(CITY_COLUMN, weatherRequest.getName());
        content.put(LAT_COLUMN, String.valueOf(weatherRequest.getCoord().getLat()));
        content.put(LON_COLUMN, String.valueOf(weatherRequest.getCoord().getLon()));
        Log.d("Internet", "Operation complied ");
        db.insert(TABLE,null,content);
        db.close();
        Log.d("Internet", "db is close ");
    }

    public DBItem get(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE,
                new String[]{ID_COLUMN,CITY_COLUMN,LAT_COLUMN,LON_COLUMN},
                ID_COLUMN + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        DBItem item = new DBItem(cursor.getString(1),cursor.getString(2),cursor.getString(3));
        cursor.close();
        return item;
    }

    public List<DBItem> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<DBItem> foreignList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE,null);

        if(cursor != null){
            cursor.moveToFirst();
            do {
                foreignList.add(new DBItem(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return foreignList;
    }

    public void update(WeatherRequest weatherRequest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CITY_COLUMN,weatherRequest.getName());
        contentValues.put(LAT_COLUMN, String.valueOf(weatherRequest.getCoord().getLat()));
        contentValues.put(LON_COLUMN, String.valueOf(weatherRequest.getCoord().getLon()));

        db.update(TABLE,contentValues,CITY_COLUMN + " = ?",new String[]{weatherRequest.getName()});
        db.close();
    }

    public void delete(WeatherRequest weatherRequest){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE,CITY_COLUMN + " = ?",new String[]{weatherRequest.getName()} );
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE,null,null);
        db.close();
    }
    public int getItemCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public boolean isContains(String cityName){
        Log.d("Internet", "In 'isContains' ");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE,
                new String[]{ID_COLUMN,CITY_COLUMN,LAT_COLUMN,LON_COLUMN},
                CITY_COLUMN + "=?",
                new String[]{cityName},
                null,null,null,null);
        Log.d("Internet", "cursor " + cursor);
        boolean isExist = cursor.getCount() > 0;
        cursor.close();
        return isExist;
    }
    public static void initInstance(Context context){
        if(singleton == null){
            singleton = new DBHelper(context);
        }
    }
    public static DBHelper getInstance(){
        return singleton;
    }
    public class DBItem{
        private String cityName;
        private String lat;
        private String lon;

        public DBItem(String cityName, String lat, String lon) {
            this.cityName = cityName;
            this.lat = lat;
            this.lon = lon;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        @NonNull
        @Override
        public String toString() {
            return "DBItem{" +
                    "cityName='" + cityName + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    '}';
        }
    }
}
