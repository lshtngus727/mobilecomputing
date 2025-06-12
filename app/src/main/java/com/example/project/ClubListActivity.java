package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClubListActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ListView listView;
    private Map<String, List<String>> clubMap;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_list);

        tabLayout = findViewById(R.id.tabLayout);
        listView = findViewById(R.id.listViewClubs);

        // ✅ 분과별 동아리 이름 리스트 구성
        clubMap = new HashMap<>();
        clubMap.put("공연분과", Arrays.asList("A-SOUND", "B.P.M", "TENZ", "빌보드", "백색소음", "블랙스톤", "위키드", "옥타브", "크레센도", "현암극회", "4M"));
        clubMap.put("체육분과", Arrays.asList("매치포인트", "청마루", "CAPS", "시너지", "하트비트", "싸이클론", "스파이커스", "오르락", "슬램", "마스터즈", "피노키오", "푸쉬오프", "HYBE", "공굴러가유"));
        clubMap.put("종교분과", Arrays.asList("CCC", "DFC", "IVF", "ESF", "JDM", "증산도"));
        clubMap.put("전시&교양분과", Arrays.asList("콜드브루", "찰나", "Moment", "RGB", "MasterDrone", "다와", "H-컬쳐랜드", "Mode", "모해", "더 필름", "원리원칙", "묘미", "Team Miracle", "스탬프", "사부작", "OPEN:BOOK"));
        clubMap.put("봉사분과", Arrays.asList("상상네이버스", "로타랙트", "악어스카우트", "한울회", "SMU", "RCY"));

        // ✅ TabLayout에 분과 탭 추가
        for (String category : clubMap.keySet()) {
            tabLayout.addTab(tabLayout.newTab().setText(category));
        }

        // ✅ 탭 선택 이벤트
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                String selectedCategory = tab.getText().toString();
                showClubs(selectedCategory);
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        // ✅ ListView 클릭 → ClubDetailActivity 이동
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String clubName = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(ClubListActivity.this, ClubDetailActivity.class);
            intent.putExtra("clubName", clubName);
            startActivity(intent);
        });

        // ✅ 첫 번째 탭 자동 선택
        if (tabLayout.getTabCount() > 0)
            tabLayout.getTabAt(0).select();
    }

    private void showClubs(String category) {
        List<String> clubs = clubMap.get(category);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clubs);
        listView.setAdapter(adapter);
    }
}
