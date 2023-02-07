package com.example.renerubio.solicituddecredito2.Model;

public class Ocupacion {
    int ocupacion_id;
    String ocupacion;

    public Ocupacion(){
        this.ocupacion_id =0;
        this.ocupacion = "";
    }

    public Ocupacion(int ocupacion_id,String ocupacion){
        this.ocupacion_id = ocupacion_id;
        this.ocupacion = ocupacion;
    }

    public int getOcupacion_id() {
        return ocupacion_id;
    }

    public void setOcupacion_id(int ocupacion_id) {
        this.ocupacion_id = ocupacion_id;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
}
