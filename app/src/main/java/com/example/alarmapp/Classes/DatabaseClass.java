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
    public static final String DBName = "DB4";
    public static final int version = 3;
    public static final String tableName = "AlarmTable2";
    public static final String id = "id";
    public static final String time = "time";
    public static final String result = "isOn";
    public static final String createTable = "create table if not exists "+tableName
            +" ( " +id+"  INTEGER PRIMARY KEY Autoincrement, "
            +time+" INTEGER unique," + result+ " Boolean );";



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
