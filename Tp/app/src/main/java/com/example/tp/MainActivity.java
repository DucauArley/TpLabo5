package com.example.tp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    public static List<Streamer> listaStreamers;
    public static StreamerAdapter adapter;
    public static RecyclerView rvStreamer;
    public static JSONArray jsonArray;
    public static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar toolBar = getSupportActionBar();
        toolBar.setDisplayHomeAsUpEnabled(true);

        handler = new Handler(this);

        SharedPreferences prefs = getSharedPreferences("Streamers", Context.MODE_PRIVATE);
        listaStreamers = new ArrayList<Streamer>();
        listaStreamers.add(new Streamer("frankkaster"));
        listaStreamers.add(new Streamer("coscu"));
        listaStreamers.add(new Streamer("goncho"));
        listaStreamers.add(new Streamer("zeko"));
        listaStreamers.add(new Streamer("luken"));
        listaStreamers.add(new Streamer("pimpeano"));
        listaStreamers.add(new Streamer("elcanaldejoaco"));
        listaStreamers.add(new Streamer("momoladinastia"));
        listaStreamers.add(new Streamer("loltyler1"));

        try
        {
           jsonArray = new JSONArray(prefs.getString("Streamers", "[]"));
           if(!jsonArray.equals("[]"))
           {
               for (int i=0; i < jsonArray.length(); i++)
               {
                   Log.d("ishere", jsonArray.getJSONObject(i).getString("Nombre"));
                  listaStreamers.add(new Streamer(jsonArray.getJSONObject(i).getString("Nombre")));
               }
           }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        HiloHttp miHilo;

        adapter = new StreamerAdapter(listaStreamers);
        rvStreamer = super.findViewById(R.id.rvStreamers);

        for(int i=0; i< listaStreamers.size(); i++)
        {
            miHilo = new HiloHttp(handler, "https://twitchrss.appspot.com/vod/" + listaStreamers.get(i).getNombre(), listaStreamers.get(i), null, true);
            miHilo.start();
        }

        adapter.setOnClickListener(new MyListenerStreamer(this));

        //Incorporar en carpeta los cardviews

        /*miHilo = new HiloHttp(handler, "https://twitchrss.appspot.com/vod/frankkaster", true);
        miHilo.start();*/

        //En twitch hay un live dentro de el primer <category>, sino hay un archive por cada video que tenga
        //https://www.nimo.tv/frankkaster

    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {

        Log.d("Mensaje", "Llego un mensaje" + msg.obj);
        Streamer st = (Streamer) msg.obj;

        for(int i=0; i< listaStreamers.size(); i++)
        {
            if(listaStreamers.get(i).getNombre() == st.getNombre())
            {
                listaStreamers.set(i, st);
            }
        }

        this.adapter.notifyDataSetChanged();

        Collections.sort(listaStreamers);

        rvStreamer.setAdapter(adapter);

        rvStreamer.setLayoutManager(new LinearLayoutManager(this));

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuItem)
    {
        super.getMenuInflater().inflate(R.menu.menu, menuItem);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.dg)
        {
            MiDialogo miDialogo = new MiDialogo(this);
            miDialogo.show(super.getSupportFragmentManager(), "Confirm");
        }
        else if(android.R.id.home == item.getItemId())
        {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
