package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
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
        fileList.add(new FileItem("2025년도 예산안", "2025-01-03"));
        fileList.add(new FileItem("2025년도 방침", "2025-03-01"));

        adapter = new FileAdapter(fileList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnUploadFile.setOnClickListener(v -> {
            Intent intent = new Intent(this, FileUploadActivity.class);
            startActivity(intent);
        });

        // 사용자 권한에 따라 버튼 표시
        if ("관리자".equals(getCurrentUserRole())) {
            btnUploadFile.setVisibility(View.VISIBLE);
        } else {
            btnUploadFile.setVisibility(View.GONE);
        }
    }

    // ❗ 반드시 클래스 바깥에서 정의해야 함
    private String getCurrentUserRole() {
        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        return pref.getString("role", "비회원");
    }
}
