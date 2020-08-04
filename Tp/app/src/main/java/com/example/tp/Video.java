package com.example.tp;

import java.io.Serializable;

public class Video implements Serializable
{
    private String link;
    private String linkImagen;
    private String titulo;
    private byte[] imagen;
    private boolean vivo = false;

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getLink() {
        return link;
    }

    public String getLinkImagen() {
        return linkImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public byte[] getImagen() {
        return imagen;
    }


}
