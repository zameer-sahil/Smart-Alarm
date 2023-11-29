package com.example.alarmapp.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySqlClass {
    SQLiteOpenHelper myhelper;
    public MySqlClass(Context context)
    {
         myhelper = new DatabaseClass(context);
    }

    public void insertData(int h, int m, String am, int y, int mon, int d)
    {

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseClass.hour, h);
            contentValues.put(DatabaseClass.min, m);
            contentValues.put(DatabaseClass.am, am);
            contentValues.put(DatabaseClass.year, y);
            contentValues.put(DatabaseClass.month, mon);
            contentValues.put(DatabaseClass.day, d);

            dbb.insert(DatabaseClass.tableName, null , contentValues);

    }
    public void deleteData(int h, int m, String am, int d)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(DatabaseClass.tableName, "hour = ? AND min = ? AND am = ? AND day = ?", new String[]{Integer.toString(h), Integer.toString(m), am, Integer.toString(d)});
    }
    public void update(int h, int m, String am, int d, int hour, int min, String am_pm, int year, int month, int day)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseClass.hour, hour);
        contentValues.put(DatabaseClass.min, min);
        contentValues.put(DatabaseClass.am, am_pm);
        contentValues.put(DatabaseClass.year, year);
        contentValues.put(DatabaseClass.month, month);
        contentValues.put(DatabaseClass.day, day);

        dbb.update(DatabaseClass.tableName, contentValues , "hour = ? AND min = ? AND am = ? AND day = ?", new String[]{Integer.toString(h), Integer.toString(m), am, Integer.toString(d)});
    }
    public Cursor getCursor()
    {
            SQLiteDatabase db = myhelper.getReadableDatabase();
            String[] col = {DatabaseClass.hour, DatabaseClass.min, DatabaseClass.am,
                    DatabaseClass.year, DatabaseClass.month, DatabaseClass.day};
            Cursor  cursor = db.query(DatabaseClass.tableName, col,
                    null,null, null, null, "hour ASC");
            return cursor;
    }

}
