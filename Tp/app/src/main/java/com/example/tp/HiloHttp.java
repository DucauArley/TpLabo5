package com.example.tp;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HiloHttp extends Thread
{
    public Handler handler;
    public String url;
    public boolean texto = true;//Esto era por si necesito cambiar el hilo para buscar otra cosa, le tendria que pasar por constructor
    public Streamer streamer;
    public Video video;

    public HiloHttp(Handler handler, String url, Streamer streamer, Video video, boolean texto)
    {
        this.handler = handler;
        this.url = url;
        this.streamer = streamer;
        this.video = video;
        this.texto = texto;
    }


    @Override
    public void run()
    {
        HttpManager manager = new HttpManager();

        /*try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/


        if (texto)
        {
            String respuesta = manager.consultarPersonas(this.url);

            try
            {
                List<Video> v = ParserXml.parsearXml(respuesta);
                for(int i=0;i<v.size(); i++)
                {
                    if(v.get(i).isVivo())
                    {
                        this.streamer.setEstado(true);
                        this.streamer.setLink(v.get(i).getLink());
                        this.streamer.setVideo(v);
                    }
                    else
                    {
                        this.streamer.setVideo(v);
                    }
                }

            /*Devuelve [] Podria usar esto como solucion
            previsoria hasta que encuentre algo mejor para mostrar que esta en vivo o sino le agrego solamente el titulo del
            video y se lo mando de una*/
            }
            catch (XmlPullParserException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            Message message = new Message();
            message.obj = this.streamer;
            this.handler.sendMessage(message);
        }
        else
        {
            byte[] imagen = manager.consultarImg(this.url);
            this.video.setImagen(imagen);
            Message message = new Message();
            message.obj = this.video;
            this.handler.sendMessage(message);

        }
    }

}
