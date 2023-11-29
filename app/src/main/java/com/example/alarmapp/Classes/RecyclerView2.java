package com.example.alarmapp.Classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import com.example.alarmapp.Activities.SetAlramActivity;
import com.example.alarmapp.Activities.TempActivity;
import com.example.alarmapp.BlankFragment;
import com.example.alarmapp.MainActivity;
import com.example.alarmapp.R;

import java.util.LinkedList;
import java.util.Set;

public class RecyclerView2 extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerView2.viewHolder> {


    LinkedList<ModelClass> list;
    Context context;

    public RecyclerView2(LinkedList<ModelClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
                holder.timeText.setText(list.get(position).getHour()+":"+list.get(position).getMin()+":"
                        +list.get(position).getAm());

        holder.dateText.setText(list.get(position).getDay()+"/"+list.get(position).getMonth()+"/"
                +list.get(position).getYear());
        holder.cardView.setOnClickListener(e->{
            Intent intent = new Intent(context, SetAlramActivity.class);
            intent.putExtra("hour2", list.get(position).getHour());
            intent.putExtra("min2", list.get(position).getMin());
            intent.putExtra("am2", list.get(position).getAm());
            intent.putExtra("year2", list.get(position).getYear());
            intent.putExtra("month2", list.get(position).getMonth());
            intent.putExtra("day2", list.get(position).getDay());
            intent.putExtra("update", true);
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
                        mySqlClass.deleteData(list.get(position).getHour(), list.get(position).getMin(),list.get(position).getAm(),list.get(position).getDay());
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });
                builder.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        TextView timeText, dateText;
        Switch aSwitch;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.timeText);
            dateText = itemView.findViewById(R.id.dateText);
            aSwitch = itemView.findViewById(R.id.switchRec);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
