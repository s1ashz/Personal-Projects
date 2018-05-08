package com.example.s1ash.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "itemListApp2.db";
    private static final String TABLE_ITEMS = "items";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ITEM_NAME = "itemname";
    private static final String COLUMN_ITEM_DESCRIPTION = "itemdescription";

    private static final String TAG = "itemListApp";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_ITEMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM_NAME + " TEXT," +
                COLUMN_ITEM_DESCRIPTION + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(query);

        Log.v(TAG, "Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_ITEMS;
        sqLiteDatabase.execSQL(query);
        //resetDatabase();
        onCreate(sqLiteDatabase);

        Log.v(TAG, "Database Updated");
    }

    public void addItem(Item item) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String name = item.get_itemname();
        String description = item.get_itemdescription();
        //onCreate(sqLiteDatabase);

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_DESCRIPTION, description);
        values.put(COLUMN_ITEM_NAME, name);

        sqLiteDatabase.insert(TABLE_ITEMS, null, values);
        sqLiteDatabase.close();

        Log.v(TAG, "Item Added to DATABASE: Name:" + name + "    Description: " + description);
    }

    public void deleteItem(String itemName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEM_NAME + "=\"" + itemName + "\";";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();

        Log.v(TAG, "Item DELECTED FROM to DATABASE: Name: " + itemName);
    }

    public Item getItem(String nameToSearch) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEM_NAME + "=\"" + nameToSearch + "\";";
        Item dbItem = null;


        Cursor c= sqLiteDatabase.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("itemname"))!=null){
                //Item item = new Item(c.getString( c.getColumnIndex("itemname")), c.getString( c.getColumnIndex("itemdescription")) );
                int id = Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_ID)));
                String name = c.getString( c.getColumnIndex("itemname"));
                String description = c.getString( c.getColumnIndex("itemdescription"));

                dbItem = new Item();
                dbItem.set_id(id);
                dbItem.set_itemname(name);
                dbItem.set_itemdescription(description);

                Log.v(TAG,"ITEMMMMMM:    " +dbItem.toString());
            }
            c.moveToNext();
        }
        sqLiteDatabase.close();
        return dbItem;


    }

    public List<String> getDatabaseItems() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        List<String> dbString = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while ( !c.isAfterLast() ) {
            if ( c.getString( c.getColumnIndex("itemname")) != null ) {
                dbString.add( c.getString( c.getColumnIndex("itemname")) );
            }
            c.moveToNext();
        }
        sqLiteDatabase.close();

        return dbString;
    }

    public void resetDatabase() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + TABLE_ITEMS;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.close();
        Log.v(TAG, "Database Reseted");
    }

}
