package com.pruebas123.petagram.pojo;

import java.io.Serializable;

public class Mascota implements Serializable {


    private int id;
    private int imagen;
    private String nombre;
    private int calificacion;
    private String urlFoto;

    public Mascota(int imagen, String nombre, int calificacion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.calificacion = calificacion;
    }

    public Mascota(int imagen, int calificacion) {
        this.imagen = imagen;
        this.calificacion = calificacion;
    }

    public Mascota() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }


}
