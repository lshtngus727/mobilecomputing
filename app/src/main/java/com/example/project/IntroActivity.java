package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    Button btnNotice, btnCalendar, btnFiles, btnUpload, btnQnA, btnReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_union_intro);

        // 버튼 연결
        btnNotice = findViewById(R.id.btnNotice);
        btnCalendar = findViewById(R.id.btnCalendar);
        btnFiles = findViewById(R.id.btnFiles);
        btnUpload = findViewById(R.id.btnUpload);
        btnQnA = findViewById(R.id.btnQnA);
        btnReservation = findViewById(R.id.btnReservation);

        // 버튼 클릭 시 이동
        btnNotice.setOnClickListener(v -> startActivity(new Intent(this, NoticeListActivity.class)));
        btnCalendar.setOnClickListener(v -> startActivity(new Intent(this, CalendarActivity.class)));
        btnFiles.setOnClickListener(v -> startActivity(new Intent(this, FileListActivity.class)));
        btnUpload.setOnClickListener(v -> startActivity(new Intent(this, AddFileActivity.class)));
        btnQnA.setOnClickListener(v -> startActivity(new Intent(this, QnaListActivity.class)));
        btnReservation.setOnClickListener(v -> startActivity(new Intent(this, ReservationActivity.class)));
    }
}
