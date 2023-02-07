package com.example.renerubio.solicituddecredito2.Entity;

public class LoginData {
    String usuario_id;
    String usuario;
    String contrasena;
    String admin;
    String activo;
    String empleado;
    String estado;
    String municipio;

    public LoginData(){
        this.usuario_id = "";
        this.usuario = "";
        this.contrasena = "";
        this.admin = "";
        this.activo = "";
        this.empleado = "";
        this.estado = "";
        this.municipio = "";
    }
    public LoginData(String usuario_id,String usuario,String contrasena,String admin,String empleado,String estado,String municipio){
        this.usuario_id = usuario_id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.admin = admin;
        this.empleado = empleado;
        this.estado = estado;
        this.municipio = municipio;
    }
    public LoginData (String usuario_id,String usuario,String estado){
        this.usuario_id = usuario_id;
        this.usuario = usuario;
        this.estado = estado;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
