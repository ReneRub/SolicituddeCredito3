package com.example.renerubio.solicituddecredito2.Model;

public class Estado {
	int estado_id;
	String estado;

	public Estado (){
		this.estado_id =0;
		this.estado ="";
	}
	public Estado (int estado_id,String estado){
		this.estado_id= estado_id;
		this.estado = estado;
	}

	public int getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(int estado_id) {
		this.estado_id = estado_id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
