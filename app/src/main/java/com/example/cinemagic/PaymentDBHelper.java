package com.example.cinemagic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PaymentDBHelper extends SQLiteOpenHelper {

    public PaymentDBHelper(Context context) {
        super(context,"PaymentBD.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase loginDB) {
        loginDB.execSQL("create table payments(type text, name text, expDate text, cardNumber text primary key, username text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase loginDB, int i, int i1) {
        loginDB.execSQL("drop table if exists payments");
    }

    public Boolean insertData(String name, String type, String expDate, String cardNo, String userName){
        SQLiteDatabase loginDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type",type);
        contentValues.put("name",name);
        contentValues.put("expDate",expDate);
        contentValues.put("cardNumber",cardNo);
        contentValues.put("username",userName);
        long result = loginDB.insert("payments",null,contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkCardNo(String cardNo){
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("select * from payments where cardNumber = ?", new String[] {cardNo});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
//
//    public Boolean checkUserNamePassword(String username, String password){
//        SQLiteDatabase loginDB = this.getWritableDatabase();
//        Cursor cursor = loginDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username,password});
//        if(cursor.getCount()>0){
//            return true;
//        }else{
//            return false;
//        }
//    }

    public Cursor getData(String userName) {
        SQLiteDatabase loginDB = this.getWritableDatabase();
        Cursor cursor = loginDB.rawQuery("select * from payments where username = ?", new String[] {userName});


        Log.i("info", "ssfsfss");
        if (cursor == null) {
            return null;
        }
        if ((cursor != null) && (cursor.getCount() > 0)) {
            return null;
        }

        cursor.moveToPosition(0);
        return cursor;
    }

//    public boolean deleteData(String userName) {
//        SQLiteDatabase loginDB = this.getWritableDatabase();
//        Cursor cursor = loginDB.rawQuery("select * from users where username = ?", new String[] {userName});
//        if (cursor.getCount() > 0) {
//            long result = loginDB.delete("users", "username=?", new String[]{userName});
//            if (result == -1) {
//                return false;
//            } else {
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }
//
//    public Boolean updateData(String username, String email, String mobile, String password, String oldUserName){
//        SQLiteDatabase loginDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username",username);
//        contentValues.put("email",email);
//        contentValues.put("mobile",mobile);
//        contentValues.put("password",password);
//
//        Cursor cursor = loginDB.rawQuery("select * from users where username = ?", new String[] {oldUserName});
//        if (cursor.getCount() > 0) {
//            long result = loginDB.update("users", contentValues, "username=?", new String[] {oldUserName});
//            if(result == -1){
//                return false;
//            }else{
//                Log.i("info", "Updated");
//                return true;
//            }
//        } else {
//            return false;
//        }
//
//    }
}
