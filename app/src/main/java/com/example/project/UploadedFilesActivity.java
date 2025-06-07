package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    private List<File> fileList;         // 전체 파일 리스트
    private List<File> filteredList;     // 필터링된 리스트 (선택된 카테고리 기준)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file); // 레이아웃 연결

        spinnerFilterCategory = findViewById(R.id.spinnerFilterCategory);
        recyclerViewFiles = findViewById(R.id.recyclerViewFiles);

        fileList = new ArrayList<>();
        filteredList = new ArrayList<>();

        fileList.add(new File("5월 회비 사용내역", "TENZ", "2025-06-01", "정기서류", "url1"));
        fileList.add(new File("신입부원 서류", "상상네이버스", "2025-06-02", "신입부원", "url2"));

        uploadFileAdapter = new UploadFileAdapter(fileList, this);
        recyclerViewFiles.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFiles.setAdapter(uploadFileAdapter);

        // 스피너 초기화
        String[] categories = {"전체", "신입부원", "정기서류", "기타"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilterCategory.setAdapter(spinnerAdapter);

        // 스피너 선택 시 필터링
        spinnerFilterCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];
                filterListByCategory(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    // 카테고리 필터링
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
