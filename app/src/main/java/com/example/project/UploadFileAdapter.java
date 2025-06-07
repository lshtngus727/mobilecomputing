package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UploadFileAdapter extends RecyclerView.Adapter<UploadFileAdapter.ViewHolder> {

    private List<File> fileList;
    private Context context;

    public UploadFileAdapter(List<File> fileList, Context context) {
        this.fileList = fileList;
        this.context = context;
    }

    public void updateList(List<File> newList) {
        this.fileList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UploadFileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_uploaded_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadFileAdapter.ViewHolder holder, int position) {
        File file = fileList.get(position);
        holder.tvFileTitle.setText(file.getTitle());
        holder.tvUploaderClub.setText("업로드 동아리: " + file.getClubName());
        holder.tvUploadedDate.setText(file.getUploadedDate());

        // 다운로드 버튼 동작 (예시)
        holder.btnDownload.setOnClickListener(v -> {
            Toast.makeText(context, "다운로드 기능 미구현", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFileTitle, tvUploaderClub, tvUploadedDate;
        Button btnDownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFileTitle = itemView.findViewById(R.id.tvFileTitle);
            tvUploaderClub = itemView.findViewById(R.id.tvUploaderClub);
            tvUploadedDate = itemView.findViewById(R.id.tvUploadedDate);
            btnDownload = itemView.findViewById(R.id.btnDownload);
        }
    }
}

