package com.example.myim;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ConcurrentModificationException;

public class SQL extends SQLiteOpenHelper {

    public SQL(Context context) {
        super(context, "my.db", null, 1);
    }

    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE userinfo(" +"\n"+
                "id INTEGER PRIMARY KEY," +"\n"+
                "userid VARCHAR(100)," +"\n"+
                "usersig VARCHAR(255));");

        db.execSQL("CREATE TABLE friends(" +"\n"+
                "id INTEGER PRIMARY KEY," +"\n"+
                "indexx VARCHAR(255)," +"\n"+
                "friendId VARCHAR(255)," +"\n"+
                "nickName VARCHAR(255));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * 获取db对象
     */
    public static SQLiteDatabase getWriteDB(Context context) {
        SQL sql = new SQL(context);
        return sql.getWritableDatabase();
    }
    /**
     * 获取 Cursor对象
     */
    public static Cursor getCursor(Context context){
        SQLiteDatabase db = getWriteDB(context);
        Cursor cursor = db.query("friends",null,null,null,null,null,null);
        return cursor;
    }
}