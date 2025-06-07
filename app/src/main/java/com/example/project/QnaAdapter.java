package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

        // 🔜 클릭 시 상세보기 화면으로 이동
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, QnaReplyActivity.class);  // QnaReplyActivity로 이동
            intent.putExtra("title", qna.getTitle());
            intent.putExtra("content", qna.getContent());
            intent.putExtra("writer", qna.getWriter());
            intent.putExtra("date", qna.getDate());
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
