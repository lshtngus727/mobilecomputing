package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddFileActivity extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;

    private EditText etFileTitle;
    private Spinner spinnerCategory, spinnerClub;
    private TextView tvSelectedFile;
    private CheckBox checkNoticeAlso;
    private Button btnSelectFile, btnUploadFile;

    private Uri selectedFileUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        // View 연결
        etFileTitle = findViewById(R.id.etFileTitle);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerClub = findViewById(R.id.spinnerClub);
        tvSelectedFile = findViewById(R.id.tvSelectedFile);
        checkNoticeAlso = findViewById(R.id.checkNoticeAlso);
        btnSelectFile = findViewById(R.id.btnSelectFile);
        btnUploadFile = findViewById(R.id.btnUploadFile);

        // Spinner 더미 데이터 (원래는 서버/DB에서 불러와야 함)
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"신입부원", "정기서류", "기타"});
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        ArrayAdapter<String> clubAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"TENZ", "A-SOUND", "B.P.M", "위키드", "빌보드", "블랙스톤", "백색소음", "옥타브", "현암극회", "옥타브", "현암극회", "크레센도", "4M", "상상네이버스", "로타랙트", "악어스카우트", "한울회", "SMU", "RCY", "매치포인트"});
        clubAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClub.setAdapter(clubAdapter);

        // 파일 선택 버튼 클릭
        btnSelectFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            startActivityForResult(intent, PICK_FILE_REQUEST);
        });

        // 등록 버튼 클릭
        btnUploadFile.setOnClickListener(v -> {
            String title = etFileTitle.getText().toString().trim();
            String category = spinnerCategory.getSelectedItem().toString();
            String club = spinnerClub.getSelectedItem().toString();
            boolean alsoNotice = checkNoticeAlso.isChecked();

            if (title.isEmpty() || selectedFileUri == null) {
                Toast.makeText(this, "제목과 파일을 모두 입력하세요", Toast.LENGTH_SHORT).show();
                return;
            }

            // 🔜 실제 파일 업로드 또는 DB 저장 로직은 여기에 들어감
            Toast.makeText(this, "임시로 등록 완료 (파일: " + selectedFileUri.getLastPathSegment() + ")", Toast.LENGTH_LONG).show();

            // 🔜 만약 checkNoticeAlso가 true면 공지사항에도 등록되도록 로직 추가 예정

            finish(); // 화면 닫기
        });
    }

    // 파일 선택 후 결과 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedFileUri = data.getData();
            String filename = selectedFileUri.getLastPathSegment();
            tvSelectedFile.setText(filename != null ? filename : "선택된 파일");
        }
    }
}
