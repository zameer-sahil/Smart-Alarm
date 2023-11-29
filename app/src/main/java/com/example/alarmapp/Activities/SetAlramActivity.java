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
import android.widget.Toast;

import com.example.alarmapp.Classes.MySqlClass;
import com.example.alarmapp.MainActivity;
import com.example.alarmapp.R;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SetAlramActivity extends AppCompatActivity {

    Button cancelBtn, okBtn;
    ImageView keyboard;
    TextView text, textDate;
    TimePicker timePicker;
   static int year, month,day, hour, min;

    String am = "";
    MySqlClass obj;
    Calendar cal;
    int y, m, d;
    boolean result = false, res = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        obj = new MySqlClass(this);


        result = getIntent().getBooleanExtra("update", false);
        long getTime = getIntent().getLongExtra("millis", 0);
        Toast.makeText(this, result+" ", Toast.LENGTH_SHORT).show();
        hour = timePicker.getHour();
        min = timePicker.getMinute();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG_FORMAT, Locale.getDefault());
        String monthStr = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.getDefault());

        textDate.setText(dayOfWeek.substring(0, 3) + ", " + day + " " + monthStr.substring(0, 3));
        okBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


                hour = timePicker.getHour();
                min = timePicker.getMinute();

                Calendar calendar = Calendar.getInstance();

                calendar.set(year, month, day, hour, min);

                long millis = calendar.getTimeInMillis();

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
                if (result) {
                    obj.update(millis, getTime, true);
                } else obj.insertData(millis, true);
                Intent i = new Intent(SetAlramActivity.this, MainActivity.class);
                startActivity(i);

                finish();

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SetAlramActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        keyboard.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                res = true;
                getDate();
            }
        });


    }

    public void getDate() {
        cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_WEEK);

        DatePickerDialog dialog = new DatePickerDialog(SetAlramActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                year = i;
                month = i1;
                day = i2;

                cal.set(i, i1, i2);

                String[] dayAbbreviations = {"", "Sun, ", "Mon, ", "Tue, ", "Wed, ", "Thu, ", "Fri, ", "Sat, "};
                String[] monthNames = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                String week = dayAbbreviations[dayOfWeek];

                String formattedDate = week + i2 + " " + monthNames[month + 1];

                textDate.setText(formattedDate);
            }
        }, y, m, d);

        dialog.show();

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SetAlramActivity.this, MainActivity.class);
        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getFormattedTime(long messageTime) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(messageTime), TimeZone.getDefault().toZoneId());

        // Create a DateTimeFormatter object to format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the date and print the result
        String formattedTime = dateTime.format(formatter);
        return formattedTime;
    }
}
