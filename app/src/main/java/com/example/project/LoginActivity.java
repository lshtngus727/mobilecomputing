package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginId, etLoginPassword;
    Button btnLogin;
    TextView tvGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginId = findViewById(R.id.etLoginId);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToRegister = findViewById(R.id.tvGoToRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputId = etLoginId.getText().toString().trim();
                String inputPw = etLoginPassword.getText().toString().trim();

                SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
                String savedId = pref.getString("email", "");
                String savedPw = pref.getString("password", "");
                String savedRole = pref.getString("role", "");

                if (inputId.equals(savedId) && inputPw.equals(savedPw)) {
                    pref.edit().putBoolean("isLoggedIn", true).apply();

                    Toast.makeText(LoginActivity.this, savedRole + "님 로그인 성공", Toast.LENGTH_SHORT).show();

                    // 메인 화면으로 이동
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패: 아이디 또는 비밀번호 불일치", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
