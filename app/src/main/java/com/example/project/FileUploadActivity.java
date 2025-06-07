package com.example.project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FileUploadActivity extends AppCompatActivity {

    private Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        btnUpload = findViewById(R.id.btnSelectFile);

        btnUpload.setOnClickListener(v -> {
            Toast.makeText(this, "파일 업로드 기능은 추후 구현 예정입니다.", Toast.LENGTH_SHORT).show();
            finish(); // 등록 후 돌아가기
        });
    }
}
