package com.example.alarmapp;

import androidx.appcompat.app.AppCompatActivity;
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

import java.util.Calendar;
import java.util.LinkedList;

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
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionButton = findViewById(R.id.floatingActionButton);
        frameLayout = findViewById(R.id.frame);
        recyclerView = findViewById(R.id.recyle);
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        actionButton.setVisibility(View.VISIBLE);
        list = new LinkedList<ModelClass>();

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

        Calendar[] calendar = new Calendar[cursor.getCount()];
        AlarmManager[] alarmManagers = new AlarmManager[cursor.getCount()];
        Intent[] intent = new Intent[cursor.getCount()];
        PendingIntent[] pendingIntent = new PendingIntent[cursor.getCount()];
        long[] time = new long[cursor.getCount()];
        int i=-1, j=-1;

        while (cursor.moveToNext()) {

            i++;
            int h = cursor.getInt(0);
            int min = cursor.getInt(1);
            String am = cursor.getString(2);
            int d = cursor.getInt(5);
            int m =cursor.getInt(4);
            int y = cursor.getInt(3);
            Toast.makeText(this, i+", ", Toast.LENGTH_SHORT).show();
            list.add(new ModelClass(h, min, am, y, m+1, d));
            calendar[i] = Calendar.getInstance();
            calendar[i].set(y, m, d, h, m, 0);
            intent[i] = new Intent(MainActivity.this, MyBroadcastRec.class);
            alarmManagers[i] = (AlarmManager) getSystemService(ALARM_SERVICE);
        }
        for(int k=0; k<cursor.getCount(); k++)
        {

            time[k] = calendar[k].getTimeInMillis();
            pendingIntent[k] = PendingIntent.getBroadcast(MainActivity.this, k, intent[k], PendingIntent.FLAG_MUTABLE);
            alarmManagers[k].setRepeating(alarmManagers[k].RTC_WAKEUP,time[k], alarmManagers[k].INTERVAL_DAY, pendingIntent[k]);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerClass = new RecyclerView2(list, this);
        recyclerView.setAdapter(recyclerClass);

        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0
        );
    }
}