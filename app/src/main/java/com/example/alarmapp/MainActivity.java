package com.example.alarmapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarmapp.Activities.SetAlramActivity;
import com.example.alarmapp.Activities.TempActivity;
import com.example.alarmapp.Classes.DatabaseClass;
import com.example.alarmapp.Classes.ModelClass;
import com.example.alarmapp.Classes.MyBroadcastRec;
import com.example.alarmapp.Classes.MySqlClass;
import com.example.alarmapp.Classes.RecyclerView2;
import com.example.alarmapp.Interfaces.MysqlDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView txt, txt2;
    TimePickerDialog dialog;
    FloatingActionButton actionButton;
    FrameLayout frameLayout;
    LinkedList<ModelClass> list;
    RecyclerView2 recyclerClass;
    RecyclerView recyclerView;
    SQLiteOpenHelper obj;
    MySqlClass mySqlClass ;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint({"MissingInflatedId", "ScheduleExactAlarm"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionButton = findViewById(R.id.floatingActionButton);

        recyclerView = findViewById(R.id.recyle);
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        actionButton.setVisibility(View.VISIBLE);
        list = new LinkedList<ModelClass>();
        // Set default night mode based on system setting
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);


        obj = new DatabaseClass(this);
        mySqlClass = new MySqlClass(this);

        actionButton.setOnClickListener(e->
        {
            Intent i = new Intent(MainActivity.this, SetAlramActivity.class);
            startActivity(i);
            finish();
        });


//        int d = getIntent().getIntExtra("day", 0);
//        int m = getIntent().getIntExtra("month", 0);
//        int y = getIntent().getIntExtra("year", 0);
//        int h = getIntent().getIntExtra("hour_day", 0);
//        int min = getIntent().getIntExtra("min_day", 0);
//        String am = getIntent().getStringExtra("AM");
//        Toast.makeText(this, d+"/"+m+"/"+y, Toast.LENGTH_SHORT).show();
//
//        list.add(new ModelClass());

        Cursor cursor = mySqlClass.getCursor();

        Calendar calendar ;
        AlarmManager alarmManagers;
        PendingIntent pendingIntent;
        int i=-1, j=-1;

        while (cursor.moveToNext()) {

            i++;
            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli((cursor.getLong(0))), ZoneId.systemDefault());
            int y = dateTime.getYear();
            int m = dateTime.getMonthValue();
            int d = dateTime.getDayOfMonth();
            int h = dateTime.getHour();
            int min = dateTime.getMinute();
            int intValue = cursor.getInt(1);

            // Convert the integer value to a boolean
            boolean booleanValue = (intValue == 1);
            if (h>12)
                h = h-12;
            else if(h==0)
                h+=12;
            if (m>12)
                m-=12;
            else if(m==0)
                m+=1;
            String am = dateTime.format(DateTimeFormatter.ofPattern("a"));

                list.add(new ModelClass(h, min, am, y, m, d, (cursor.getLong(0)), booleanValue));
//            calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(cursor.getLong(0));
//            Intent intent1= new Intent(MainActivity.this, MyBroadcastRec.class);
//            intent1.putExtra("time", calendar.getTimeInMillis());
//            alarmManagers = (AlarmManager) getSystemService(ALARM_SERVICE);
//            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, i, intent1, PendingIntent.FLAG_MUTABLE);
//                alarmManagers.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

//            if (booleanValue)
//            {
//                alarmManagers.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//            }
//            else
//                alarmManagers.cancel(pendingIntent);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerClass = new RecyclerView2(list, this);
        recyclerClass.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerClass);

        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0
        );
    }


}