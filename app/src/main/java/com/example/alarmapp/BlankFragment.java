package com.example.alarmapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alarmapp.Classes.MySqlClass;

import java.util.Calendar;


public class BlankFragment extends Fragment {
    Button cancelBtn, okBtn;
    ImageView keyboard;
    TextView text;
    TimePicker timePicker;
    int year = 0;
    int month = 0;
    int day = 0;
    MySqlClass obj ;

    public BlankFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_blank, container, false);
//        cancelBtn = v.findViewById(R.id.cancelBtn);
//        okBtn = v.findViewById(R.id.okBtn);
//        keyboard = v.findViewById(R.id.keyboard);
//        text = v.findViewById(R.id.textDate);
//        timePicker = v.findViewById(R.id.timePicker);
//        obj= new MySqlClass(getContext());
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getContext(), MainActivity.class);
//                getActivity().startActivity(i);
//            }
//        });
//
//        okBtn.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View view) {
//
//                int hour= timePicker.getHour(), min = timePicker.getMinute();
//                String am = "";
//
//                if (hour==0)
//                {
//                    am = "AM";
//                    hour=12;
//                }
//                else if(hour==12)
//                {
//                    am = "PM";
//
//                }
//                else if (hour > 12)
//                {
//                    am = "PM";
//                    hour-=12;
//                }
//                else
//                    am = "AM";
//
//                obj.insertData(hour, min, am, year, month, day);
//                Intent i = new Intent(getContext(), MainActivity.class);
//                i.putExtra("hour_day", hour);
//                i.putExtra("min_day", min);
//                i.putExtra("AM", am);
//                i.putExtra("year", year);
//                i.putExtra("month", month);
//                i.putExtra("day", day);
//                getActivity().startActivity(i);
//            }
//        });
//
//        Calendar cal = Calendar.getInstance();
//        int y = cal.get(Calendar.YEAR);
//        int m = cal.get(Calendar.MONTH);
//        int d = cal.get(Calendar.DAY_OF_MONTH);
//
//        keyboard.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View view) {
//                 DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                     @Override
//                     public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        year = i;
//                        month = i1;
//                        day = i2;
//                     }
//                 }, y, m, d);
//
//                 dialog.show();
//            }
//        });

        return v;

    }

}