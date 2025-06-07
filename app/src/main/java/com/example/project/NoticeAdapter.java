package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private List<Notice> noticeList;
    private Context context;

    public NoticeAdapter(List<Notice> noticeList, Context context) {
        this.noticeList = noticeList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice = noticeList.get(position);

        holder.tvTitle.setText(notice.getTitle());
        holder.tvDate.setText(notice.getDate());

        if (notice.hasAttachment()) {
            holder.iconAttachment.setVisibility(View.VISIBLE);
        } else {
            holder.iconAttachment.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NoticeDetailActivity.class);
            intent.putExtra("title", notice.getTitle());
            intent.putExtra("date", notice.getDate());
            intent.putExtra("content", notice.getContent());
            intent.putExtra("hasAttachment", notice.hasAttachment());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvIndex;
        ImageView iconAttachment;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvNoticeTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvIndex = itemView.findViewById(R.id.tvNoticeIndex);
            iconAttachment = itemView.findViewById(R.id.iconAttachment);
        }
    }
}
