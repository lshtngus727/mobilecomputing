package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UploadedFilesActivity extends AppCompatActivity {

    private Spinner spinnerFilterCategory;
    private RecyclerView recyclerViewFiles;
    private UploadFileAdapter uploadFileAdapter;

    private List<File> fileList;
    private List<File> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded_files);

        spinnerFilterCategory = findViewById(R.id.spinnerFilterCategory);
        recyclerViewFiles = findViewById(R.id.recyclerViewFiles);
        Button btnGoToUpload = findViewById(R.id.btnGoToUpload);

        // ✅ SharedPreferences("UserInfo")에서 role 가져오기
        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String role = pref.getString("role", "비회원");

        // ✅ 관리자 또는 동아리장만 버튼 보이게
        if (role.equals("관리자") || role.equals("동아리장")) {
            btnGoToUpload.setVisibility(View.VISIBLE);
            btnGoToUpload.setOnClickListener(v -> {
                Intent intent = new Intent(this, AddFileActivity.class);
                startActivity(intent);
            });
        } else {
            btnGoToUpload.setVisibility(View.GONE);
        }

        // 🔽 예시 데이터
        fileList = new ArrayList<>();
        filteredList = new ArrayList<>();
        fileList.add(new File("5월 회비 사용내역", "TENZ", "2025-06-01", "정기서류", "url1"));
        fileList.add(new File("신입부원 서류", "상상네이버스", "2025-06-02", "신입부원", "url2"));

        uploadFileAdapter = new UploadFileAdapter(fileList, this);
        recyclerViewFiles.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFiles.setAdapter(uploadFileAdapter);

        // 🔽 스피너 설정
        String[] categories = {"전체", "신입부원", "정기서류", "기타"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilterCategory.setAdapter(spinnerAdapter);

        spinnerFilterCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterListByCategory(categories[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void filterListByCategory(String category) {
        filteredList.clear();
        if (category.equals("전체")) {
            filteredList.addAll(fileList);
        } else {
            for (File file : fileList) {
                if (file.getCategory().equals(category)) {
                    filteredList.add(file);
                }
            }
        }
        uploadFileAdapter.updateList(filteredList);
    }
}
