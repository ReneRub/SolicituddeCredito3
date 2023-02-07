package com.example.renerubio.solicituddecredito2.Model;

public class Categoria {
    int categoria;
    String nombre;

    public Categoria(){
        this.categoria =0;
        this.nombre = "";
    }

    public Categoria(int categoria,String nombre){
        this.categoria = categoria;
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
