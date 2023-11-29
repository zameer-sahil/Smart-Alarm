package com.example.alarmapp.Interfaces;

import com.example.alarmapp.Classes.DatabaseClass;

import java.sql.Connection;
import java.sql.Statement;

public interface MysqlDatabase {
     Statement createStatement2();
     Connection getConnection2 ();
}
