package com.example.renerubio.solicituddecredito2.Model;

public class Colonia {
    int ciudad_id;
    int colonia_id;
    String colonia;
    int cp;

    public Colonia() {
        this.ciudad_id = 0;
        this.colonia_id = 0;
        this.colonia ="";
        this.cp = 0;
    }

    public Colonia(int ciudad_id,int colonia_id,String colonia, int cp){
        this.ciudad_id = ciudad_id;
        this.colonia_id = colonia_id;
        this.colonia = colonia;
        this.cp = cp;
    }
    public int getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(int ciudad_id) {
        this.ciudad_id = ciudad_id;
    }

    public int getColonia_id() {
        return colonia_id;
    }

    public void setColonia_id(int colonia_id) {
        this.colonia_id = colonia_id;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }
}
