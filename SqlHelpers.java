package com.example.root.lab8test;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
/**
 * Created by root on 4/5/16.
 */
public class SqlHelpers extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 12;
    // Database Name
    private static final String DATABASE_NAME = "Stockdb";
    // Stocks table name
    private static final String TABLE_STOCKS = "stocks";
    private static final String TABLE_ALERTS = "alerts";
    private static final String TABLE_CREDENTIALS = "credentials";
    // Stocks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_VOLUME = "volume";
    private static final String KEY_BUYSELL = "buysellcall";
    private static final String KEY_CHANGE = "change";
    private static final String KEY_RATING = "rating";
    private static final String KEY_ALERT = "alertPrice";


    //private static final String KEY_IMAGE = "imageName";
    public SqlHelpers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create stock table
        String CREATE_STOCK_TABLE = "CREATE TABLE stocks ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "price REAL, volume REAL, buysellcall TEXT, change REAL, rating INTEGER)";
        String CREATE_ALERT_TABLE = "CREATE TABLE alerts ( " +
                "name TEXT, alertPrice REAL)";
        String CREATE_CREDENTIAL_TABLE = "CREATE TABLE credentials ( " +
                "email TEXT, password TEXT)";

        // create stocks table
        db.execSQL(CREATE_STOCK_TABLE);
        db.execSQL(CREATE_ALERT_TABLE);
        db.execSQL(CREATE_CREDENTIAL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older stocks table if existed
        db.execSQL("DROP TABLE IF EXISTS stocks");
        db.execSQL("DROP TABLE IF EXISTS alerts");
        db.execSQL("DROP TABLE IF EXISTS credentials");
        // create fresh stocks table
        this.onCreate(db);

        if(oldVersion == 3 && newVersion==4){

        }

    }

    /*CRUD operations (create "add", read "get", update, delete) */
    public void addStock(Stock stock) {
        Log.d("addStock", stock.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, stock.getName()); // get NAME
        values.put(KEY_PRICE, stock.getPrice()); // get author
        values.put(KEY_VOLUME, stock.getVolume()); // get NAME
        values.put(KEY_BUYSELL, stock.getBuySellCall());
        values.put(KEY_CHANGE, stock.getPercentageChange()); // get NAME
        values.put(KEY_RATING, stock.getRating());
       // values.put(KEY_SECTOR, stock.getSector());
        // 3. insert
        db.insert(TABLE_STOCKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values
        // 4. Close dbase
        db.close();
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

    public boolean addCredentials(String email, String password){

        Log.d("addcreds",email+password);
        boolean res = false;
            SQLiteDatabase db = this.getWritableDatabase();
            // 2. create ContentValues to add key "column"/value

            ContentValues values = new ContentValues();
            values.put("email", email); // get NAME
            values.put("password", password); // get author
            String query = "SELECT * FROM credentials";
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                   do{
                       if(cursor.getString(0).equals(email)&&cursor.getString(1).equals(password))
                       {
                           res=true;
                       }
                       else {

                           res= false;
                       }
                   }while(cursor.moveToNext());


                }else {
                String query1 = "INSERT INTO credentials VALUES('"+email+"','"+password+"');";
                db.rawQuery(query1,null);
                res = true;
            }

        db.close();
       return res;
            // 4. Close dbase

        }



    public ArrayList<String> getHighestRatedNames() {
        ArrayList<String> highestRatedNAMEs = new ArrayList<String>();
        String query = "SELECT  MAX(" + KEY_RATING + ") AS RATING FROM " + TABLE_STOCKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int result = (cursor.getInt(cursor.getColumnIndex("RATING")));
        String query1 = "SELECT  " + KEY_NAME + " FROM " + TABLE_STOCKS + " WHERE " + KEY_RATING + "=" + result;
        Cursor cursor1 = db.rawQuery(query1, null);
        if (cursor1.moveToFirst()) {
            do {
                highestRatedNAMEs.add(cursor1.getString(0));
            } while (cursor1.moveToNext());
        }
        return highestRatedNAMEs;
    }

    public ArrayList<String> getLowestRatedNames() {
        ArrayList<String> lowestRatedNAMEs = new ArrayList<String>();
        String query = "SELECT  MIN(" + KEY_RATING + ") AS RATING FROM " + TABLE_STOCKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int result = (cursor.getInt(cursor.getColumnIndex("RATING")));
        String query1 = "SELECT  " + KEY_NAME + " FROM " + TABLE_STOCKS + " WHERE " + KEY_RATING + "=" + result;
        Cursor cursor1 = db.rawQuery(query1, null);
        if (cursor1.moveToFirst()) {
            do {
                lowestRatedNAMEs.add(cursor1.getString(0));
            } while (cursor1.moveToNext());
        }
        return lowestRatedNAMEs;
    }

    public ArrayList<String> getHighestPositiveChangedStocks() {
        ArrayList<String> highestChanged = new ArrayList<String>();
        String query = "SELECT  " + KEY_NAME + "," + KEY_PRICE + "," + KEY_CHANGE + " FROM " + TABLE_STOCKS + " ORDER BY change DESC LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String stockDetails;
                stockDetails = cursor.getString(0) + ", " + cursor.getDouble(1) + ", " + cursor.getDouble(2);
                highestChanged.add(stockDetails);
            } while (cursor.moveToNext());

        }
        return highestChanged;
    }


    public ArrayList<String> getHighestNegativeChangedStocks() {
        ArrayList<String> highestNegativeChanged = new ArrayList<String>();
        String query = "SELECT  " + KEY_NAME + "," + KEY_PRICE + "," + KEY_CHANGE + " FROM " + TABLE_STOCKS + " ORDER BY change LIMIT 5";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String stockDetails;
                stockDetails = cursor.getString(0) + ", " + cursor.getDouble(1) + ", " + cursor.getDouble(2);
                highestNegativeChanged.add(stockDetails);
            } while (cursor.moveToNext());

        }
        return highestNegativeChanged;
    }
    // Get All stocks with BUY call
    public ArrayList<Stock> getAllStocksWithBuyCall() {
        ArrayList<Stock> stocks = new ArrayList<Stock>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_STOCKS+" WHERE buysellcall LIKE 'BUY'";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build stock and add it to list
        Stock stock = null;
        if (cursor.moveToFirst()) {
            do {
                stock = new Stock();
                stock.setId(Integer.parseInt(cursor.getString(0)));
                stock.setName(cursor.getString(1));
                stock.setPrice(cursor.getDouble(2));
                stock.setVolume(cursor.getDouble(3));
                stock.setBuySellCall(cursor.getString(4));
                stock.setPercentageChange(cursor.getDouble(5));
                stock.setRating(cursor.getInt(6));
             //   stock.setSector(cursor.getColumnName(7));
                // Add stock to stocks
                stocks.add(stock);
            } while (cursor.moveToNext());
        }
        Log.d("getAllstocks()", stocks.toString());
        return stocks; // return stocks
    }

    // Get All stocks with BUY call
    public ArrayList<Stock> getAllStocksWithSellCall() {
        ArrayList<Stock> stocks = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_STOCKS+" WHERE buysellcall LIKE 'SELL'";
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        // 3. go over each row, build stock and add it to list
        Stock stock = null;
        if (cursor.moveToFirst()) {
            do {
                stock = new Stock();
                stock.setId(Integer.parseInt(cursor.getString(0)));
                stock.setName(cursor.getString(1));
                stock.setPrice(cursor.getDouble(2));
                stock.setVolume(cursor.getDouble(3));
                stock.setBuySellCall(cursor.getString(4));
                stock.setPercentageChange(cursor.getDouble(5));
                stock.setRating(cursor.getInt(6));
               // stock.setSector(cursor.getColumnName(7));
                // Add stock to stocks
                stocks.add(stock);
            } while (cursor.moveToNext());
        }
        Log.d("getAllstocks()", stocks.toString());
        return stocks; // return stocks
    }
    // Updating single stock

    public int updateStock(Stock stock, String newName, double newVolume, double newPrice, String newCall, double newChange, int newRating) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", newName); // get NAME
        values.put("volume", newVolume);
        values.put("price", newPrice); // get author
        values.put("buysellcall", newCall);
        values.put("percentagechange", newChange); // get author
        values.put("rating", newRating);
        // 3. updating row
        int i = db.update(TABLE_STOCKS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(stock.getId()) }); //selection args
        // 4. close dbase
        db.close();
        Log.d("UpdateStock", stock.toString());
        return i;
    }
    // Deleting single stock
    public void deleteStock(Stock stock) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        // 2. delete
        db.delete(TABLE_STOCKS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(stock.getId()) });
        // 3. close
        db.close();
        Log.d("deleteStock", stock.toString());
    }
    public int getIds()
    {
        String selectQuery = "SELECT id FROM stocks";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        Log.d("Count=",String.valueOf(total));
        return total;
    }
}
