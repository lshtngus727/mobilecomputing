package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class QnaWriteActivity extends AppCompatActivity {

    EditText etTitle, etContent, etWriter, etPassword;
    Spinner spinnerCategory, spinnerVisibility;
    Button btnSubmitQna, btnAttachFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_write);

        etTitle = findViewById(R.id.etQnaTitle);
        etContent = findViewById(R.id.etQnaContent);
        etWriter = findViewById(R.id.etWriter);
        etPassword = findViewById(R.id.etPassword);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerVisibility = findViewById(R.id.spinnerVisibility);
        btnSubmitQna = findViewById(R.id.btnSubmitQna);
        btnAttachFile = findViewById(R.id.btnAttachFile);

        // 스피너 데이터 설정
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.qna_categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> visibilityAdapter = ArrayAdapter.createFromResource(this,
                R.array.qna_visibility, android.R.layout.simple_spinner_item);
        visibilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVisibility.setAdapter(visibilityAdapter);

        // 기본 사용자 정보 (나중에 로그인 정보로 자동 입력 예정)
        etWriter.setText("20251234");

        btnSubmitQna.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            String writer = etWriter.getText().toString();
            String category = spinnerCategory.getSelectedItem().toString();
            String visibility = spinnerVisibility.getSelectedItem().toString();
            String password = etPassword.getText().toString();
            boolean isPrivate = visibility.equals("비공개");

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "제목과 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 🔧 임시 저장 로직
            Toast.makeText(this, "질문 등록 완료 (임시)", Toast.LENGTH_SHORT).show();
            finish(); // 돌아가기
        });

        btnAttachFile.setOnClickListener(v -> {
            Toast.makeText(this, "첨부파일 기능은 준비 중입니다.", Toast.LENGTH_SHORT).show();
        });
    }
}
