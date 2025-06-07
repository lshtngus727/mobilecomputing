package com.example.project;

import android.content.Intent;
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
        tvQuestionTitle.setText(intent.getStringExtra("title"));
        tvQuestionContent.setText(intent.getStringExtra("content"));
        boolean hasAttachment = intent.getBooleanExtra("hasAttachment", false);

        if (hasAttachment) {
            btnDownloadAttachment.setOnClickListener(v -> {
                Toast.makeText(this, "다운로드 기능 준비 중", Toast.LENGTH_SHORT).show();
            });
        } else {
            btnDownloadAttachment.setVisibility(View.GONE);
        }

        btnSubmitReply.setOnClickListener(v -> {
            String reply = etReplyContent.getText().toString();
            if (reply.isEmpty()) {
                Toast.makeText(this, "답변을 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }

            // 🔧 임시 저장 처리
            Toast.makeText(this, "답변 등록 완료 (임시)", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
