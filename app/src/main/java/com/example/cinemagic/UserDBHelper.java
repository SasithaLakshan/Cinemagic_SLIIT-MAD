package com.example.cinemagic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserDBHelper extends SQLiteOpenHelper {
    public UserDBHelper(Context context) {
        super(context,"UserDB.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase loginDB) {
        loginDB.execSQL("create table users(username text primary key, email text, mobile text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase loginDB, int i, int i1) {
        loginDB.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String email, String mobile, String password){
        SQLiteDatabase loginDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("mobile",mobile);
        contentValues.put("password",password);
        long result = loginDB.insert("users",null,contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkUserName(String username){
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("select * from users where username = ?", new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public Cursor getData(String userName) {
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("select * from users where username = ?", new String[] {userName});
        cursor.moveToPosition(0);
//        Log.i("info", cursor.getString(2));
//        Log.i("info", String.valueOf(cursor.getColumnCount()));
        return cursor;
    }

    public boolean deleteData(String userName) {
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("select * from users where username = ?", new String[] {userName});
        if (cursor.getCount() > 0) {
            long result = loginDB.delete("users", "username=?", new String[]{userName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean updateData(String username, String email, String mobile, String password, String oldUserName){
        SQLiteDatabase loginDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("mobile",mobile);
        contentValues.put("password",password);

        Cursor cursor = loginDB.rawQuery("select * from users where username = ?", new String[] {oldUserName});
        if (cursor.getCount() > 0) {
            long result = loginDB.update("users", contentValues, "username=?", new String[] {oldUserName});
            if(result == -1){
                return false;
            }else{
                Log.i("info", "Updated");
                return true;
            }
        } else {
            return false;
        }

    }
}
