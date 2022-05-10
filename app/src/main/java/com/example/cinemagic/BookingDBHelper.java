package com.example.cinemagic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BookingDBHelper extends SQLiteOpenHelper {
    
    private Context context;
    private static final String DATABASE_NAME = "cinemagic.db";
    private static final int DATABASE_VERSION = 1;
    
    private static final String TABLE_NAME = "booking_mgmt";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "book_name";
    private static final String COLUMN_DATE = "book_date";
    private static final String COLUMN_THEATRE = "book_theatre";
    private static final String COLUMN_SHOWTIME = "book_showtime";
    private static final String COLUMN_TICKETCOUNT = "book_tickets";
    
    public BookingDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + 
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + 
                COLUMN_DATE + " TEXT, " + 
                COLUMN_THEATRE + " TEXT, " +
                COLUMN_SHOWTIME + " TEXT, " + 
                COLUMN_TICKETCOUNT + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    
    void addBooking(String name, String date, String theatre, String showtime, int ticketcount){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_THEATRE,theatre);
        cv.put(COLUMN_SHOWTIME,showtime);
        cv.put(COLUMN_TICKETCOUNT,ticketcount);
        
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context, "Creation Unsuccessful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Created Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String date, String theatre, String showtime, String ticketCount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_THEATRE,theatre);
        cv.put(COLUMN_SHOWTIME,showtime);
        cv.put(COLUMN_TICKETCOUNT,ticketCount);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Update Unsuccessful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Deletion Unsuccessful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
