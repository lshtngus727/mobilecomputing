package com.example.project;

import android.content.SharedPreferences;
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

        SharedPreferences pref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String role = pref.getString("role", "비회원");


        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.qna_categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> visibilityAdapter = ArrayAdapter.createFromResource(this,
                R.array.qna_visibility, android.R.layout.simple_spinner_item);
        visibilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVisibility.setAdapter(visibilityAdapter);

        etWriter.setText("20251234"); // 임시 사용자 ID

        btnSubmitQna.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            String category = spinnerCategory.getSelectedItem().toString();
            String visibility = spinnerVisibility.getSelectedItem().toString();
            boolean isPrivate = visibility.equals("비공개");

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "제목과 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 🔧 저장 로직 (Firebase or SQLite 이후 구현 예정)
            Toast.makeText(this, "질문 등록 완료 (임시)", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnAttachFile.setOnClickListener(v -> {
            Toast.makeText(this, "첨부파일 기능은 준비 중입니다.", Toast.LENGTH_SHORT).show();
        });
    }
}
