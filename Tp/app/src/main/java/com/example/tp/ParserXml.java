package com.example.tp;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParserXml
{

    public static List<Video> parsearXml(String xml) throws XmlPullParserException, IOException {
        List<Video> videos = new ArrayList<>();
        XmlPullParser parser = Xml.newPullParser();
        if(xml!=null && !xml.equals(""))
        {
            parser.setInput(new StringReader(xml));
            int event = parser.getEventType();
            Video v = null;

            loop:
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        parser.getName();// Para ver el nombre del tag
                        break;
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("item")) {
                            v = new Video();
                            Log.d("Tag", parser.getName());
                        }

                        //Creo que aca tendria que hacer algo para saber el nombre del streamer ya que cuando se lanzan hilos, no
                        //te aseguran que todos lleguen como los lanzaste, alta paja, no se si con lo que vimos de hilos pueda hacer
                        //Algo al respecto
                        else if (v != null) {
                            switch (parser.getName()) {
                                case "title":
                                    v.setTitulo(parser.nextText());
                                    Log.d("Title", v.getTitulo());
                                /*if(v.getTitulo().indexOf("LIVE") != -1)
                                {
                                    break loop; No es necesario esto ahora que arregle lo de el link Imagenes
                                    ahora podria usar el tag category, quiza sea mejor usar este para no gastar recursos
                                }*/
                                    break;
                                case "link":
                                    v.setLink(parser.nextText());
                                    Log.d("Link", v.getLink());
                                    break;
                                case "description":
                                    String texto = parser.nextText();
                                    String link = texto.substring(texto.indexOf("src=") + 5, texto.indexOf("g\" ") + 1);
                                    Log.d("Link Imagen", link + "");
                                    v.setLinkImagen(link);
                                    break;
                                case "category":
                                    if (parser.nextText().equals("live")) {
                                        v.setVivo(true);
                                        videos.add(v);
                                        break loop;
                                    }
                                    break;

                            }

                        /*Hay un dilema ya que streamer deberia tener una lista de titulos de videos y una lista de los
                        links a esos videos y una lista de los arrays de bites de las imagenes*/
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (v != null && parser.getName().equals("item")) {
                            videos.add(v);
                        }
                        break;
                }
                event = parser.next();
            }
        }

        return videos;
    }

}
