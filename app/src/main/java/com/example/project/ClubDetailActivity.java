package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClubDetailActivity extends AppCompatActivity {

    private TextView tvName, tvIntro, tvLeader, tvApplyInfo;
    private ImageView imgPhoto;
    private Button btnApply, btnNotice, btnSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_detail);

        // ✅ 뷰 연결은 onCreate 안에서 실행
        tvName = findViewById(R.id.tvClubName);
        tvIntro = findViewById(R.id.tvClubIntro);
        tvLeader = findViewById(R.id.tvLeaderInfo);
        tvApplyInfo = findViewById(R.id.tvApplyInfo);
        imgPhoto = findViewById(R.id.imgPhoto1);
        btnApply = findViewById(R.id.btnApply);
        btnNotice = findViewById(R.id.btnNotice);
        btnSchedule = findViewById(R.id.btnSchedule);

        // 전달받은 동아리 이름
        String clubName = getIntent().getStringExtra("clubName");
        tvName.setText(clubName);

        // B.P.M 정보 세팅
        if ("B.P.M".equals(clubName)) {
            tvIntro.setText("BPM은 Bouncing People for Music의 약자로 힙합, 일렉트로닉, 올카인드 디제잉을 기반으로 한 파티플랜, 친목 등을 주로 하는 한밭대학교 디제잉 크루입니다.");
            tvLeader.setText("이동근 (회장) / 010-5255-3176");
            tvApplyInfo.setText("회비: 2만원(학기당)\n지원자격: 한밭대학교 학생\n인스타 참고\n상시모집");
            imgPhoto.setImageResource(R.drawable.club);
        } else {
            tvIntro.setText("소개 정보 없음");
            tvLeader.setText("임원 정보 없음");
            tvApplyInfo.setText("지원 정보 없음");
            imgPhoto.setImageResource(R.drawable.logo_always); // 기본 이미지
        }

        // 버튼 이벤트
        btnApply.setOnClickListener(v -> {
            Intent intent = new Intent(ClubDetailActivity.this, ApplySelectActivity.class);
            startActivity(intent);
        });

        btnNotice.setOnClickListener(v -> {
            Toast.makeText(this, "공지사항 페이지로 이동 예정", Toast.LENGTH_SHORT).show();
        });

        btnSchedule.setOnClickListener(v -> {
            Toast.makeText(this, "동아리 일정 기능은 준비 중입니다.", Toast.LENGTH_SHORT).show();
        });
    }
}
