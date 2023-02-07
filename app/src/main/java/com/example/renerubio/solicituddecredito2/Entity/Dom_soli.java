package com.example.renerubio.solicituddecredito2.Entity;

public class Dom_soli {
    String solicitud_id;
    String rol_id;
    String tipoviv;
    String viv_anos;
    String viv_meses;
    String dom_sc_sn;
    String calle;
    String no_ext;
    String no_int;
    String cp;
    String colonia;
    String tipo_loc;
    String localidad;
    String estado;
    String municipio;
    String ent_calles;
    String tel_part;
    String tel_cel;
    String email;
    String no_depen;
    String no_lab;
    String credit_con;
    String coincide_ine;
    String tododia_dom;
    String hora1a_dom;
    String hora1b_dom;
    String hora2a_dom;
    String hora2b_dom;
    String tododia_tel;
    String hora1a_tel;
    String hora1b_tel;
    String hora2a_tel;
    String hora2b_tel;

    public Dom_soli(){
        this.solicitud_id = "";
        this.rol_id = "";
        this.tipoviv = "";
        this.viv_anos = "";
        this.viv_meses = "";
        this.dom_sc_sn = "";
        this.calle = "";
        this.no_ext = "";
        this.no_int = "";
        this.cp = "";
        this.colonia = "";
        this.tipo_loc = "";
        this.localidad = "";
        this.estado = "";
        this.municipio = "";
        this.ent_calles = "";
        this.tel_part = "";
        this.tel_cel = "";
        this.email = "";
        this.no_depen = "";
        this.no_lab = "";
        this.credit_con = "";
        this.coincide_ine = "";
        this.tododia_dom = "";
        this.hora1a_dom = "";
        this.hora1b_dom = "";
        this.hora2a_dom = "";
        this.hora2b_dom = "";
        this.tododia_tel = "";
        this.hora1a_tel = "";
        this.hora1b_tel = "";
        this.hora2a_tel = "";
        this.hora2b_tel = "";
    }
    public Dom_soli(String solicitud_id,String rol_id,String tipoviv,String viv_anos,String viv_meses,String dom_sc_sn,String calle,String no_ext,String no_int,String cp,String colonia,String tipo_loc,String localidad,
                    String estado,String municipio,String ent_calles,String tel_part,String tel_cel,String email,String no_depen,String no_lab,String credit_con,String coincide_ine,String tododia_dom,String hora1a_dom,
                    String hora1b_dom,String hora2a_dom,String hora2b_dom,String tododia_tel,String hora1a_tel,String hora1b_tel,String hora2a_tel,String hora2b_tel){
        this.solicitud_id = solicitud_id;
        this.rol_id = rol_id;
        this.tipoviv = tipoviv;
        this.viv_anos = viv_anos;
        this.viv_meses = viv_meses;
        this.dom_sc_sn = dom_sc_sn;
        this.calle = calle;
        this.no_ext = no_ext;
        this.no_int = no_int;
        this.cp = cp;
        this.colonia = colonia;
        this.tipo_loc = tipo_loc;
        this.localidad = localidad;
        this.estado = estado;
        this.municipio = municipio;
        this.ent_calles = ent_calles;
        this.tel_part = tel_part;
        this.tel_cel = tel_cel;
        this.email = email;
        this.no_depen = no_depen;
        this.no_lab = no_lab;
        this.credit_con = credit_con;
        this.coincide_ine = coincide_ine;
        this.tododia_dom = tododia_dom;
        this.hora1a_dom = hora1a_dom;
        this.hora1b_dom = hora1b_dom;
        this.hora2a_dom = hora2a_dom;
        this.hora2b_dom = hora2b_dom;
        this.tododia_tel = tododia_tel;
        this.hora1a_tel = hora1a_tel;
        this.hora1b_tel = hora1b_tel;
        this.hora1b_tel = hora1b_tel;
        this.hora2a_tel = hora2a_tel;
        this.hora2b_tel = hora2b_tel;
    }
    public String getSolicitud_id(){return solicitud_id;}
    public void setSolicitud_id(String solicitud_id){this.solicitud_id = solicitud_id;}

