package com.example.tp;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> implements View.OnClickListener
{
    private List<Video> videos;
    private View.OnClickListener listener;

    public VideoAdapter(List<Video> videos)
    {
        this.videos = videos;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        VideoViewHolder vh = new VideoViewHolder(view);

        view.setOnClickListener(this);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position)
    {
        Video v = this.videos.get(position);
        TextView tvTitulo = holder.view.findViewById(R.id.tvTitulo);
        ImageView iv = holder.view.findViewById(R.id.Imagen);
        Log.d("Titulovsky", v.getTitulo());
        tvTitulo.setText(v.getTitulo());
        if(v.getImagen() != null)
        {
            Log.d("Imagenes de bits", v.getImagen() + "");
            iv.setImageBitmap(BitmapFactory.decodeByteArray(v.getImagen(), 0, v.getImagen().length));
        }
    }

    @Override
    public int getItemCount()
    {
        return this.videos.size();
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
