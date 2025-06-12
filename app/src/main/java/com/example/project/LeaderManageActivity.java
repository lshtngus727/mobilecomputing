package com.example.project;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeaderManageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LeaderAdapter adapter;
    private List<Leader> leaderList = new ArrayList<>();
    private Button btnAddLeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_manage);

        recyclerView = findViewById(R.id.recyclerViewLeaders);
        btnAddLeader = findViewById(R.id.btnAddLeader);

        adapter = new LeaderAdapter(leaderList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAddLeader.setOnClickListener(v -> {
            leaderList.add(new Leader("", ""));
            adapter.notifyItemInserted(leaderList.size() - 1);
        });
    }
}
