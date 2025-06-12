package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QnaAdapter extends RecyclerView.Adapter<QnaAdapter.QnaViewHolder> {
    private List<Qna> qnaList;
    private Context context;

    public QnaAdapter(List<Qna> qnaList, Context context) {
        this.qnaList = qnaList;
        this.context = context;
    }

    @NonNull
    @Override
    public QnaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_qna, parent, false);
        return new QnaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QnaViewHolder holder, int position) {
        Qna qna = qnaList.get(position);

        holder.tvTitle.setText(qna.getTitle());
        holder.tvWriter.setText("작성자: " + qna.getWriter());
        holder.tvDate.setText("날짜: " + qna.getDate());

        // QnA 항목 클릭 시 처리
        holder.itemView.setOnClickListener(v -> {
            SharedPreferences pref = context.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            String role = pref.getString("role", "비회원");

            Intent intent;
            if ("관리자".equals(role)) {
                intent = new Intent(context, QnaReplyActivity.class);
            } else {
                Toast.makeText(context, "상세보기 기능은 준비 중입니다.", Toast.LENGTH_SHORT).show();
                return; // 관리자만 이동
            }

            intent.putExtra("title", qna.getTitle());
            intent.putExtra("content", qna.getContent());
            intent.putExtra("hasAttachment", qna.hasAttachment());

            context.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        return qnaList.size();
    }

    public static class QnaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvWriter, tvDate;

        public QnaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvQnaTitle);
            tvWriter = itemView.findViewById(R.id.tvQnaWriter);
            tvDate = itemView.findViewById(R.id.tvQnaDate);
        }
    }
}
