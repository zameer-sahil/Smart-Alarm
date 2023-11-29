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

    public void insertData(long d, boolean res)
    {

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(DatabaseClass.time, d);
        contentValues.put(DatabaseClass.result, res);

            dbb.insert(DatabaseClass.tableName, null , contentValues);

    }
    public void deleteData(long d)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(DatabaseClass.tableName, " time = ?", new String[]{String.valueOf(d)});
    }
    public void update(long day, long d, boolean res)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseClass.time, day);
        contentValues.put(DatabaseClass.result, res);

        dbb.update(DatabaseClass.tableName, contentValues , "time = ?", new String[]{String.valueOf(d)});
        dbb.close();
    }

    public void updateSwitch(long day, boolean res)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseClass.result, res);

        dbb.update(DatabaseClass.tableName, contentValues , "time = ?", new String[]{String.valueOf(day)});
        dbb.close();
    }
    public Cursor getCursor()
    {
            SQLiteDatabase db = myhelper.getReadableDatabase();
            String[] col = {DatabaseClass.time, DatabaseClass.result};
            Cursor  cursor = db.query(DatabaseClass.tableName, col,
                    null,null, null, null, "time ASC");
            return cursor;
    }

}
