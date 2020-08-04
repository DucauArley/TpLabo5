package com.example.tp;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager
{
    public String consultarPersonas(String urlString)
    {
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();// Aca se conecta
            int response = urlConnection.getResponseCode();

            if(response == 200)
            {
                InputStream is = urlConnection.getInputStream();
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream bais = new ByteArrayOutputStream();
                int cantidadByte = 0;

                while ((cantidadByte = is.read(buffer))!= -1)
                {
                    bais.write(buffer,0, cantidadByte);
                }

                is.close();

                Log.d("Respuesta servidor", bais.toString());

                return bais.toString();
            }
            else
            {
                throw new IOException("Error en el servidor");
            }


        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;

    }

    public byte[] consultarImg(String urlString)
    {
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();// Aca se conecta
            int response = urlConnection.getResponseCode();

            if(response == 200)
            {
                InputStream is = urlConnection.getInputStream();
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream bais = new ByteArrayOutputStream();
                int cantidadByte = 0;

                while ((cantidadByte = is.read(buffer))!= -1)
                {
                    bais.write(buffer,0, cantidadByte);
                }

                is.close();

                Log.d("Respuesta servidor", bais.toString());

                return bais.toByteArray();
            }
            else
            {
                throw new IOException("Error en el servidor");
            }


        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;

    }


}
