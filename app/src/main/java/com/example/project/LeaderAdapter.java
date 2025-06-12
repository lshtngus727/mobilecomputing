package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.LeaderViewHolder> {

    private List<Leader> leaderList;

    public LeaderAdapter(List<Leader> leaderList) {
        this.leaderList = leaderList;
    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leader_input, parent, false);
        return new LeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        Leader leader = leaderList.get(position);
        holder.etName.setText(leader.getName());
        holder.etRole.setText(leader.getRole());

        holder.btnDelete.setOnClickListener(v -> {
            leaderList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, leaderList.size());
        });
    }

    @Override
    public int getItemCount() {
        return leaderList.size();
    }

    public static class LeaderViewHolder extends RecyclerView.ViewHolder {
        EditText etName, etRole;
        Button btnDelete;

        public LeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            etName = itemView.findViewById(R.id.etLeaderName);
            etRole = itemView.findViewById(R.id.etLeaderRole);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
