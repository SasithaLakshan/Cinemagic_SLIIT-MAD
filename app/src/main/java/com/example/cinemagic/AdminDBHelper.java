package com.example.cinemagic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDBHelper extends SQLiteOpenHelper {
    public AdminDBHelper(Context context) {
        super(context,"AdminDB.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase AdminDB) {
        AdminDB.execSQL("create table admin_users(username text primary key, email text, mobile text, adminExp text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase AdminDB, int i, int i1) {
        AdminDB.execSQL("drop table if exists admin_users");
    }

    public Boolean insertData(String username, String email, String mobile, String adminExp, String password){
        SQLiteDatabase AdminDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("mobile",mobile);
        contentValues.put("adminExp", adminExp);
        contentValues.put("password",password);

        long result = AdminDB.insert("admin_users",null,contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkUserName(String username){
        SQLiteDatabase AdminDB = this.getWritableDatabase();
        Cursor cursor = AdminDB.rawQuery("select * from admin_users where username = ?", new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase AdminDB = this.getWritableDatabase();
        Cursor cursor = AdminDB.rawQuery("select * from admin_users where username = ? and password = ?", new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}

