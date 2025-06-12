package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class QnaReplyActivity extends AppCompatActivity {

    TextView tvQuestionTitle, tvQuestionContent;
    Button btnDownloadAttachment, btnSubmitReply;
    EditText etReplyContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_reply);

        tvQuestionTitle = findViewById(R.id.tvQuestionTitle);
        tvQuestionContent = findViewById(R.id.tvQuestionContent);
        btnDownloadAttachment = findViewById(R.id.btnDownloadAttachment);
        btnSubmitReply = findViewById(R.id.btnSubmitReply);
        etReplyContent = findViewById(R.id.etReplyContent);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        boolean hasAttachment = intent.getBooleanExtra("hasAttachment", false);

        tvQuestionTitle.setText(title);
        tvQuestionContent.setText(content);

        if (!hasAttachment) {
            btnDownloadAttachment.setVisibility(View.GONE);
        }

        // 역할 판별
        SharedPreferences pref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String role = pref.getString("role", "비회원");


        btnDownloadAttachment.setOnClickListener(v -> {
            Toast.makeText(this, "다운로드 기능은 준비 중입니다.", Toast.LENGTH_SHORT).show();
        });

        btnSubmitReply.setOnClickListener(v -> {
            String reply = etReplyContent.getText().toString();
            if (reply.isEmpty()) {
                Toast.makeText(this, "답변을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "답변이 등록되었습니다. (임시)", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