    public String getRol_id(){return rol_id;}
    public void setRol_id(String rol_id){this.rol_id = rol_id;}

    public String getTipoviv(){return tipoviv;}
    public void setTipoviv(String tipoviv){this.tipoviv = tipoviv;}

    public String getViv_anos(){return viv_anos;}
    public void setViv_anos(String viv_anos){this.viv_anos = viv_anos;}

    public String getViv_meses(){return viv_meses;}
    public void setViv_meses(String viv_meses){this.viv_meses = viv_meses;}

    public String getDom_sc_sn(){return dom_sc_sn;}
    public void setDom_sc_sn(String dom_sc_sn){this.dom_sc_sn = dom_sc_sn;}

    public String getCalle(){return calle;}
    public void setCalle(String calle){this.calle = calle;}

    public String getNo_ext(){return no_ext;}
    public void setNo_ext(String no_ext){this.no_ext = no_ext;}

    public String getNo_int(){return no_int;}
    public void setNo_int(String no_int){this.no_int = no_int;}

    public String getCp(){return cp;}
    public void setCp(String cp){this.cp = cp;}

    public String getColonia(){return colonia;}
    public void setColonia(String colonia){this.colonia = colonia;}

    public String getTipo_loc(){return tipo_loc;}
    public void setTipo_loc(String tipo_loc){this.tipo_loc = tipo_loc;}

    public String getLocalidad(){return localidad;}
    public void setLocalidad(String localidad){this.localidad = localidad;}

    public String getEstado(){return estado;}
    public void setEstado(String estado){this.estado = estado;}

    public String getMunicipio(){return municipio;}
    public void setMunicipio(String municipio){this.municipio = municipio;}

    public String getEnt_calles(){return ent_calles;}
    public void setEnt_calles(String ent_calles){this.ent_calles = ent_calles;}

    public String getTel_part(){return tel_part;}
    public void setTel_part(String tel_part){this.tel_part = tel_part;}

    public String getTel_cel(){return tel_cel;}
    public void setTel_cel(String tel_cel){this.tel_cel = tel_cel;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getNo_depen(){return no_depen;}
    public void setNo_depen(String no_depen){this.no_depen = no_depen;}

    public String getNo_lab(){return no_lab;}
    public void setNo_lab(String no_lab){this.no_lab = no_lab;}

    public String getCredit_con(){return credit_con;}
    public void setCredit_con(String credit_con){this.credit_con = credit_con;}

    public String getCoincide_ine(){return coincide_ine;}
    public void setCoincide_ine(String coincide_ine){this.coincide_ine = coincide_ine;}

    public String getTododia_dom(){return tododia_dom;}
    public void setTododia_dom(String tododia_dom){this.tododia_dom = tododia_dom;}

    public String getHora1a_dom(){return hora1a_dom;}
    public void setHora1a_dom(String hora1a_dom){this.hora1a_dom = hora1a_dom;}

    public String getHora1b_dom(){return hora1b_dom;}
    public void setHora1b_dom(String hora1b_dom){this.hora1b_dom = hora1b_dom;}

    public String getHora2a_dom(){return hora2a_dom;}
    public void setHora2a_dom(String hora2a_dom){this.hora2a_dom = hora2a_dom;}

    public String getHora2b_dom(){return hora2b_dom;}
    public void setHora2b_dom(String hora2b_dom){this.hora2b_dom = hora2b_dom;}

    public String getTododia_tel(){return tododia_tel;}
    public void setTododia_tel(String tododia_tel){this.tododia_tel = tododia_tel;}

    public String getHora1a_tel(){return hora1a_tel;}
    public void setHora1a_tel(String hora1a_tel){this.hora1a_tel = hora1a_tel;}

    public String getHora1b_tel(){return hora1b_tel;}
    public void setHora1b_tel(String hora1b_tel){this.hora1b_tel = hora1b_tel;}

    public String getHora2a_tel(){return hora2a_tel;}
    public void setHora2a_tel(String hora2a_tel){this.hora2a_tel = hora2a_tel;}

    public String getHora2b_tel(){return hora2b_tel;}
    public void setHora2b_tel(String hora2b_tel){this.hora2b_tel = hora2b_tel;}
}

