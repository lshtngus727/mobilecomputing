package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

    private List<Day> dayList;
    private Context context;

    public DayAdapter(List<Day> dayList, Context context) {
        this.dayList = dayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_day_cell, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Day day = dayList.get(position);
        if (day.getDayNumber() > 0) {
            holder.tvDay.setText(String.valueOf(day.getDayNumber()));
            holder.tvDay.setAlpha(day.isCurrentMonth() ? 1.0f : 0.3f);
        } else {
            holder.tvDay.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
        }
    }
}
