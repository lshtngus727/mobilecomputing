package com.example.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubmitReservationActivity extends AppCompatActivity {

    private EditText etQnaTitle, etWriter, etQnaContent, etPassword;
    private Spinner spinnerCategory, spinnerVisibility;
    private Button btnAttachFile, btnSubmitQna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_write);  // 그대로 사용

        // XML에 맞춰 위젯 연결
        etQnaTitle = findViewById(R.id.etQnaTitle);
        etWriter = findViewById(R.id.etWriter);
        etQnaContent = findViewById(R.id.etQnaContent);
        etPassword = findViewById(R.id.etPassword);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerVisibility = findViewById(R.id.spinnerVisibility);
        btnAttachFile = findViewById(R.id.btnAttachFile);
        btnSubmitQna = findViewById(R.id.btnSubmitQna);

        // 카테고리 예시 설정
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                new String[]{"지원", "예약", "기타"});
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // 공개 여부 예시 설정
        ArrayAdapter<String> visibilityAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                new String[]{"공개", "비공개"});
        visibilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVisibility.setAdapter(visibilityAdapter);

        // 작성자 자동 설정 (예: SharedPreferences에서 불러오기)
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String writer = prefs.getString("studentId", "20241234");
        etWriter.setText(writer);

        // 파일 첨부 (추후 구현 필요)
        btnAttachFile.setOnClickListener(v -> {
            Toast.makeText(this, "파일 첨부 기능은 아직 구현되지 않았습니다.", Toast.LENGTH_SHORT).show();
        });

        // 질문 등록 버튼
        btnSubmitQna.setOnClickListener(v -> {
            String title = etQnaTitle.getText().toString();
            String category = spinnerCategory.getSelectedItem().toString();
            String writerText = etWriter.getText().toString();
            String content = etQnaContent.getText().toString();
            String visibility = spinnerVisibility.getSelectedItem().toString();
            String password = etPassword.getText().toString();

            // 유효성 검사
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "제목과 내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (visibility.equals("비공개") && password.isEmpty()) {
                Toast.makeText(this, "비공개 글은 비밀번호를 입력해야 합니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 데이터 저장 (임시 Toast 처리)
            Toast.makeText(this, "질문이 등록되었습니다.", Toast.LENGTH_SHORT).show();

            // 실제로는 Firebase 또는 SQLite 저장 로직 필요
            finish();
        });
    }
}
