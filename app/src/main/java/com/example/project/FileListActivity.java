package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FileListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FileAdapter adapter;
    private ArrayList<FileItem> fileList;
    private Button btnUploadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        recyclerView = findViewById(R.id.recyclerViewFiles);
        btnUploadFile = findViewById(R.id.btnUploadFile);

        fileList = new ArrayList<>();
        // 임시 데이터
        fileList.add(new FileItem("2025년도 예산안", "2025-01-03"));
        fileList.add(new FileItem("2025년도 방침", "2025-03-01"));

        adapter = new FileAdapter(fileList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnUploadFile.setOnClickListener(v -> {
            Intent intent = new Intent(this, FileUploadActivity.class);
            startActivity(intent);
        });

        // 관리자 여부에 따라 visibility 조정 (예: 관리자인 경우만 보이게)
        if (isAdminUser()) {
            btnUploadFile.setVisibility(View.VISIBLE);
        }
    }

    private boolean isAdminUser() {
        // 테스트용 관리자 판단 로직 (true로 고정)
        return true;
    }
}

