package com.devzamse.appoficial.InicioNavigation;

public class Almuerzos {

    private String nombre;
    private String info;
    private String image;

    public Almuerzos() {
    }

    public Almuerzos(String nombre, String info, String image) {
        this.nombre = nombre;
        this.info = info;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
