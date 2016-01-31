package com.englishnary.eridev.android.moviesfinder;

/**
 * Created by eridev on 31/01/16.
 */

    public class Pelicula {

        private String titulo;
        private int anyo;
        private String[] guionistas;
        private String[] actores;
        private String plot_simple;
        private Caratula caratula;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public Caratula getCaratula() {
        return caratula;
    }

    public void setCaratula(Caratula caratula) {
        this.caratula = caratula;
    }

    public String getPlot_simple() {
        return plot_simple;
    }

    public void setPlot_simple(String plot_simple) {
        this.plot_simple = plot_simple;
    }

    public String[] getActores() {
        return actores;
    }

    public void setActores(String[] actores) {
        this.actores = actores;
    }

    public String[] getGuionistas() {
        return guionistas;
    }

    public void setGuionistas(String[] guionistas) {
        this.guionistas = guionistas;
    }
}
