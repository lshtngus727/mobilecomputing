package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnSignup, btnLogout;
    Button btnIntro, btnClubList, btnApply, btnResources, btnQna, btnNoticePopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 로그인 관련 버튼
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        btnLogout = findViewById(R.id.btnLogout);

        // 메뉴 버튼
        btnIntro = findViewById(R.id.btnIntro);
        btnClubList = findViewById(R.id.btnClubList);
        btnApply = findViewById(R.id.btnApply);
        btnResources = findViewById(R.id.btnResources);
        btnQna = findViewById(R.id.btnQna); // 마이페이지
        btnNoticePopup = findViewById(R.id.btnNoticePopup);

        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        boolean isLoggedIn = pref.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // 로그인 안 했으면 로그인/회원가입만 보임
            btnLogin.setVisibility(View.VISIBLE);
            btnSignup.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);

            // 나머지 메뉴 전부 숨김
            btnIntro.setVisibility(View.GONE);
            btnClubList.setVisibility(View.GONE);
            btnApply.setVisibility(View.GONE);
            btnResources.setVisibility(View.GONE);
            btnQna.setVisibility(View.GONE);
            btnNoticePopup.setVisibility(View.GONE);
        } else {
            // 로그인했으면 모든 메뉴 다 보임 (역할 구분 X)
            btnLogin.setVisibility(View.GONE);
            btnSignup.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);

            btnIntro.setVisibility(View.VISIBLE);
            btnClubList.setVisibility(View.VISIBLE);
            btnApply.setVisibility(View.VISIBLE);
            btnResources.setVisibility(View.VISIBLE);
            btnQna.setVisibility(View.VISIBLE);
            btnNoticePopup.setVisibility(View.VISIBLE);
        }


        // ---------------- 버튼 이벤트 ---------------- //

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        btnSignup.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            // 로그아웃 처리
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Toast.makeText(this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();

            // 메인 다시 실행해서 화면 갱신
            finish();
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        });

        btnIntro.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, IntroActivity.class));
        });

        btnClubList.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ClubListActivity.class));
        });

        btnApply.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ApplySelectActivity.class));
        });

        btnResources.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FileListActivity.class));
        });

        btnQna.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MypageActivity.class));
        });

        btnNoticePopup.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NoticeListActivity.class));
        });
    }
}
