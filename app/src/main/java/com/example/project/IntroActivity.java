package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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

        // SharedPreferences에서 role 가져오기
        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String role = pref.getString("role", "지원자"); // 기본은 '지원자'

        // 버튼 클릭 시 이동
        btnNotice.setOnClickListener(v -> startActivity(new Intent(this, NoticeListActivity.class)));
        btnCalendar.setOnClickListener(v -> startActivity(new Intent(this, CalendarActivity.class)));
        btnFiles.setOnClickListener(v -> startActivity(new Intent(this, FileListActivity.class)));
        btnQnA.setOnClickListener(v -> startActivity(new Intent(this, QnaListActivity.class)));

        // 역할에 따라 업로드 버튼 제어
        btnUpload.setOnClickListener(v -> {
            if (role.equals("관리자") || role.equals("동아리장")) {
                startActivity(new Intent(this, UploadedFilesActivity.class));
            } else {
                Toast.makeText(this, "관리자 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 역할에 따라 예약 버튼 제어
        btnReservation.setOnClickListener(v -> {
            switch (role) {
                case "관리자":
                    startActivity(new Intent(this, ReservationListActivity.class)); // 관리자: 예약 리스트로
                    break;
                case "동아리장":
                    startActivity(new Intent(this, SubmitReservationActivity.class)); // 동아리장: 예약 신청 화면으로
                    break;
                default:
                    Toast.makeText(this, "관리자 권한이 필요합니다.", Toast.LENGTH_SHORT).show(); // 그 외(지원자 등): 차단
                    break;
            }
        });
    }
}
