package com.example.timemanagementsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Activities.db";
    private static final String TABLE_NAME="Activities";
    private static final String COL_1="ID";
    private static final String COL_2="ACTIVITY_TYPE";
    private static final String COL_3="DATE";
    private static final String COL_4="TIME";
    private static final String COL_5="SOLO_TEAM";
    private static final String COL_6="DESCRIPTION";

    public MyDatabase(Context context) {
        super(context,DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " +TABLE_NAME+" "+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,ACTIVITY_TYPE TEXT,DATE TEXT,TIME TEXT,SOLO_TEAM TEXT,DESCRIPTION TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean isInsert(String activity_type,String date,String time,String solo_team,String description)
    {

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,activity_type);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,time);
        contentValues.put(COL_5,solo_team);
        contentValues.put(COL_6,description);
        long res=  database.insert(TABLE_NAME,null,contentValues);
        if (res==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }


    public Cursor getAllData()
    {

        SQLiteDatabase database=this.getReadableDatabase();
        Cursor result=database.rawQuery("select * from "+TABLE_NAME,null);
        return result;

    }
    public boolean isUpdate(String id, String activity_type, String date, String time, String solo_team,String description)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,activity_type);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,time);
        contentValues.put(COL_5,solo_team);
        contentValues.put(COL_6,description);
        database.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;


    }

    public int deleteData(String id)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
}
