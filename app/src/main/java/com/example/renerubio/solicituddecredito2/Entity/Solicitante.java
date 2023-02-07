package com.example.renerubio.solicituddecredito2.Entity;

public class Solicitante {
    String solicitud_id;
    String rol_id;
    String unapellido;
    String apellidopat;
    String apellidomat;
    String nombre;
    String fechanac;
    String sexo;
    String estadociv;
    String pais;
    String curp;
    String no_ineife;
    String no_rfc;

    public Solicitante(){
        this.solicitud_id = "";
        this.rol_id = "";
        this.unapellido = "";
        this.apellidopat = "";
        this.apellidomat = "";
        this.nombre = "";
        this.fechanac = "";
        this.sexo = "";
        this.estadociv = "";
        this.pais = "";
        this.curp = "";
        this.no_ineife = "";
        this.no_rfc = "";
    }
    public Solicitante(String solicitud_id,String rol_id,String unapellido,String apellidopat,String apellidomat,String nombre,String fechanac,String sexo,String estadociv,String pais,String curp,String no_ineife,String no_rfc){
        this.solicitud_id = solicitud_id;
        this.rol_id = rol_id;
        this.unapellido = unapellido;
        this.apellidopat = apellidopat;
        this.apellidomat = apellidomat;
        this.nombre = nombre;
        this.fechanac = fechanac;
        this.sexo = sexo;
        this.estadociv = estadociv;
        this.pais = pais;
        this.curp = curp;
        this.no_ineife = no_ineife;
        this.no_rfc = no_rfc;
    }
    public String getSolicitud_id(){return solicitud_id;}
    public void setSolicitud_id(String solicitud_id){this.solicitud_id = solicitud_id;}

    public String getRol_id(){return rol_id;}
    public void setRol_id(String rol_id){this.rol_id = rol_id;}

    public String getUnapellido(){return unapellido;}
    public void setUnapellido(String unapellido){this.unapellido = unapellido;}

    public String getApellidopat(){return apellidopat;}
    public void setApellidopat(String apellidopat){this.apellidopat = apellidopat;}

    public String getApellidomat(){return apellidomat;}
    public void setApellidomat(String apellidomat){this.apellidomat = apellidomat;}

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getFechanac(){return fechanac;}
    public void setFechanac(String fechanac){this.fechanac = fechanac;}

    public String getSexo(){return sexo;}
    public void setSexo(String sexo){this.sexo = sexo;}

    public String getPais(){return pais;}
    public void setPais(String pais){this.pais = pais;}

    public String getEstadociv(){return estadociv;}
    public void setEstadociv(String estadociv){this.estadociv = estadociv;}

    public String getCurp(){return curp;}
    public void setCurp(String curp){this.curp = curp;}

    public String getNo_ineife(){return no_ineife;}
    public void setNo_ineife(String no_ineife){this.no_ineife = no_ineife;}

    public String getNo_rfc(){return no_rfc;}
    public void setNo_rfc(String no_rfc){this.no_rfc = no_rfc;}
}
