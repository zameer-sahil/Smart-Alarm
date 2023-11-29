package com.example.alarmapp.Activities;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.alarmapp.Classes.MySqlClass;
import com.example.alarmapp.MainActivity;
import com.example.alarmapp.R;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class SetAlramActivity extends AppCompatActivity {

    Button cancelBtn, okBtn;
    ImageView keyboard;
    TextView text, textDate;
    TimePicker timePicker;
    int year = 0;
    int month = 0;
    int day = 0;
    String am = "";
    MySqlClass obj ;
    Calendar cal;
    int y, m, d;
    boolean result = false;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alram);
        cancelBtn = findViewById(R.id.cancelBtn);
        okBtn = findViewById(R.id.okBtn);
        keyboard = findViewById(R.id.keyboard);
        text = findViewById(R.id.textDate);
        timePicker = findViewById(R.id.timePicker);
        textDate = findViewById(R.id.textDate);
        obj= new MySqlClass(this);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SetAlramActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        timePicker.setHour(getIntent().getIntExtra("hour2",0 ));
        timePicker.setMinute(getIntent().getIntExtra("min2",0 ));
        timePicker.setIs24HourView(false);

        int currentHour = timePicker.getHour();
        int mins  = timePicker.getMinute();
        String am_pm = getIntent().getStringExtra("am2");
        int year2 = getIntent().getIntExtra("year2",0 );
        int mon2 = getIntent().getIntExtra("month2",0 );
        int day2 = getIntent().getIntExtra("day2",0 );
        result = getIntent().getBooleanExtra("update", false);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {


                    int hour = timePicker.getHour(), min = timePicker.getMinute();


                    if (hour == 0) {
                        am = "AM";
                        hour = 12;
                    } else if (hour == 12) {
                        am = "PM";

                    } else if (hour > 12) {
                        am = "PM";
                        hour -= 12;
                    } else
                        am = "AM";
                    if (result)
                    {
                        obj.update(currentHour,mins,am_pm,day2, hour, min, am, year, month, day);
                    }
                    else obj.insertData(hour, min, am, year, month, day);
                    Intent i = new Intent(SetAlramActivity.this, MainActivity.class);
                    i.putExtra("hour_day", hour);
                    i.putExtra("min_day", min);
                    i.putExtra("AM", am);
                    i.putExtra("year", year);
                    i.putExtra("month", month);
                    i.putExtra("day", day);
                    startActivity(i);

                    finish();

                }
        });

         cal = Calendar.getInstance();
         y = cal.get(Calendar.YEAR);
         m = cal.get(Calendar.MONTH);
         d = cal.get(Calendar.DAY_OF_WEEK);

        textDate.setText(getWeek(0)+d+" "+getMonth(0));
        keyboard.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(SetAlramActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;

                        cal.set(i, i1, i2);
                        String week = "";
                        int d = cal.get(Calendar.DAY_OF_WEEK);
                        if (d == 1)
                            week = "sun, ";
                        else  if(d == 2)
                            week = "Mon, ";
                        else if(d == 3)
                            week = "Tues, ";
                        else  if(d == 4)
                            week = "Wed, ";
                        else if(d == 5)
                            week = "Thu, ";
                        else  if(d == 6)
                            week = "Fri, ";
                        else if(d == 7)
                            week = "Sat, ";
                        if (month+1 == 1)
                            textDate.setText(week+i2+" Jan ");
                        else if (month+1 == 2)
                            textDate.setText(week+i2+" Feb ");
                       else  if (month+1 == 3)
                            textDate.setText(week+i2+" March ");
                       else if (month+1 == 4)
                            textDate.setText(week+i2+" April ");
                        else if (month+1 == 5)
                            textDate.setText(week+i2+" May ");
                        else  if (month+1 == 6)
                            textDate.setText(week+i2+" June ");
                        else if (month+1 == 7)
                            textDate.setText(week+i2+" July ");
                        else if (month+1 == 8)
                            textDate.setText(week+i2+" August ");
                        else  if (month+1 == 9)
                            textDate.setText(week+i2+" Sept ");
                        else if (month+1 == 10)
                            textDate.setText(week+i2+" Oct ");
                        else  if (month+1 == 11)
                            textDate.setText(week+i2+" Nov ");
                        else if (month+1 == 12)
                            textDate.setText(week+i2+" Dec ");
                    }
                }, y, m, d);

                dialog.show();
            }
        });

    }

    public String getWeek(int c)
    {

        String week = "";

        if (d == 1)
            week = "sun, ";
        else  if(d == 2)
            week = "Mon, ";
        else if(d == 3)
            week = "Tues, ";
        else  if(d == 4)
            week = "Wed, ";
        else if(d == 5)
            week = "Thu, ";
        else  if(d == 6)
            week = "Fri, ";
        else if(d == 7)
            week = "Sat, ";
        return week;

    }
    public String getMonth(int b)
    {
        String mon = "";
        if (m+1 == 1)
            mon = " Jan ";
    else if (m+1 == 2)
        mon = " Feb ";
    else  if (m+1 == 3)
            mon = " March ";
    else if (m+1 == 4)
            mon =" April ";
    else if (m+1 == 5)
            mon  = " May ";
    else  if (m+1 == 6)
            mon = " June ";
    else if (m+1 == 7)
            mon = " July ";
    else if (m+1 == 8)
            mon = " August ";
    else  if (m+1 == 9)
            mon = " Sept ";
    else if (m+1 == 10)
            mon = " Oct ";
    else  if (m+1 == 11)
            mon = " Nov ";
    else if (m+1 == 12)
            mon = " Dec ";

        return mon;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SetAlramActivity.this, MainActivity.class);
        startActivity(i);
    }
}