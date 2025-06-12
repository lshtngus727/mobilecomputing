package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private List<Schedule> scheduleList;

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.tvScheduleTitle.setText(schedule.getTitle());

        String start = formatDate(schedule.getStartDate());
        String end = formatDate(schedule.getEndDate());

        if (start != null && end != null) {
            if (start.equals(end)) {
                holder.tvScheduleDate.setText(start);  // 하루짜리 일정
            } else {
                holder.tvScheduleDate.setText(start + " ~ " + end);  // 여러날짜 일정
            }
        } else if (start != null) {
            holder.tvScheduleDate.setText(start);  // end가 비어있는 경우
        } else {
            holder.tvScheduleDate.setText("날짜 없음");  // 둘 다 null인 경우
        }
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView tvScheduleTitle, tvScheduleDate;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScheduleTitle = itemView.findViewById(R.id.tvScheduleTitle);
            tvScheduleDate = itemView.findViewById(R.id.tvScheduleDate);
        }
    }

    private String formatDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) return null;

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            Date date = inputFormat.parse(dateString);

            SimpleDateFormat outputFormat = new SimpleDateFormat("M월 d일", Locale.KOREA);
            return outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;  // 실패 시 원본 반환
        }
    }
}
