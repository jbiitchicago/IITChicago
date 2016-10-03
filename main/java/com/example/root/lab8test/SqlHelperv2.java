package com.example.root.lab8test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by root on 5/6/16.
 */
public class SqlHelperv2 extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 12;
    // Database Name
    private static final String DATABASE_NAME = "Stockdb";
    private static final String TABLE_ALERTS = "alerts";
    private static final String KEY_NAME = "name";
    private static final String KEY_ALERT = "alertPrice";


    public SqlHelperv2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addAlert(String name, double alertPrice){

        Log.d("addAlert",name+String.valueOf(alertPrice));
        if(name!=null && alertPrice!=0) {
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. create ContentValues to add key "column"/value
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name); // get NAME
            values.put(KEY_ALERT, alertPrice); // get author
            db.insert(TABLE_ALERTS, // table
                    null, //nullColumnHack
                    values); // key/value -> keys = column names/values
            // 4. Close dbase
            db.close();
        }

    }
}
