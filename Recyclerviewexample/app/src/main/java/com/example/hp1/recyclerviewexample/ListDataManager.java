package com.example.hp1.recyclerviewexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hp1 on 11/2/2017.
 */

public class ListDataManager {

    private static final String TAG = "TESTLOG";
    private SQLiteDatabase database;
    private ItemData itemData;
    private ArrayList<ItemData.Item> itemsList;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "List_items.db";

    private static final String CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.TABLE_NAME + " (" +
                    DBContract._ID + " INTEGER PRIMARY KEY," +
                    DBContract.COL1_IMAGE_ID + " INTEGER NOT NULL," +
                    DBContract.COL2_BIRD_NAME + " TEXT NOT NULL)";

    public ListDataManager(Context context){
        DBSetupHelper dbSetupHelper =
                new DBSetupHelper(context, DB_NAME, null, DB_VERSION);
        database = dbSetupHelper.getWritableDatabase();
        itemData = ItemData.getInstance();
    }



    public void loadData(){
        for(int i=0; i<10; i++){
            ItemData.Item item = itemData.next();
            if(insert(item.getImageId(), item.getItemText()))
                Log.i(TAG, "Item " + i + " inserted");
        }
    }

    public boolean insert(int image_id, String bird_name){
        ContentValues cv = new ContentValues();
        cv.put(DBContract.COL1_IMAGE_ID, image_id);
        cv.put(DBContract.COL2_BIRD_NAME, bird_name);
        return database.insert(DBContract.TABLE_NAME, null, cv) > 0;
    }

    public ArrayList<ItemData.Item> getAllRows() {

        String[] projection = {
                DBContract._ID,
                DBContract.COL1_IMAGE_ID,
                DBContract.COL2_BIRD_NAME
        };

        String sortOrder =
                DBContract.COL2_BIRD_NAME + " ASC";

        Cursor cursor = database.query(
                DBContract.TABLE_NAME,      // The table to query
                projection,                 // The columns to return
                null,                       // The columns for the WHERE clause
                null,                       // The values for the WHERE clause
                null,                       // don't group the rows
                null,                       // don't filter by row groups
                sortOrder                        // The sort order
        );

        itemsList = new ArrayList<>();
        while (cursor.moveToNext()) {
            ItemData.Item item = itemData.getNewItem(
                    cursor.getInt(cursor.getColumnIndex(DBContract.COL1_IMAGE_ID)),
                    cursor.getString(cursor.getColumnIndex(DBContract.COL2_BIRD_NAME)));
            itemsList.add(item);
        }
        cursor.close();
        return itemsList;
    }

    public class DBSetupHelper extends SQLiteOpenHelper {

        public DBSetupHelper(Context context, String db_name,
                             SQLiteDatabase.CursorFactory factory, int version) {
            super(context, db_name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(CREATE_ENTRIES);
    }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                              int oldVersion, int newVersion) {
        }
    }


}

