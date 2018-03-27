package com.daniel.homecard.module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Database
 * Created by Daniel on 2018/3/27.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "card";
    public static final String SQL_MALE_LIST = " SELECT cardId, cardName, cardDesc, count FROM Male LEFT JOIN Card ON Male.cardID = Card._id; ";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Card (_id INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, cardName TEXT NOT NULL, cardDesc TEXT);");
        db.execSQL("INSERT INTO Card (cardName,cardDesc) VALUES ('按摩卡','');");
        db.execSQL("INSERT INTO Card (cardName,cardDesc) VALUES ('跑步卡','');");
        db.execSQL("INSERT INTO Card (cardName,cardDesc) VALUES ('抚触卡','');");
        db.execSQL("INSERT INTO Card (cardName,cardDesc) VALUES ('吃饭卡','');");
        db.execSQL("INSERT INTO Card (cardName,cardDesc) VALUES ('不生气卡','');");
        db.execSQL("INSERT INTO Card (cardName,cardDesc) VALUES ('读书卡','');");
        db.execSQL("INSERT INTO Card (cardName,cardDesc) VALUES ('音乐卡','');");

        db.execSQL("CREATE TABLE Male (_id INTEGER PRIMARY KEY AUTOINCREMENT, cardID INTEGER UNIQUE NOT NULL, count INTEGER);");
        db.execSQL("CREATE TABLE Female (_id INTEGER PRIMARY KEY AUTOINCREMENT, cardID INTEGER UNIQUE NOT NULL, count INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<CardBean> getMaleCardList() {
        return getUserCardList("Male");
    }

    public List<CardBean> getFemaleCardList() {
        return getUserCardList("Female");
    }

    public List<CardBean> getUserCardList(String tableName) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(" SELECT cardId, cardName, cardDesc," +
                " count FROM "+tableName+" LEFT JOIN Card "+
                "ON "+tableName+".cardID = Card._id; ", null);
        List<CardBean> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            int cardId = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            int count = cursor.getInt(3);
            result.add(new CardBean(cardId, name, desc, count));
        }
        Log.i("database", result.toString());
        cursor.close();
        readableDatabase.close();
        return result;
    }

    public void insertMaleCard(int cardId) {
        insertUserCard(cardId,"Male");
    }

    public void insertFemaleCard(int cardId) {
        insertUserCard(cardId,"Female");
    }

    private void insertUserCard(int cardId, String tableName) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = writableDatabase.query(tableName, new String[]{"count"}, "cardId=?", new String[]{cardId + ""}, null, null, null);
        if (cursor.moveToNext()) {
            //update
            int old = cursor.getInt(0);
            ContentValues contentValues = new ContentValues();
            contentValues.put("count", ++old);
            writableDatabase.update(tableName, contentValues, "cardId=?", new String[]{cardId + ""});
        } else {
            //insert
            ContentValues contentValues = new ContentValues();
            contentValues.put("cardId",cardId);
            contentValues.put("count", 1);
            writableDatabase.insert(tableName, null, contentValues);
        }
        cursor.close();
        writableDatabase.close();
    }

    public void consumeMaleCardOnce(int cardId){
        consumeCardOnce(cardId,"Male");
    }

    public void consumeFemaleCardOnce(int cardId){
        consumeCardOnce(cardId,"Female");
    }

    private void consumeCardOnce(int cardId, String tableName){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = writableDatabase.query(tableName, new String[]{"count"}, "cardId=?", new String[]{cardId + ""}, null, null, null);
        if (cursor.moveToNext()) {

            int old = cursor.getInt(0);
            if (old > 1) { //update
                ContentValues contentValues = new ContentValues();
                contentValues.put("count", --old);
                writableDatabase.update(tableName, contentValues, "cardId=?", new String[]{cardId + ""});
            } else {
                writableDatabase.delete(tableName, "cardId=?", new String[]{cardId + ""});
            }
        } else {
            throw new IllegalStateException("No related card data in table " + tableName);
        }
        cursor.close();
        writableDatabase.close();
    }
}
