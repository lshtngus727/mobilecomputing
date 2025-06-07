package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QnaListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QnaAdapter adapter;
    private ArrayList<Qna> qnaList;
    private Button btnWriteQna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_list);

        recyclerView = findViewById(R.id.recyclerViewQna);
        btnWriteQna = findViewById(R.id.btnWriteQna);

        qnaList = new ArrayList<>();

        // 임시 데이터
        qnaList.add(new Qna("동아리 지원 관련 문의", "서류 마감일이 언제인가요?", "20241234", "지원", false, "", "공개", false));
        qnaList.add(new Qna("공용 공간 예약 문의", "예약 가능한 시간대를 알고 싶습니다.", "20240001", "예약", false, "", "공개", true));

        adapter = new QnaAdapter(qnaList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnWriteQna.setOnClickListener(v -> {
            Intent intent = new Intent(QnaListActivity.this, QnaWriteActivity.class);
            startActivity(intent);
        });
    }
}
