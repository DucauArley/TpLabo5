package com.example.tp;

import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class StreamerAdapter extends RecyclerView.Adapter<StreamerViewHolder> implements View.OnClickListener
{
    private List<Streamer> streamers;
    private View.OnClickListener listener;

    public StreamerAdapter(List<Streamer> streamers)
    {
        this.streamers = streamers;
    }

    @Override
    public StreamerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_streamer, parent, false);
        StreamerViewHolder vh = new StreamerViewHolder(view);

        view.setOnClickListener(this);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StreamerViewHolder holder, int position)
    {
        Streamer s = this.streamers.get(position);
        TextView tvStreamer = holder.view.findViewById(R.id.tvStreamer);
        TextView tvTitulo = holder.view.findViewById(R.id.tvTitulo);
        tvStreamer.setText(s.getNombre().substring(0,1).toUpperCase() + s.getNombre().substring(1));
        if(s.getEstado())
        {
            tvTitulo.setText(s.getVideo().get(0).getTitulo());
        }
    }

    @Override
    public int getItemCount()
    {
        return this.streamers.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v)
    {
        if(this.listener != null)
        {
            listener.onClick(v);
        }
    }


}
