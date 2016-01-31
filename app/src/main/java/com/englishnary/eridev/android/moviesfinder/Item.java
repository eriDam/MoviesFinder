package com.englishnary.eridev.android.moviesfinder;

/**
 * Created by eridev on 31/01/16.
 */
public class Item {
    private Integer imagen;
    private String titulo;
    private String url;

    public Item() {
        super();
    }

    public Item(Integer image, String title, String url) {
        super();
        this.imagen = image;
        this.titulo = title;
        this.url = url;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
