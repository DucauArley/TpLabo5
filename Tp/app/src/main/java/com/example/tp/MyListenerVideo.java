package com.example.tp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class MyListenerVideo implements View.OnClickListener
{

    private activity_videos a;

    public MyListenerVideo(activity_videos a)
    {
        this.a = a;
    }

    @Override
    public void onClick(View v)
    {
        Video vid = a.listaVideos.get(a.rvVideos.getChildAdapterPosition(v));

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(vid.getLink()));
        a.startActivity(browserIntent);

    }
}
