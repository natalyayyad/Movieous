package com.example.movieapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.movieapp.models.Movie;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static DataBaseHelper sInstance;

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private DataBaseHelper(Context context) {
        super(context, "User", null, 4);
    }

    public static synchronized DataBaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you 
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USER(EMAIL TEXT PRIMARY KEY,NAME TEXT, GENDER TEXT, PASSWORD TEXT,PHONE TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE WATCHLIST(EMAIL TEXT, ID STRING, TITLE TEXT, DATE TEXT, TIME TEXT, URL TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE RATEDLIST(EMAIL TEXT, ID STRING ,TITLE TEXT, RATE TEXT, URL TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE COMMENT(EMAIL TEXT, ID STRING ,COMMENT TEXT, NAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public Integer authenticateUser(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        try {
            Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM USER", null);
            int emailIndex = c.getColumnIndex("EMAIL");
            int passIndex = c.getColumnIndex("PASSWORD");
            c.moveToFirst();
            while (c != null) {
                {
                    String savedEmail = c.getString(emailIndex);
                    String savedPass = c.getString(passIndex);
                    Log.i("Table Content:", savedPass + savedEmail);

                    if (savedEmail.equals(email)) {

                        if (savedPass.equals(password))

                            return 0;
                        else
                            return 1;
                    }
                }
                c.moveToNext();
            }
        } catch (Exception e) {
            return 1;
            //e.printStackTrace();
        }
        return 1;
    }

    public void insert(String email,String name,String gender,String password, String Phone) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("NAME",name);
        contentValues.put("GENDER", gender);
        contentValues.put("PASSWORD", password);
        contentValues.put("PHONE", Phone);
        sqLiteDatabase.insert("User", null, contentValues);
    }

    // get all user Info from DB
    public Cursor getUserInfo(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE EMAIL = ?",new String[] {email});
        if (c.moveToFirst())
            return c;
        return null;
    }

    public String getUserName (String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT NAME FROM USER WHERE EMAIL = ?",new String[] {email});
        int index = c.getColumnIndex("NAME");
        c.moveToFirst();
        String re;
        if(c != null){
            try {
                String s = c.getString(index);
                return  s;
            }catch (Exception e){
                e.printStackTrace();
            }
            c.moveToNext();
        }

        return "USER";
    }


    // Update a user name  in DB
    public void updateUserName(String table,String email,String name) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE  "+table+" SET "+" NAME "+" =\'" + name+"\'  WHERE "+" EMAIL " +" =\'"+email+"\'");
    }

    // update user password im db
    public void updatePassword(String table,String email,String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE  " + table + " SET " + " PASSWORD " + " =\'" + password + "\'  WHERE " + " EMAIL " + " =\'" + email + "\'");
    }
    //update user phone in DB
    public void updatePhone(String table,String email,String phone) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE  "+table +" SET "+" PHONE "+" =\'" + phone+"\'  WHERE "+" EMAIL " +" =\'"+email+"\'");
    }



    public Cursor getMovies() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM MOVIE", null);
    }

    public void insertWatchMovie(String email,String id,String title,String url) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("ID",id);
        contentValues.put("URL",url);
        contentValues.put("TITLE",title);
        contentValues.put("DATE",LocalDate.now()+"");
        contentValues.put("TIME",LocalTime.now(TimeZone.getTimeZone("Asia/Jerusalem").toZoneId())+"");
        sqLiteDatabase.insert("WATCHLIST", null, contentValues);
    }

    public void removeWatchMovie(String email, String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM WATCHLIST WHERE EMAIL ='"+email+"' AND ID="+id);
    }

    public Cursor getWatchList(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM WATCHLIST WHERE EMAIL='"+email+"'", null);
    }

    public boolean checkifExists(String Table, String email, String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+Table+" WHERE EMAIL='"+email+"' AND ID="+id,null);
        if(cursor.getCount()==0)
            return false;
        return true;
    }

    public String getRate(String email, String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM RATEDLIST WHERE EMAIL='"+email+"' AND ID="+id, null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("RATE"));
    }

    public void updateRate(String email, String id, String rate) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String updateDBQuery = "UPDATE  RATEDLIST SET RATE='"+rate+"' WHERE EMAIL = '"+email+"' AND ID ="+id;
        sqLiteDatabase.execSQL(updateDBQuery);
    }

    public void insertRatedMovie(String email,String id,String title ,String rate, String url) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("ID",id);
        contentValues.put("RATE",rate);
        contentValues.put("URL",url);
        contentValues.put("TITLE",title);
        sqLiteDatabase.insert("RATEDLIST", null, contentValues);
    }

    public Cursor getRatedList(String Email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM RATEDLIST WHERE EMAIL='"+Email+"'", null);
    }

    public void insertComment(String email,String id, String name, String comment) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", email);
        contentValues.put("ID",id);
        contentValues.put("NAME", name);
        contentValues.put("COMMENT", comment);
        sqLiteDatabase.insert("Comment", null, contentValues);
    }

    public Cursor getComments(String id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM COMMENT WHERE ID="+id, null);
    }

}