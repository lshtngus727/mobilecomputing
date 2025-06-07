package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoticeDetailActivity extends AppCompatActivity {
    TextView tvTitle, tvDate, tvContent;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvDate = findViewById(R.id.tvDetailDate);
        tvContent = findViewById(R.id.tvDetailContent);
        btnDownload = findViewById(R.id.btnDownloadAttachment);

        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("title"));
        tvDate.setText(intent.getStringExtra("date"));
        tvContent.setText(intent.getStringExtra("content"));

        if (intent.getBooleanExtra("hasAttachment", false)) {
            btnDownload.setVisibility(View.VISIBLE);
            // 추가: 파일 다운로드 처리
        }
    }
}

