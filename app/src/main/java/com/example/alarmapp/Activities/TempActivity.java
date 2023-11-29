package com.example.alarmapp.Activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alarmapp.Classes.MyBroadcastRec;
import com.example.alarmapp.MainActivity;
import com.example.alarmapp.R;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TempActivity extends AppCompatActivity {

    TextView timePicker;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        timePicker = findViewById(R.id.textTime);
//        long time = getIntent().getLongExtra("getTime", 0);
//        Toast.makeText(this, "time = "+ time, Toast.LENGTH_SHORT).show();
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
        int y = dateTime.getYear();
        int m = dateTime.getMonthValue();
        int d = dateTime.getDayOfMonth();
        int h = dateTime.getHour();
        int min = dateTime.getMinute();


        // Convert the integer value to a boolean
        if (h>12)
            h = h-12;
        else if(h==0)
            h+=12;
        if (m>12)
            m-=12;
        else if(m==0)
            m+=1;
        String am = dateTime.format(DateTimeFormatter.ofPattern("a"));
        timePicker.setText(h+":"+min);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void cancelBtn(View view) {
        startActivity(new Intent(TempActivity.this, MainActivity.class));
    }
}