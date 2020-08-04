package com.example.tp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.stream.Stream;

public class ClickDialog implements DialogInterface.OnClickListener
{

    public View v;
    public MainActivity mainActivity;

    public ClickDialog(View v, MainActivity ma)
    {
        this.v = v;
        this.mainActivity = ma;
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        switch(which)
        {
            case -1:
                //Este es el positivo
                EditText et = v.findViewById(R.id.etStreamer);
                if(!et.getText().equals("") && et.getText() != null)
                {
                    JSONObject json = new JSONObject();
                    String nombre = et.getText().toString().toLowerCase();
                    try
                    {
                        json.put("Nombre", nombre);
                        this.mainActivity.jsonArray.put(json);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    SharedPreferences preferences = this.mainActivity.getSharedPreferences("Streamers", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Streamers", this.mainActivity.jsonArray.toString());
                    Streamer s = new Streamer(nombre);
                    this.mainActivity.listaStreamers.add(s);
                    HiloHttp miHilo;
                    miHilo = new HiloHttp(this.mainActivity.handler, "https://twitchrss.appspot.com/vod/" + nombre, s, null, true);
                    miHilo.start();

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    if(this.mainActivity.listaStreamers.get(this.mainActivity.listaStreamers.indexOf(s)).getVideo() == null)
                    {
                        this.mainActivity.listaStreamers.remove(s);
                    }
                    else
                    {
                        editor.commit();
                        this.mainActivity.adapter.notifyDataSetChanged();
                    }
                }
                Log.d("Click", "clickeo el boton positivo " + et.getText());
                break;
            case -2:
                //Este es el negativo
                Log.d("Click", "clickeo el boton negativo");
                break;
            case -3:
                Log.d("Click", "clickeo el boton neutral");
                break;
        }

    }

}
