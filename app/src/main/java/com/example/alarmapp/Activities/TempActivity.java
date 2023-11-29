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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alarmapp.Classes.MyBroadcastRec;
import com.example.alarmapp.R;

import java.util.Calendar;

public class TempActivity extends AppCompatActivity implements SensorEventListener {

    TimePicker timePicker;
    String am = "";
    Button btn;
    PendingIntent pendingIntent;
    TextView textView, textView2;
    SensorManager sensorManager;
    Sensor sensor;
    float count = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        textView = findViewById(R.id.texttt);
        textView2 = findViewById(R.id.texttt2);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
         sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sensor!=null)
        {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }


//        timePicker = findViewById(R.id.timePicker2);
//        btn =  findViewById(R.id.btn);
//        btn.setOnClickListener(e->{
//            Calendar cal = Calendar.getInstance();
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//            cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_WEEK)
//                    ,timePicker.getHour(), timePicker.getMinute(), 0 );
//            final long time = cal.getTimeInMillis();
//            Intent intent = new Intent(TempActivity.this, MyBroadcastRec.class);
//            pendingIntent = PendingIntent.getBroadcast(TempActivity.this, 10, intent, PendingIntent.FLAG_MUTABLE);
//            alarmManager.setRepeating(alarmManager.RTC_WAKEUP,time, alarmManager.INTERVAL_DAY, pendingIntent );
//            Toast.makeText(this, "set the notification", Toast.LENGTH_SHORT).show();
//        });

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textView.setText("sensor = "+sensorEvent.values[0]);
            float zValue = Math.abs(sensorEvent.values[2]);

        if (zValue>7)
        {
////// Get the DevicePolicyManager instance
//            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
////
//// Check if the app has been granted device admin permission
//            ComponentName componentName = new ComponentName(this, DeviceAdminReceiver.class);
//            if (devicePolicyManager.isAdminActive(componentName)) {
//                // If the app has admin permission, lock the screen
//                devicePolicyManager.lockNow();
//            } else {
//                // If the app does not have admin permission, request the permission
//                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
//                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
//                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "This app needs admin permission to lock the screen.");
//                startActivity(intent);
//            }


        }
        if (count<zValue)
        {
            count = zValue;
            textView2.setText( " " +count);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDestroy() {
//        sensorManager.unregisterListener(this, sensor);

        super.onDestroy();
    }
}