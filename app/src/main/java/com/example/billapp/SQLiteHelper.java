package com.example.billapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }
    public void insertData(String name, String price, String category, byte[] image){
        SQLiteDatabase database=getWritableDatabase();
        String sql="INSERT INTO BILLS VALUES(NULL,?,?,?,?)";


        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindString(3, category);
        statement.bindBlob(4,image);
    statement.executeInsert();
    }

    public Cursor getData(String sql){
    SQLiteDatabase database = getReadableDatabase();
    return  database.rawQuery(sql,null);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
