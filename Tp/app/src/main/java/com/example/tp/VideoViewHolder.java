package com.example.tp;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoViewHolder extends RecyclerView.ViewHolder
{
    public View view;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
    }
}
