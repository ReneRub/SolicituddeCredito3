package com.example.renerubio.solicituddecredito2.Model;

public class Municipio {
    int estado_id;
    String municipio;

    public Municipio(){
        this.estado_id =0;
        this.municipio ="";
    }
    public Municipio (int estado_id,String municipio){
        this.estado_id = estado_id;
        this.municipio = municipio;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
