package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnSignup;
    Button btnIntro, btnClubList, btnApply, btnResources, btnQna, btnNoticePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 로그인 관련 버튼
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        // 메뉴 버튼
        btnIntro = findViewById(R.id.btnIntro);
        btnClubList = findViewById(R.id.btnClubList);
        btnApply = findViewById(R.id.btnApply);
        btnResources = findViewById(R.id.btnResources);
        btnQna = findViewById(R.id.btnQna); // 👈 마이페이지
        btnNoticePopup = findViewById(R.id.btnNoticePopup);

        // 로그인 상태 및 권한 확인
        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        boolean isLoggedIn = pref.getBoolean("isLoggedIn", false);
        String role = pref.getString("role", "비회원");

        // 로그인 상태 아닐 때: 로그인/회원가입만 보이고 나머지는 숨김
        if (!isLoggedIn) {
            btnIntro.setVisibility(View.GONE);
            btnClubList.setVisibility(View.GONE);
            btnApply.setVisibility(View.GONE);
            btnResources.setVisibility(View.GONE);
            btnQna.setVisibility(View.GONE); // 👈 마이페이지 숨김
        } else {
            // 로그인 상태일 경우 로그인/회원가입 버튼 숨김
            btnLogin.setVisibility(View.GONE);
            btnSignup.setVisibility(View.GONE);

            // 역할에 따라 제한
            if (role.equals("지원자")) {
                btnIntro.setVisibility(View.GONE);
                btnClubList.setVisibility(View.GONE);
            } else if (role.equals("동아리장")) {
                btnApply.setVisibility(View.GONE);
            }
            // 관리자는 전부 보이게
        }

        // 버튼 이벤트
        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        btnSignup.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        // 총동아리연합회 소개
        btnIntro.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, IntroActivity.class));
        });

// 동아리 보기
        btnClubList.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ClubListActivity.class));
        });

// 지원하기
        btnApply.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ApplySelectActivity.class));
        });

// 자료실
        btnResources.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FileListActivity.class));
        });

// 마이페이지
        btnQna.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MypageActivity.class));
        });

// 공지사항 바로가기
        btnNoticePopup.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NoticeListActivity.class));
        });

    }
}
