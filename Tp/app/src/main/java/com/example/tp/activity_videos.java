package com.example.tp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.io.Serializable;
import java.util.List;

public class activity_videos extends AppCompatActivity implements Handler.Callback {

    public static List<Video> listaVideos;
    public static VideoAdapter adapter;
    public static RecyclerView rvVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        ActionBar toolBar = getSupportActionBar();
        toolBar.setDisplayHomeAsUpEnabled(true);

        Handler handler = new Handler(this);

        listaVideos = (List<Video>) super.getIntent().getSerializableExtra("Videos");
        adapter = new VideoAdapter(listaVideos);
        rvVideos = super.findViewById(R.id.rvVideos);

        Log.d("Cantidad", listaVideos.size() + "");
        //Tendria que hacer algo en el hilo para que no me vuelva a parsear todas las weas

        for(int i=0; i< listaVideos.size(); i++)
        {
            HiloHttp miHilo = new HiloHttp(handler, listaVideos.get(i).getLinkImagen(), null, listaVideos.get(i), false);
            miHilo.start();
        }

        rvVideos.setAdapter(adapter);

        rvVideos.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnClickListener(new MyListenerVideo(this));

    }

    @Override
    public boolean handleMessage(@NonNull Message msg)
    {
        Video vid = (Video) msg.obj;

        for(int i=0; i< listaVideos.size(); i++)
        {
            if(listaVideos.get(i).getTitulo() == vid.getTitulo())
            {
                listaVideos.set(i, vid);
            }
        }

        this.adapter.notifyDataSetChanged();

        rvVideos.setAdapter(adapter);

        rvVideos.setLayoutManager(new LinearLayoutManager(this));

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(android.R.id.home == item.getItemId())
        {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
