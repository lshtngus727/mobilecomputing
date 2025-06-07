package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NoticeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private ArrayList<Notice> noticeList;
    private Button btnWriteNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);

        recyclerView = findViewById(R.id.recyclerViewNotices);
        btnWriteNotice = findViewById(R.id.btnWriteNotice);

        SharedPreferences pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String role = pref.getString("role", "비회원");

        if (role.equals("총동아리연합회")) {
            btnWriteNotice.setVisibility(View.VISIBLE);
        }

        btnWriteNotice.setOnClickListener(v -> {
            Intent intent = new Intent(NoticeListActivity.this, NoticeWriteActivity.class);
            startActivityForResult(intent, 100); // 글쓰기 요청
        });

        noticeList = new ArrayList<>();
        noticeList.add(new Notice("임시 공지", "공지 내용입니다.", "2025-06-07", true, true));
        noticeList.add(new Notice("총동연 어촌활동", "안녕하십니까, 국립한밭대학교 제 40대 Always 총동아리연합회 입니다. 종강을 앞두고 8월 ..", "2025-06-07", false, false));

        adapter = new NoticeAdapter(noticeList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    // 글쓰기 결과 받기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String date = data.getStringExtra("date");
            boolean isFixed = data.getBooleanExtra("isFixed", false);
            boolean hasAttachment = data.getBooleanExtra("hasAttachment", false);

            Notice newNotice = new Notice(title, content, date, isFixed, hasAttachment);
            noticeList.add(0, newNotice); // 리스트 맨 위에 추가
            adapter.notifyItemInserted(0);
            recyclerView.scrollToPosition(0);
        }
    }
}
