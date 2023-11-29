package com.example.alarmapp.Classes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import com.example.alarmapp.Activities.SetAlramActivity;
import com.example.alarmapp.Activities.TempActivity;
import com.example.alarmapp.BlankFragment;
import com.example.alarmapp.MainActivity;
import com.example.alarmapp.R;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Set;
import java.util.TimeZone;

public class RecyclerView2 extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerView2.viewHolder> {


    LinkedList<ModelClass> list;
    Context context;
    MySqlClass obj;
    public RecyclerView2(LinkedList<ModelClass> list, Context context) {
        this.list = list;
        this.context = context;
        obj = new MySqlClass(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);

        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {

        ModelClass modelClass = list.get(position);
        int hour = modelClass.getHour();
        int min = modelClass.getMin();
        int day = modelClass.getDay();
        int month = modelClass.getMonth();
        int year = modelClass.getMonth();

        if (hour<=9) {
            if(min<=9)
            holder.timeText.setText("0" + hour + ":0" + min + ":" + list.get(position).getAm());
            else
                holder.timeText.setText("0" + hour + ":" + min + ":" + list.get(position).getAm());
        }
         else
            holder.timeText.setText(hour+":"+min+":" +list.get(position).getAm());
        if (hour>9 && min<=9) {
                holder.timeText.setText( hour + ":0" + min + ":" + list.get(position).getAm());
        }
         if (day<=9 ) {
             if (month<=9)
             holder.dateText.setText("0" + day + "/0" + month + "/" + list.get(position).getYear());
             else
                 holder.dateText.setText("0"+day+"/"+month+"/" +list.get(position).getYear());

         }
         else
             holder.dateText.setText(day+"/"+month+"/" +list.get(position).getYear());

         if (list.get(position).isOn())
             holder.aSwitch.setOn(true);
         else
             holder.aSwitch.setOn(false);

        holder.cardView.setOnClickListener(e->{
            Intent intent = new Intent(context, SetAlramActivity.class);
//            intent.putExtra("hour2", list.get(position).getHour());
//            intent.putExtra("min2", list.get(position).getMin());
//            intent.putExtra("am2", list.get(position).getAm());
//            intent.putExtra("year2", list.get(position).getYear());
//            intent.putExtra("month2", list.get(position).getMonth());
//            intent.putExtra("day2", list.get(position).getDay());
            intent.putExtra("update", true);
            intent.putExtra("millis", list.get(position).getTime());
            context.startActivity(intent);

            // Create the AlertDialog object and return it


        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(4000);
                AlertDialog builder = new AlertDialog.Builder(context).create();
                builder.setTitle("Delete Dialog");
                builder.setMessage("Are you sure to delete it ?");
                builder.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.dismiss();
                    }
                });
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MySqlClass mySqlClass = new MySqlClass(context);
                        mySqlClass.deleteData(list.get(position).getTime());
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });
                builder.show();

                return true;
            }
        });

        holder.aSwitch.setOnClickListener(e->{

            if (list.get(position).isOn()) {
                list.get(position).setOn(false);
                obj.updateSwitch(list.get(position).getTime(), false);
                Toast.makeText(context, "is on = success "+list.get(position).getTime(), Toast.LENGTH_SHORT).show();

            }
            else {
                list.get(position).setOn(true);
                obj.updateSwitch(list.get(position).getTime(), true);

            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        TextView timeText, dateText;
        LabeledSwitch aSwitch;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.timeText);
            dateText = itemView.findViewById(R.id.dateText);
            aSwitch = itemView.findViewById(R.id.switchRec);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getFormattedTime(long messageTime) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(messageTime), TimeZone.getDefault().toZoneId());

        // Create a DateTimeFormatter object to format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Format the date and print the result
        String formattedTime = dateTime.format(formatter);
        return formattedTime;
    }
}
