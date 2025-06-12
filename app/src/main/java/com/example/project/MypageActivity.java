package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MypageActivity extends AppCompatActivity {

    TextView tvName, tvMajorStudentId, tvEmail;
    Button btnUnionNotices, btnUnionQna, btnUnionResources, btnApplyClub;
    Button btnSavedForms, btnManageLeaders, btnMyClub, btnWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        tvName = findViewById(R.id.tvName);
        tvMajorStudentId = findViewById(R.id.tvMajorStudentId);
        tvEmail = findViewById(R.id.tvEmail);

        btnUnionNotices = findViewById(R.id.btnUnionNotices);
        btnUnionQna = findViewById(R.id.btnUnionQna);
        btnUnionResources = findViewById(R.id.btnUnionResources);
        btnApplyClub = findViewById(R.id.btnApplyClub);
        btnSavedForms = findViewById(R.id.btnSavedForms);
        btnManageLeaders = findViewById(R.id.btnManageLeaders);
        btnMyClub = findViewById(R.id.btnMyClub);
        btnWithdraw = findViewById(R.id.btnWithdraw);

        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String name = pref.getString("name", "이름 없음");
        String studentId = pref.getString("studentId", "학번 없음");
        String department = pref.getString("department", "학과 없음");
        String email = pref.getString("email", "이메일 없음");
        String role = pref.getString("role", "지원자");

        tvName.setText(name + "  @" + studentId);
        tvMajorStudentId.setText(department + " / " + studentId);
        tvEmail.setText(email);

        if (role.equals("관리자")) {
            btnManageLeaders.setVisibility(View.VISIBLE);
        }

        btnUnionNotices.setOnClickListener(v -> {
            startActivity(new Intent(this, NoticeListActivity.class));
        });

        btnUnionQna.setOnClickListener(v -> {
            startActivity(new Intent(this, QnaListActivity.class));
        });

        btnUnionResources.setOnClickListener(v -> {
            startActivity(new Intent(this, UploadedFilesActivity.class));
        });

        btnApplyClub.setOnClickListener(v -> {
            startActivity(new Intent(this, ApplySelectActivity.class));
        });

        btnSavedForms.setOnClickListener(v -> {
            startActivity(new Intent(this, CheckStatusActivity.class));
        });

        btnMyClub.setOnClickListener(v -> {
            // 미구현
        });

        btnWithdraw.setOnClickListener(v -> {
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        btnManageLeaders.setOnClickListener(v -> {
            startActivity(new Intent(this, LeaderManageActivity.class));
        });
    }
}
