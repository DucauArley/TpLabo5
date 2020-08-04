package com.example.tp;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StreamerViewHolder extends RecyclerView.ViewHolder
{
    public View view;

    public StreamerViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.view = itemView;
    }
}
