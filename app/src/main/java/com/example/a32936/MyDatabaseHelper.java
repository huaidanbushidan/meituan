package com.example.a32936;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement, "
            + "UserName text, "
            + "UserCount text, "
            + "Password text)";

    private static final String CREATE_SHOP = "create table Shop ("
            + "id integer primary key autoincrement, "
            + "shopAccount integer,"
            + "owner text,"
            + "Shop_Name text, "
            + "Shop_Address text,"
            + "Shop_Picture integer)";

    private static final String CREATE_GOODS = "create table Goods ("
            + "id integer primary key autoincrement, "
            + "belongshop integer,"
            + "goodsName text,"
            + "goodsPrice real,"
            + "goodsPicture interger)";

    private static final String CREATE_BUYSHEET = "create table Buysheet ("
            + "id integer primary key autoincrement,"
            + "customer text,"
            + "goodsName text,"
            + "goodsPrice real,"
            + "goodsPicture interger)";

    private Context mContext;

    public MyDatabaseHelper(Context context) {
        super(context, "Shop.db", null, 2);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_SHOP);
        db.execSQL(CREATE_GOODS);
        db.execSQL(CREATE_BUYSHEET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Shop");
        db.execSQL("drop table if exists Goods");
        db.execSQL("drop table if exists Buysheet");
    }
}
