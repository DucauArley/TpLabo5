package com.example.tp;

import android.util.Log;

import java.util.List;

public class Streamer implements Comparable
{
    private String nombre;
    private String link;
    private List<Video> video;
    private boolean estado = false;

    public Streamer(String nombre)
    {
        this.nombre = nombre;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLink() {
        return link;
    }

    public List<Video> getVideo() {
        return video;
    }

    @Override
    public int compareTo(Object s)
    {
        Streamer str = (Streamer) s;

        if(this.estado && str.getEstado())
        {
            return this.nombre.compareTo(str.getNombre());
            //Negativo si this.nombre va antes que str.nombre
            //positivo si this.nombre va despues que str.nombre
        }
        else
        {
            if(this.estado)
            {
                return -10;
            }
            else
            {
                if(str.getEstado())
                {
                    return 10;
                }
                else
                {
                    return this.nombre.compareTo(str.getNombre());
                }
            }
        }
    }

}
