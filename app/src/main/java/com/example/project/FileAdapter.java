package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    private List<FileItem> fileList;
    private Context context;

    public FileAdapter(List<FileItem> fileList, Context context) {
        this.fileList = fileList;
        this.context = context;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        FileItem item = fileList.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDate.setText(item.getDate());

        // 다운로드 버튼 클릭 시 처리
        holder.iconDownload.setOnClickListener(v -> {
            Toast.makeText(context, "다운로드 기능은 아직 구현되지 않았습니다.", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate;
        ImageView iconDownload;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvFileTitle);
            tvDate = itemView.findViewById(R.id.tvFileDate);
            iconDownload = itemView.findViewById(R.id.iconDownload);
        }
    }
}
