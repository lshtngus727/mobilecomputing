package com.example.project;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ApplyFormActivity extends AppCompatActivity {

    EditText etName, etStudentId, etDepartment, etBirth, etPhone, etEmail, etMotivation, etPlan;
    Spinner spinnerClubs;
    Button btnUploadFile, btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_form);

        // 뷰 연결
        etName = findViewById(R.id.etName);
        etStudentId = findViewById(R.id.etStudentId);
        etDepartment = findViewById(R.id.etDepartment);
        etBirth = findViewById(R.id.etBirth);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etMotivation = findViewById(R.id.etMotivation);
        etPlan = findViewById(R.id.etPlan);
        spinnerClubs = findViewById(R.id.spinnerClubs);
        btnUploadFile = findViewById(R.id.btnUploadFile);
        btnSubmit = findViewById(R.id.btnSubmit);

        // 최종 동아리 리스트 (분과별 통합)
        HashMap<String, List<String>> clubMap = new HashMap<>();
        clubMap.put("공연분과", Arrays.asList("A-SOUND", "B.P.M", "TENZ", "빌보드", "백색소음", "블랙스톤", "위키드", "옥타브", "크레센도", "현암극회", "4M"));
        clubMap.put("체육분과", Arrays.asList("매치포인트", "청마루", "CAPS", "시너지", "하트비트", "싸이클론", "스파이커스", "오르락", "슬램", "마스터즈", "피노키오", "푸쉬오프", "HYBE", "공굴러가유"));
        clubMap.put("종교분과", Arrays.asList("CCC", "DFC", "IVF", "ESF", "JDM", "증산도"));
        clubMap.put("전시&교양분과", Arrays.asList("콜드브루", "찰나", "Moment", "RGB", "MasterDrone", "다와", "H-컬쳐랜드", "Mode", "모해", "더 필름", "원리원칙", "묘미", "Team Miracle", "스탬프", "사부작", "OPEN:BOOK"));
        clubMap.put("봉사분과", Arrays.asList("상상네이버스", "로타랙트", "악어스카우트", "한울회", "SMU", "RCY"));

        // 전체 리스트로 합치기
        List<String> allClubs = new ArrayList<>();
        for (List<String> clubs : clubMap.values()) {
            allClubs.addAll(clubs);
        }

        // Spinner 어댑터 설정
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allClubs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClubs.setAdapter(adapter);

        // 첨부파일 (미구현 안내)
        btnUploadFile.setOnClickListener(v -> {
            Toast.makeText(this, "파일 업로드 기능은 현재 준비 중입니다.", Toast.LENGTH_SHORT).show();
        });

        // 제출 버튼 클릭 처리
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String club = spinnerClubs.getSelectedItem().toString();

            if (name.isEmpty()) {
                Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this,
                    club + " 동아리에 지원서가 제출되었습니다.\n(※ 마이페이지 > 지원서 기록 기능은 준비 중입니다.)",
                    Toast.LENGTH_LONG).show();

            // 실제 DB 저장 또는 Firebase 연동 코드는 추후 구현
        });
    }
}
