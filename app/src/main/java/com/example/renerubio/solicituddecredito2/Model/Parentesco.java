package com.example.renerubio.solicituddecredito2.Model;

public class Parentesco {
    String parentesco_id;
    String parentesco;

    public Parentesco(){
        this.parentesco_id = "";
        this.parentesco ="";
    }

    public Parentesco(String parentesco_id, String parentesco){
        this.parentesco_id = parentesco_id;
        this.parentesco = parentesco;
    }

    public String getParentesco_id() {
        return parentesco_id;
    }

    public void setParentesco_id(String parentesco_id) {
        this.parentesco_id = parentesco_id;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public  void limpiar(){
        this.parentesco = null;
        this.parentesco_id = null;
    }

}
