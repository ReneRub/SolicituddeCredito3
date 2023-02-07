package com.example.renerubio.solicituddecredito2.Entity;

public class Datos {
    private String unapesol;
    private String unapeaval;
    private String unaperefam;
    private String unapereper;
    private String sexo;
    private String estadociv;
    private String tipoviv;
    private String domscsnsol;
    private String tododiaverdom;
    private String tododiavertel;
    private String coincideine;
    private String creditosol;
    private String creditosolB;
    private String creditosolC;
    private String creditosolT;
    private String trabajasol;
    private String periodo1;
    private String comicionista;
    private String recotroing;
    private String periodo2;
    private String comping;
    private String trabajaaval;
    private String creditoaval;
    private String trabajafam;
    private String creditofam;
    private String trabajaper;
    private String creditoper;

    public Datos() {
        this.unapesol="";
        this.unapeaval="";
        this.unaperefam="";
        this.unapereper="";
        this.sexo="";
        this.estadociv="";
        this.tipoviv="";
        this.domscsnsol="";
        this.tododiaverdom="";
        this.tododiavertel="";
        this.coincideine="";
        this.creditosol="0";
        this.creditosolB="0";
        this.creditosolC="0";
        this.creditosolT="0";
        this.trabajasol="";
        this.periodo1="";
        this.comicionista="";
        this.recotroing="";
        this.periodo2="";
        this.comping="";
        this.trabajaaval="";
        this.creditoaval="";
        this.trabajafam="";
        this.creditofam="";
        this.trabajaper="";
        this.creditoper="";
    }

    public Datos(String unapesol,String unapeaval,String unaperefam,String unapereper,String sexo,String estadociv,String tipoviv,String domscsnsol,String tododiaverdom,String tododiavertel,String coincideine,String creditosol,String creditosolB,String creditosolC,String creditosolT,String trabajasol,String periodo1,
                     String comicionista,String recotroing,String periodo2,String comping,String trabajaaval,String creditoaval,String trabajafam,String creditofam,String trabajaper,String creditoper){
        this.unapesol = unapesol;
        this.unapeaval = unapeaval;
        this.unaperefam = unaperefam;
        this.unapereper = unapereper;
        this.sexo = sexo;
        this.estadociv = estadociv;
        this.tipoviv = tipoviv;
        this.domscsnsol = domscsnsol;
        this.domscsnsol = domscsnsol;
        this.tododiaverdom = tododiaverdom;
        this.tododiavertel = tododiavertel;
        this.coincideine = coincideine;
        this.creditosol = creditosol;
        this.creditosolB = creditosolB;
        this.creditosolC = creditosolC;
        this.creditosolT = creditosolT;
        this.trabajasol = trabajasol;
        this.periodo1 = periodo1;
        this.comicionista = comicionista;
        this.recotroing = recotroing;
        this.periodo2 = periodo2;
        this.comping = comping;
        this.trabajaaval = trabajaaval;
        this.creditoaval = creditoaval;
        this.trabajafam = trabajafam;
        this.creditofam = creditofam;
        this.trabajaper = trabajaper;
        this.creditoper = creditoper;
    }

    public String getCreditosolB() {
        return creditosolB;
    }

    public void setCreditosolB(String creditosolB) {
        this.creditosolB = creditosolB;
    }

    public String getCreditosolC() {
        return creditosolC;
    }

    public void setCreditosolC(String creditosolC) {
        this.creditosolC = creditosolC;
    }

    public String getCreditosolT() {
        return creditosolT;
    }

    public void setCreditosolT(String creditosolT) {
        this.creditosolT = creditosolT;
    }

    public String getUnapesol(){return unapesol;}
    public void setUnapesol(String unapesol){this.unapesol = unapesol;}

    public String getUnapeaval() {
        return unapeaval;
    }

    public void setUnapeaval(String unapeaval) {
        this.unapeaval = unapeaval;
    }

    public String getUnaperefam() {
        return unaperefam;
    }

    public void setUnaperefam(String unaperefam) {
        this.unaperefam = unaperefam;
    }

    public String getUnapereper() {
        return unapereper;
    }

    public void setUnapereper(String unapereper) {
        this.unapereper = unapereper;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadociv() {
        return estadociv;
    }

    public void setEstadociv(String estadociv) {
        this.estadociv = estadociv;
    }

    public String getTipoviv() {
        return tipoviv;
    }

    public void setTipoviv(String tipoviv) {
        this.tipoviv = tipoviv;
    }

    public String getDomscsnsol() {
        return domscsnsol;
    }

    public void setDomscsnsol(String domscsnsol) {
        this.domscsnsol = domscsnsol;
    }

    public String getTododiaverdom() {
        return tododiaverdom;
    }

    public void setTododiaverdom(String tododiaverdom) {
        this.tododiaverdom = tododiaverdom;
    }

    public String getTododiavertel() {
        return tododiavertel;
    }

    public void setTododiavertel(String tododiavertel) {
        this.tododiavertel = tododiavertel;
    }

    public String getCoincideine() {
        return coincideine;
    }

    public void setCoincideine(String coincideine) {
        this.coincideine = coincideine;
    }

    public String getCreditosol() {
        return creditosol;
    }

    public void setCreditosol(String creditosol) {
        this.creditosol = creditosol;
    }

    public String getTrabajasol() {
        return trabajasol;
    }

    public void setTrabajasol(String trabajasol) {
        this.trabajasol = trabajasol;
    }

    public String getPeriodo1() {
        return periodo1;
    }

    public void setPeriodo1(String periodo1) {
        this.periodo1 = periodo1;
    }

    public String getComicionista() {
        return comicionista;
    }

    public void setComicionista(String comicionista) {
        this.comicionista = comicionista;
    }

    public String getRecotroing() {
        return recotroing;
    }

    public void setRecotroing(String recotroing) {
        this.recotroing = recotroing;
    }

    public String getPeriodo2() {
        return periodo2;
    }

    public void setPeriodo2(String periodo2) {
        this.periodo2 = periodo2;
    }

    public String getComping() {
        return comping;
    }

    public void setComping(String comping) {
        this.comping = comping;
    }

    public String getTrabajaaval() {
        return trabajaaval;
    }

    public void setTrabajaaval(String trabajaaval) {
        this.trabajaaval = trabajaaval;
    }

    public String getCreditoaval() {
        return creditoaval;
    }

    public void setCreditoaval(String creditoaval) {
        this.creditoaval = creditoaval;
    }

    public String getTrabajafam() {
        return trabajafam;
    }

    public void setTrabajafam(String trabajafam) {
        this.trabajafam = trabajafam;
    }

    public String getCreditofam() {
        return creditofam;
    }

    public void setCreditofam(String creditofam) {
        this.creditofam = creditofam;
    }

    public String getTrabajaper() {
        return trabajaper;
    }

    public void setTrabajaper(String trabajaper) {
        this.trabajaper = trabajaper;
    }

    public String getCreditoper() {
        return creditoper;
    }

    public void setCreditoper(String creditoper) {
        this.creditoper = creditoper;
    }

}
