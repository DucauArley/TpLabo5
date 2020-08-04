package com.example.tp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import java.io.Serializable;

public class MyListenerStreamer implements View.OnClickListener
{
    private MainActivity a;

    public MyListenerStreamer(MainActivity a)
    {
        this.a = a;
    }

    @Override
    public void onClick(View v) {
        Streamer s = a.listaStreamers.get(a.rvStreamer.getChildAdapterPosition(v));
        if(s.getEstado())
        {
            Log.d("NombreStreamer", s.getLink());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s.getLink()));
            a.startActivity(browserIntent);
        }
        else
        {
            Intent intent = new Intent(a, activity_videos.class);
            intent.putExtra("Videos", (Serializable) s.getVideo());

            a.startActivityForResult(intent, 1);
        }
    }
}
