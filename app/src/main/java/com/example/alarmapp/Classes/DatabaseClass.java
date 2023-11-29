package com.example.alarmapp.Classes;

import static java.nio.file.StandardOpenOption.CREATE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.alarmapp.Interfaces.MysqlDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseClass extends SQLiteOpenHelper {
    public static final String DBName = "DB2";
    public static final int version = 1;
    public static final String tableName = "AlarmTable";
    public static final String id = "id";
    public static final String hour = "hour";
    public static final String min = "min";
    public static final String am = "am";
    public static final String year = "year";
    public static final String month = "month";
    public static final String day = "day";
    public static final String createTable = "create table if not exists "+tableName
            +" ( " +id+"  INTEGER PRIMARY KEY Autoincrement, "
            +hour+" INTEGER , "
            +min+" INTEGER , "
            +am+" TEXT , "
            +year+" INTEGER  , "
            +month+" INTEGER, "
            +day+" INTEGER );";



    public DatabaseClass(@Nullable Context context) {
        super(context, DBName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                sqLiteDatabase.execSQL("Drop table if exists "+tableName );
    }



}
