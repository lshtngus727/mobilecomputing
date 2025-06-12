package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ApplySelectActivity extends AppCompatActivity {

    private Spinner spinnerClubSelect;
    private TextView tvDeadline;
    private Button btnWriteApplication, btnGoToStatus, btnCheckClubs;

    private final Map<String, String> deadlineMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_select);

        // 뷰 연결
        spinnerClubSelect = findViewById(R.id.spinnerClubSelect);
        tvDeadline = findViewById(R.id.tvDeadline);
        btnWriteApplication = findViewById(R.id.btnWriteApplication);
        btnGoToStatus = findViewById(R.id.btnGoToStatus);
        btnCheckClubs = findViewById(R.id.btnCheckClubs);

        // ✅ 최종 동아리 리스트
        String[] clubs = {
                "A-SOUND", "B.P.M", "TENZ", "빌보드", "백색소음", "블랙스톤", "위키드", "옥타브", "크레센도", "현암극회", "4M",
                "매치포인트", "청마루", "CAPS", "시너지", "하트비트", "싸이클론", "스파이커스", "오르락", "슬램", "마스터즈", "피노키오", "푸쉬오프", "HYBE", "공굴러가유",
                "CCC", "DFC", "IVF", "ESF", "JDM", "증산도",
                "콜드브루", "찰나", "Moment", "RGB", "MasterDrone", "다와", "H-컬쳐랜드", "Mode", "모해", "더 필름", "원리원칙", "묘미", "Team Miracle", "스탬프", "사부작", "OPEN:BOOK",
                "상상네이버스", "로타랙트", "악어스카우트", "한울회", "SMU", "RCY"
        };

        // ✅ 마감일 예시
        deadlineMap.put("B.P.M", "상시모집");
        deadlineMap.put("TENZ", "2025-06-28까지");

        // ✅ Spinner 세팅
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.asList(clubs));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClubSelect.setAdapter(adapter);

        spinnerClubSelect.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                String selectedClub = parent.getItemAtPosition(position).toString();
                String deadline = deadlineMap.getOrDefault(selectedClub, "등록된 정보가 없습니다.");
                tvDeadline.setText("지원 마감일: " + deadline);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                tvDeadline.setText("지원 마감일: 선택된 동아리에 따라 표시됩니다.");
            }
        });

        // ✅ 지원서 작성 화면 이동
        btnWriteApplication.setOnClickListener(v -> {
            String selectedClub = spinnerClubSelect.getSelectedItem().toString();
            Intent intent = new Intent(this, ApplyFormActivity.class);
            intent.putExtra("selectedClub", selectedClub); // 선택된 동아리 전달 (필요 시)
            startActivity(intent);
        });

        btnGoToStatus.setOnClickListener(v -> {
            String selectedClub = spinnerClubSelect.getSelectedItem().toString();
            Intent intent = new Intent(this, CheckStatusActivity.class);
            intent.putExtra("selectedClub", selectedClub);  // 넘김
            startActivity(intent);
        });

        // ✅ 동아리 리스트 보기 이동
        btnCheckClubs.setOnClickListener(v -> {
            startActivity(new Intent(this, ClubListActivity.class));
        });
    }
}
