package com.example.cinemagic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingCustomAdapter extends RecyclerView.Adapter<BookingCustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList booking_id,booking_name,booking_date,booking_theatre,booking_showtime,booking_ticketcount;

    BookingCustomAdapter(Activity activity, Context context, ArrayList booking_id, ArrayList booking_name, ArrayList booking_date, ArrayList booking_theatre, ArrayList booking_showtime, ArrayList booking_ticketcount){
        this.activity = activity;
        this.context = context;
        this.booking_id = booking_id;
        this.booking_name = booking_name;
        this.booking_date = booking_date;
        this.booking_theatre = booking_theatre;
        this.booking_showtime = booking_showtime;
        this.booking_ticketcount = booking_ticketcount;
    }

    @NonNull
    @Override
    public BookingCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingCustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.booking_id_txt.setText(String.valueOf(booking_id.get(position)));
        holder.booking_name_txt.setText(String.valueOf(booking_name.get(position)));
        holder.booking_date_txt.setText(String.valueOf(booking_date.get(position)));
        holder.booking_theatre_txt.setText(String.valueOf(booking_theatre.get(position)));
        holder.booking_showtime_txt.setText(String.valueOf(booking_showtime.get(position)));
        holder.booking_ticketcount_txt.setText(String.valueOf(booking_ticketcount.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateBooking.class);
                intent.putExtra("id", String.valueOf(booking_id.get(position)));
                intent.putExtra("name",String.valueOf(booking_name.get(position)));
                intent.putExtra("date",String.valueOf(booking_date.get(position)));
                intent.putExtra("theatre",String.valueOf(booking_theatre.get(position)));
                intent.putExtra("showtime",String.valueOf(booking_showtime.get(position)));
                intent.putExtra("ticketcount",String.valueOf(booking_ticketcount.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return booking_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView booking_id_txt,booking_name_txt,booking_date_txt,booking_theatre_txt,booking_showtime_txt,booking_ticketcount_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            booking_id_txt = itemView.findViewById(R.id.booking_id_txt);
            booking_name_txt = itemView.findViewById(R.id.booking_name_txt);
            booking_date_txt = itemView.findViewById(R.id.booking_date_txt);
            booking_theatre_txt = itemView.findViewById(R.id.booking_theatre_txt);
            booking_showtime_txt = itemView.findViewById(R.id.booking_showtime_txt);
            booking_ticketcount_txt = itemView.findViewById(R.id.booking_ticketcount_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
