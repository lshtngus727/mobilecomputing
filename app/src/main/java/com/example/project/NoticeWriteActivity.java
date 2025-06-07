package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NoticeWriteActivity extends AppCompatActivity {

    EditText etTitle, etContent;
    Button btnSubmit, btnAttach;
    CheckBox checkFixed;

    private Uri selectedFileUri = null; // 첨부파일 경로 저장용

    private static final int FILE_PICK_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_write);

        etTitle = findViewById(R.id.etNoticeTitle);
        etContent = findViewById(R.id.etNoticeContent);
        btnSubmit = findViewById(R.id.btnSubmitNotice);
        btnAttach = findViewById(R.id.btnAttachFile);
        checkFixed = findViewById(R.id.checkFixed);

        // 첨부파일 버튼 클릭 시 파일 선택기 열기
        btnAttach.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*"); // 모든 타입 가능
            startActivityForResult(intent, FILE_PICK_REQUEST);
        });

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            boolean isFixed = checkFixed.isChecked();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "제목과 내용을 모두 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", title);
            resultIntent.putExtra("content", content);
            resultIntent.putExtra("isFixed", isFixed);
            resultIntent.putExtra("hasAttachment", selectedFileUri != null);
            resultIntent.putExtra("date", "2025-06-07");

            // 🔧 선택한 파일 경로도 넘길 수 있음 (지금은 생략)
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    // 파일 선택 결과 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_PICK_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedFileUri = data.getData();
            String fileName = selectedFileUri.getLastPathSegment();  // 간단한 이름 추출
            Toast.makeText(this, "선택된 파일: " + fileName, Toast.LENGTH_SHORT).show();
        }
    }
}
