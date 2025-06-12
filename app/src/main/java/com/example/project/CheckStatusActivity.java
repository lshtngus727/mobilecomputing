package com.example.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckStatusActivity extends AppCompatActivity {

    private Spinner spinnerCheckClub;
    private TextView tvStatus, tvSummary;

    private final Map<String, String> statusMap = new HashMap<>();
    private final Map<String, String> summaryMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_status);

        spinnerCheckClub = findViewById(R.id.spinnerCheckClub);
        tvStatus = findViewById(R.id.tvStatus);
        tvSummary = findViewById(R.id.tvSummary);

        // 예시 동아리 리스트 (실제론 DB에서 불러오는 게 좋음)
        String[] clubs = {"B.P.M", "TENZ"};

        // 임시 상태 데이터
        statusMap.put("B.P.M", "검토중");
        statusMap.put("TENZ", "합격");

        summaryMap.put("B.P.M", "지원 동기: 음악에 대한 열정\n각오: 누구보다 열심히 활동하겠습니다.");
        summaryMap.put("TENZ", "지원 동기: 춤을 좋아합니다.\n각오: 모두와 함께 성장하고 싶습니다.");

        // Spinner 연결
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.asList(clubs));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCheckClub.setAdapter(adapter);

        // 전달받은 동아리 이름 (ApplySelectActivity에서 putExtra로 넘겼을 경우)
        Intent intent = getIntent();
        String selectedClubFromIntent = intent.getStringExtra("selectedClub");

        // Spinner 자동 선택 설정
        if (selectedClubFromIntent != null) {
            int index = Arrays.asList(clubs).indexOf(selectedClubFromIntent);
            if (index >= 0) {
                spinnerCheckClub.setSelection(index);
            }
        }

        // Spinner 이벤트
        spinnerCheckClub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClub = parent.getItemAtPosition(position).toString();

                if (!statusMap.containsKey(selectedClub)) {
                    tvStatus.setText("상태: 아직 지원하지 않았습니다.");
                    tvStatus.setTextColor(Color.GRAY);
                    tvSummary.setText("지원 이력이 없습니다.");
                    return;
                }

                // 상태와 요약 불러오기
                String status = statusMap.getOrDefault(selectedClub, "검토중");
                String summary = summaryMap.getOrDefault(selectedClub, "지원 동기: \n각오: ");

                tvStatus.setText("상태: " + status);
                tvSummary.setText(summary);

                // 상태별 색상 지정
                switch (status) {
                    case "합격":
                        tvStatus.setTextColor(Color.parseColor("#388E3C")); // 초록
                        break;
                    case "불합격":
                        tvStatus.setTextColor(Color.parseColor("#D32F2F")); // 빨강
                        break;
                    case "검토중":
                    default:
                        tvStatus.setTextColor(Color.parseColor("#1976D2")); // 파랑
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvStatus.setText("상태: 선택된 동아리에 따라 표시됩니다.");
                tvSummary.setText("");
                tvStatus.setTextColor(Color.DKGRAY);
            }
        });
    }
}
