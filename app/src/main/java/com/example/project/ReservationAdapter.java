package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private ArrayList<Reservation> list;

    public ReservationAdapter(ArrayList<Reservation> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlace, tvDate, tvTime, tvPurpose;
        Button btnApprove, btnReject;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPurpose = itemView.findViewById(R.id.tvPurpose);
            btnApprove = itemView.findViewById(R.id.btnApprove);
            btnReject = itemView.findViewById(R.id.btnReject);
        }

        public void bind(Reservation item) {
            tvPlace.setText(item.place);
            tvDate.setText(item.date);
            tvTime.setText(item.time);
            tvPurpose.setText(item.purpose);

            btnApprove.setOnClickListener(v -> {
                Toast.makeText(v.getContext(), "승인 완료", Toast.LENGTH_SHORT).show();
            });

            btnReject.setOnClickListener(v -> {
                Toast.makeText(v.getContext(), "반려 처리", Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
