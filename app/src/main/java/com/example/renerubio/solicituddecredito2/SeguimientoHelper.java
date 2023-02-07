package com.example.renerubio.solicituddecredito2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renerubio.solicituddecredito2.Entity.Datos;
import com.example.renerubio.solicituddecredito2.Model.Estado;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class SeguimientoHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "solicitudcredito.sqlite";
    private static final int DATABASE_VERSION = 2;

    List<String> datos;
    List<String> categolist;
    String Tabla;
    String columna[];
    int i;
    public SQLiteDatabase db;
    Context ctx;
    Cursor c;
    int contador1;
    helper helpendientes;
    CargaridHelper cargahelp;
    String categoria1;
    boolean bandera;
    boolean resp = false;

    String columns[] = new String[]{"_id","solicitud_id","rol_id","unapellido","apellidopat","apellidomat","nombre","fechanac","sexo","estadociv","curp","no_ineife","no_rfc"};
    String solicitante = "Solicitante";

    String columns2[] = new String[]{"_id","solicitud_id","rol_id","tipoviv","viv_anos","viv_meses","calle","no_ext","no_int","cp","colonia","coloniaine","localidad","estado","municipio","ent_calles","tel_part","tel_cel",
            "email","no_depen","no_lab","banco","casa_com","tarj_ban","ninguno","coincide_ine","tododia_dom","hora1a_dom","hora1b_dom","hora2a_dom","hora2b_dom","tododia_tel","hora1a_tel","hora1b_tel","hora2a_tel","hora2b_tel"};
    String dom_cliente = "Dom_Cliente";

    String columns3[] = new String[]{"_id","solicitud_id","rol_id","trabaja","ocupacion","otra_ocup","nombre_trab",
            "tel_trab","sueldo","periodo"};
    String datos_lab = "Datos_Lab";

    String columns4[] = new String[]{"_id","solicitud_id","rol_id","unapellido","parentesco","apellido_pat","apellido_mat","nombre","tel_part","tel_cel"};
    String aval = "Aval";

    String columns5[] = new String[]{"_id","solicitud_id","rol_id","calle","no_ext","no_int","cp","colonia","colonia_ine","localidad","estado","municipio"};
    String dom_aval = "Dom_Aval";

    String columns6[] = new String[]{"_id","solicitud_id","rol_id","unapellido","parentesco","apellido_pat","apellido_mat","nombre","tel_part","tel_cel"};
    String ref_fam = "Ref_Fam";

    String columns7[] = new String[]{"_id","solicitud_id","rol_id","calle","no_ext","no_int","cp","colonia","colonia_ine","localidad","estado","municipio"};
    String dom_reffam = "Dom_RefFam";

    String columns8[] = new String[]{"_id","solicitud_id","rol_id","unapellido","parentesco","apellido_pat","apellido_mat","nombre","tel_part","tel_cel"};
    String ref_per = "Ref_Per";

    String columns9[] = new String[]{"_id","solicitud_id","rol_id","categoria"};
    String categorias = "CatCategoria";

    public SeguimientoHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        //setForcedUpgrade(2);

        if(DATABASE_VERSION < 2 ){
            setForcedUpgrade(2);
        }

        db = getWritableDatabase();


        ctx = context;
        helpendientes = new helper(ctx);
        cargahelp = new CargaridHelper(ctx);
        categolist = new ArrayList<>();
    }
    /*
    @Override
    public void onUpgrade(SQLiteDatabase db, int DATABASE_VERSION, int NewDATABASE_VERSION) {
        super.setForcedUpgrade(NewDATABASE_VERSION);
        super.onUpgrade(db, DATABASE_VERSION, NewDATABASE_VERSION);
    }*/


    //Guardar seguimiento++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public boolean GuardarSolicitud(CheckBox unapesolsi, EditText apepat, EditText apemat, EditText nombre, EditText fecnac, EditText sexo, EditText estciv,
                        EditText curp, EditText noine, EditText norfc, EditText tipviv, Spinner vivanos, Spinner vivmeses, EditText calle, EditText no_ext, EditText no_int, EditText cp, EditText colonia,
                        EditText colonia_ine, EditText localidad, EditText estado, EditText municipio, EditText entrecalle, EditText tel_part, EditText tel_cel, EditText email, EditText nodepen, EditText nolab,CheckBox banco,CheckBox casacom,CheckBox tarjban,CheckBox ninguno, CheckBox coincideine, CheckBox tododiadomsi, EditText hora1a_dom,
                        EditText hora1b_dom, EditText hora2a_dom, EditText hora2b_dom, CheckBox tododiatelsi, EditText hora1a_tel, EditText hora1b_tel, EditText hora2a_tel, EditText hora2b_tel,
                        CheckBox trabajasi,CheckBox trabajano, EditText ocupacion,EditText otraocupa, EditText nombre_trab,EditText tel_trab, EditText ingresos, EditText periodo,CheckBox unapesiaval, EditText parentesco_aval, EditText apelli_pataval, EditText apelli_mataval, EditText nombre_aval, EditText tel_partaval, EditText tel_celaval,
                        EditText calle_aval, EditText no_extaval, EditText no_intaval, EditText cp_aval, EditText colonia_aval,EditText coline_aval, EditText localidad_aval, EditText estado_aval, EditText municipio_aval,
                        CheckBox unapellisi_fam, EditText parentesco_fam, EditText apellipat_fam, EditText apellimat_fam, EditText nombre_fam, EditText tel_partfam, EditText tel_celfam,
                        EditText calle_fam, EditText no_extfam, EditText no_intfam, EditText cp_fam, EditText colonia_fam, EditText colinerefam, EditText localidad_fam, EditText estado_fam,EditText municipio_fam,CheckBox unapellisi_per,EditText parentesco_per,
                        EditText apellipat_per,EditText apellimat_per,EditText nombre_per,EditText tel_partper,EditText tel_celper, TextView categoria,CheckBox cbsoli,CheckBox cbine,CheckBox cbcomdom,CheckBox cbformbur,CheckBox cbcomping,TextView tvcurp,TextView tvrfc){

        if(validar_etp1(unapesolsi,apepat,apemat,nombre,fecnac,sexo,estciv,curp,noine,norfc,tipviv,vivanos,vivmeses,calle,no_ext,no_int,cp,colonia,colonia_ine,localidad,estado,municipio
        ,entrecalle,tel_part,tel_cel,nodepen,nolab,tododiadomsi,hora1a_dom,hora1b_dom,hora2a_dom,hora2b_dom,trabajasi,trabajano,ocupacion,otraocupa,nombre_trab,tel_trab,ingresos,periodo,tvcurp,tvrfc)){
             if(validar_etp2(unapesiaval,parentesco_aval,apelli_pataval,apelli_mataval,nombre_aval,tel_partaval,tel_celaval,calle_aval,no_extaval,cp_aval,colonia_aval,coline_aval,localidad_aval,estado_aval,municipio_aval)){
                 if(validar_etp3(unapellisi_fam,parentesco_fam,apellipat_fam,apellimat_fam,nombre_fam,tel_partfam,tel_celfam,calle_fam,no_extfam,cp_fam,colonia_fam,colinerefam,localidad_fam,estado_fam,municipio_fam)){
                     if(validar_etp4(cbsoli,cbine,cbcomdom,cbformbur,cbcomping,coincideine,categoria)) {
                         //contador1 = cargahelp.getSolicitud_id() + 1;
                         contador1 = cargahelp.getContador() + 1;
                         // c = helpendientes.CargarPendientes();
                         // contador1 = c.getCount()+1;
                         String parenaval, parenrefam, parenrefper;
                         String estadosol, estadoaval, estadorefam;
                         String munisol, muniaval, munirefam;
                         String trabajo;
                         String solicitud_id = String.valueOf(contador1);
                         String rol_id1 = "1", rol_id2 = "0";
                         String unapellido;
                         String coincideineife;
                         String tododiadom;
                         String tododiatel;
                         String unapellaval;
                         String unapellreffam;
                         String unapellsiper;
                         String Sexo;
                         String Estadociv = "";
                         String Tipoviv = "";
                         String Ocupac;
                         String peri = "";
                         String Banco, CasaCom, TarjBan, Ninguno;

                         //estadotrab = cargahelp.ObtenerDatosid(estado_trab.getText().toString(),3);
                         //munitrab = cargahelp.ObtenerDatosid(municipio_trab.getText().toString(),2);
                         parenaval = cargahelp.ObtenerDatosid(parentesco_aval.getText().toString(), 1);
                         parenrefam = cargahelp.ObtenerDatosid(parentesco_fam.getText().toString(), 1);
                         parenrefper = cargahelp.ObtenerDatosid(parentesco_per.getText().toString(), 1);
                         estadosol = cargahelp.ObtenerDatosid(estado.getText().toString(), 3);
                         estadoaval = cargahelp.ObtenerDatosid(estado_aval.getText().toString(), 3);
                         estadorefam = cargahelp.ObtenerDatosid(estado_fam.getText().toString(), 3);
                         munisol = cargahelp.ObtenerDatosid(municipio.getText().toString(), 2);
                         muniaval = cargahelp.ObtenerDatosid(municipio_aval.getText().toString(), 2);
                         munirefam = cargahelp.ObtenerDatosid(municipio_fam.getText().toString(), 2);
                         Ocupac = cargahelp.ObtenerDatosid(ocupacion.getText().toString(), 5);


                         if (unapesolsi.isChecked()) {
                             unapellido = "1";
                         } else {
                             unapellido = "0";
                         }
                         if (coincideine.isChecked()) {
                             coincideineife = "1";
                         } else {
                             coincideineife = "0";
                         }
                         if (tododiadomsi.isChecked()) {
                             tododiadom = "1";
                         } else {
                             tododiadom = "0";
                         }
                         if (tododiatelsi.isChecked()) {
                             tododiatel = "1";
                         } else {
                             tododiatel = "0";
                         }
                         if (unapesiaval.isChecked()) {
                             unapellaval = "1";
                         } else {
                             unapellaval = "0";
                         }
                         if (unapellisi_fam.isChecked()) {
                             unapellreffam = "1";
                         } else {
                             unapellreffam = "0";
                         }
                         if (unapellisi_per.isChecked()) {
                             unapellsiper = "1";
                         } else {
                             unapellsiper = "0";
                         }
                         if (coincideine.isChecked()) {
                             coincideineife = "1";
                         } else {
                             coincideineife = "0";
                         }
                         if (trabajasi.isChecked()) {
                             trabajo = "1";
                         } else {
                             trabajo = "0";
                         }
                         if (banco.isChecked()) {
                             Banco = "1";
                         } else {
                             Banco = "0";
                         }
                         if (casacom.isChecked()) {
                             CasaCom = "1";
                         } else {
                             CasaCom = "0";
                         }
                         if (tarjban.isChecked()) {
                             TarjBan = "1";
                         } else {
                             TarjBan = "0";
                         }
                         if (ninguno.isChecked()) {
                             Ninguno = "1";
                         } else {
                             Ninguno = "0";
                         }
                         if (sexo.getText().toString().equals("MUJER")) {
                             Sexo = "F";
                         } else {
                             Sexo = "M";
                         }

                         if (estciv.getText().toString().equals("CASADO(A)")) {
                             Estadociv = "C";
                         } else if (estciv.getText().toString().equals("DIVORCIADO(A)")) {
                             Estadociv = "D";
                         } else if (estciv.getText().toString().equals("SOLTERO(A)")) {
                             Estadociv = "S";
                         } else if (estciv.getText().toString().equals("UNIÓN LIBRE")) {
                             Estadociv = "U";
                         } else if (estciv.getText().toString().equals("VIUDO(A)")) {
                             Estadociv = "V";
                         }

                         if (tipviv.getText().toString().equals("PROPIA")) {
                             Tipoviv = "P";
                         } else if (tipviv.getText().toString().equals("FAMILIAR")) {
                             Tipoviv = "F";
                         } else if (tipviv.getText().toString().equals("RENTADA")) {
                             Tipoviv = "R";
                         }

                         if (periodo.getText().toString().equals("SEMANAL")) {
                             peri = "2";
                         } else if (periodo.getText().toString().equals("QUINCENAL")) {
                             peri = "1";
                         } else if (periodo.getText().toString().equals("MENSUAL")) {
                             peri = "3";
                         }


                         //insertar tabla Solicitante
                         ContentValues values = new ContentValues();
                         values.put(columns[1].toString(), solicitud_id.toString());
                         values.put(columns[2].toString(), rol_id1.toString());
                         values.put(columns[3].toString(), unapellido);
                         values.put(columns[4].toString(), apepat.getText().toString().toUpperCase());
                         values.put(columns[5].toString(), apemat.getText().toString().toUpperCase());
                         values.put(columns[6].toString(), nombre.getText().toString().toUpperCase());
                         values.put(columns[7].toString(), fecnac.getText().toString());
                         values.put(columns[8].toString(), Sexo);
                         values.put(columns[9].toString(), Estadociv);
                         values.put(columns[10].toString(), curp.getText().toString());
                         values.put(columns[11].toString(), noine.getText().toString());
                         values.put(columns[12].toString(), norfc.getText().toString());
                         //insertar tabla Solicitante

                         //insertar tabla Dom_Cliente
                         ContentValues values2 = new ContentValues();
                         values2.put(columns2[1].toString(), solicitud_id);
                         values2.put(columns2[2].toString(), rol_id2);
                         values2.put(columns2[3].toString(), Tipoviv);
                         values2.put(columns2[4].toString(), vivanos.getSelectedItem().toString());
                         values2.put(columns2[5].toString(), vivmeses.getSelectedItem().toString());
                         values2.put(columns2[6].toString(), calle.getText().toString().toUpperCase());
                         values2.put(columns2[7].toString(), no_ext.getText().toString());
                         values2.put(columns2[8].toString(), no_int.getText().toString());
                         values2.put(columns2[9].toString(), cp.getText().toString());
                         values2.put(columns2[10].toString(), colonia.getText().toString());
                         values2.put(columns2[11].toString(), colonia_ine.getText().toString());
                         values2.put(columns2[12].toString(), localidad.getText().toString().toUpperCase());
                         values2.put(columns2[13].toString(), estadosol);
                         values2.put(columns2[14].toString(), munisol);
                         values2.put(columns2[15].toString(), entrecalle.getText().toString().toUpperCase());
                         values2.put(columns2[16].toString(), tel_part.getText().toString());
                         values2.put(columns2[17].toString(), tel_cel.getText().toString());
                         values2.put(columns2[18].toString(), email.getText().toString());
                         values2.put(columns2[19].toString(), nodepen.getText().toString());
                         values2.put(columns2[20].toString(), nolab.getText().toString());
                         values2.put(columns2[21].toString(), Banco);
                         values2.put(columns2[22].toString(), CasaCom);
                         values2.put(columns2[23].toString(), TarjBan);
                         values2.put(columns2[24].toString(), Ninguno);
                         values2.put(columns2[25].toString(), coincideineife);
                         values2.put(columns2[26].toString(), tododiadom);
                         values2.put(columns2[27].toString(), hora1a_dom.getText().toString());
                         values2.put(columns2[28].toString(), hora1b_dom.getText().toString());
                         values2.put(columns2[29].toString(), hora2a_dom.getText().toString());
                         values2.put(columns2[30].toString(), hora2b_dom.getText().toString());
                         values2.put(columns2[31].toString(), tododiatel);
                         values2.put(columns2[32].toString(), hora1a_tel.getText().toString());
                         values2.put(columns2[33].toString(), hora1b_tel.getText().toString());
                         values2.put(columns2[34].toString(), hora2a_tel.getText().toString());
                         values2.put(columns2[35].toString(), hora2b_tel.getText().toString());
                         //insertar tabla Dom_Cliente

                         //insertar tabla Datos_Lab
                         ContentValues values3 = new ContentValues();
                         values3.put(columns3[1].toString(), solicitud_id);
                         values3.put(columns3[2].toString(), rol_id2);
                         values3.put(columns3[3].toString(), trabajo);
                         values3.put(columns3[4].toString(), Ocupac);
                         values3.put(columns3[5].toString(), otraocupa.getText().toString().toUpperCase());
                         values3.put(columns3[6].toString(), nombre_trab.getText().toString().toUpperCase());
                         values3.put(columns3[7].toString(), tel_trab.getText().toString());
                         values3.put(columns3[8].toString(), ingresos.getText().toString());
                         values3.put(columns3[9].toString(), peri);
                         //insertar tabla Datos_Lab

                         //insertar tabla Aval
                         ContentValues values4 = new ContentValues();
                         values4.put(columns4[1].toString(), solicitud_id);
                         values4.put(columns4[2].toString(), rol_id2);
                         values4.put(columns4[3].toString(), unapellaval);
                         values4.put(columns4[4].toString(), parenaval);
                         values4.put(columns4[5].toString(), apelli_pataval.getText().toString().toUpperCase());
                         values4.put(columns4[6].toString(), apelli_mataval.getText().toString().toUpperCase());
                         values4.put(columns4[7].toString(), nombre_aval.getText().toString().toUpperCase());
                         values4.put(columns4[8].toString(), tel_partaval.getText().toString());
                         values4.put(columns4[9].toString(), tel_celaval.getText().toString());
                         //insertar tabla Aval


                         //insertar tabla Dom_Aval
                         ContentValues values5 = new ContentValues();
                         values5.put(columns5[1].toString(), solicitud_id);
                         values5.put(columns5[2].toString(), rol_id2);
                         values5.put(columns5[3].toString(), calle_aval.getText().toString().toUpperCase());
                         values5.put(columns5[4].toString(), no_extaval.getText().toString());
                         values5.put(columns5[5].toString(), no_intaval.getText().toString());
                         values5.put(columns5[6].toString(), cp_aval.getText().toString());
                         values5.put(columns5[7].toString(), colonia_aval.getText().toString());
                         values5.put(columns5[8].toString(), coline_aval.getText().toString().toUpperCase());
                         values5.put(columns5[9].toString(), localidad_aval.getText().toString().toUpperCase());
                         values5.put(columns5[10].toString(), estadoaval);
                         values5.put(columns5[11].toString(), muniaval);
                         //insertar tabla Dom_aval


                         //insertar tabla Ref_Fam
                         ContentValues values6 = new ContentValues();
                         values6.put(columns6[1].toString(), solicitud_id);
                         values6.put(columns6[2].toString(), rol_id2);
                         values6.put(columns6[3].toString(), unapellreffam);
                         values6.put(columns6[4].toString(), parenrefam);
                         values6.put(columns6[5].toString(), apellipat_fam.getText().toString().toUpperCase());
                         values6.put(columns6[6].toString(), apellimat_fam.getText().toString().toUpperCase());
                         values6.put(columns6[7].toString(), nombre_fam.getText().toString().toUpperCase());
                         values6.put(columns6[8].toString(), tel_partfam.getText().toString());
                         values6.put(columns6[9].toString(), tel_celfam.getText().toString());
                         //insertar tabla Ref_Fam


                         //insertar tabla Dom_RefFam
                         ContentValues values7 = new ContentValues();
                         values7.put(columns7[1].toString(), solicitud_id);
                         values7.put(columns7[2].toString(), rol_id2);
                         values7.put(columns7[3].toString(), calle_fam.getText().toString().toUpperCase());
                         values7.put(columns7[4].toString(), no_extfam.getText().toString());
                         values7.put(columns7[5].toString(), no_intfam.getText().toString());
                         values7.put(columns7[6].toString(), cp_fam.getText().toString());
                         values7.put(columns7[7].toString(), colonia_fam.getText().toString());
                         values7.put(columns7[8].toString(), colinerefam.getText().toString().toUpperCase());
                         values7.put(columns7[9].toString(), localidad_fam.getText().toString().toUpperCase());
                         values7.put(columns7[10].toString(), estadorefam);
                         values7.put(columns7[11].toString(), munirefam);
                         //insertar tabla Dom_RefFam

                         //insertar tabla Ref_Per
                         ContentValues values8 = new ContentValues();
                         values8.put(columns8[1].toString(), solicitud_id);
                         values8.put(columns8[2].toString(), rol_id2);
                         values8.put(columns8[3].toString(), unapellsiper);
                         values8.put(columns8[4].toString(), parenrefper);
                         values8.put(columns8[5].toString(), apellipat_per.getText().toString().toUpperCase());
                         values8.put(columns8[6].toString(), apellimat_per.getText().toString().toUpperCase());
                         values8.put(columns8[7].toString(), nombre_per.getText().toString().toUpperCase());
                         values8.put(columns8[8].toString(), tel_partper.getText().toString());
                         values8.put(columns8[9].toString(), tel_celper.getText().toString());

                         String[] arrSplit;
                         //insertar tabla Categorias
                         categoria1 = categoria.getText().toString();
                         if(categoria1.equals("")){arrSplit = new String[]{"CELULARES"};}else{arrSplit = categoria1.split(",");}
                         //arrSplit = categoria1.split(",");
                         //for (int i = 0;i <= arrSplit.length; i++)    {
                         //    categolist.add(arrSplit[i]);
                         // }
                         ContentValues values9 = new ContentValues();
                         try {

                             long newRowid = db.insert(solicitante, null, values);
                             long newRowid2 = db.insert(dom_cliente, null, values2);
                             long newRowid3 = db.insert(datos_lab, null, values3);
                             long newRowid4 = db.insert(aval, null, values4);
                             long newRowid5 = db.insert(dom_aval, null, values5);
                             long newRowid6 = db.insert(ref_fam, null, values6);
                             long newRowid7 = db.insert(dom_reffam, null, values7);
                             long newRowid8 = db.insert(ref_per, null, values8);
                             for (int i = 0; i < arrSplit.length; i++) {
                                 String idcat = cargahelp.ObtenerDatosid(arrSplit[i], 7);
                                 values9.put(columns9[1].toString(), solicitud_id);
                                 values9.put(columns9[2].toString(), rol_id2);
                                 values9.put(columns9[3].toString(), idcat);
                                 long newRowid9 = db.insert(categorias, null, values9);
                             }

                             //AumentarContador(contador1);
                             Toast.makeText(ctx, "Registro exitoso de " +
                                     nombre.getText().toString() + " " + apepat.getText().toString(), Toast.LENGTH_LONG).show();
                             AumentarContador(contador1);
                             System.out.print("registro exitoso");
                             resp = true;
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }else{Toast.makeText(ctx, "Falta capturar documento.. ", Toast.LENGTH_LONG).show();}

                   }else{Toast.makeText(ctx, "Falta capturar dato de la Referencia fam.. ", Toast.LENGTH_LONG).show();}
                 }else{Toast.makeText(ctx, "Falta capturar dato del Aval ", Toast.LENGTH_LONG).show();}
            }else{Toast.makeText(ctx, "Falta capturar dato del solicitante ", Toast.LENGTH_LONG).show();}
            return resp;
        }

    //Obtener Datos de solicitud
    public List<String> ObtenerDatos(String solicitud_id,int identifi){
        datos = new ArrayList<>();
        switch (identifi){
            case 1:
            Tabla = solicitante;
            columna = columns;
            break;
            case 2:
                Tabla = dom_cliente;
                columna = columns2;
                break;
            case 3:
                Tabla = datos_lab;
                columna = columns3;
                break;
            case 4:
                Tabla = aval;
                columna = columns4;
                break;
            case 5:
                Tabla = dom_aval;
                columna = columns5;
                break;
            case 6:
                Tabla = ref_fam;
                columna = columns6;
                break;
            case 7:
                Tabla = dom_reffam;
                columna = columns7;
                break;
            case 8:
                Tabla = ref_per;
                columna = columns8;
                break;
        }
        String data = "solicitud_id";
        try {
            c = db.query(Tabla,columna,data+"=?",new String[]{solicitud_id},null,null,null
            );
            c.moveToFirst();
            do {
                for (i =1;i<columna.length;i++) {
                    datos.add(c.getString(i));
                }
            }while (c.moveToNext());
            c.close();
        } catch(Exception e){
            Toast.makeText(ctx, "NO se encontro la solicitud", Toast.LENGTH_LONG).show();
        }
        return datos;
    }
    public boolean validar_etp1(CheckBox unapesolsi,EditText apepat,EditText apemat,EditText nombre,EditText fecnac,EditText sexo,EditText estciv,EditText curp,EditText noine,EditText norfc,
                                EditText tipviv,Spinner vivanos,Spinner vivmeses,EditText calle,EditText no_ext,EditText no_int,EditText cp,EditText colonia,EditText colonia_ine,
                                EditText localidad,EditText estado,EditText municipio,EditText entrecalle,EditText tel_part,EditText tel_cel,EditText nodepen,EditText nolab,
                                CheckBox tododiadomsi,EditText hora1a_dom,EditText hora1b_dom,EditText hora2a_dom,EditText hora2b_dom,CheckBox trabajasi,CheckBox trabajano,EditText ocupacion,EditText otraocupa,EditText nombre_trab,
                                EditText tel_trab,EditText ingresos,EditText periodo,TextView tvcurp,TextView tvrfc){

        if(tvcurp.getText().toString().equals("El Curp no es valido")){bandera = true;}
        if(tvrfc.getText().toString().equals("El RFC no es valido")){bandera = true;}
        if (apepat.getText().toString().equals(""))bandera = true;
        if (!unapesolsi.isChecked() && apemat.getText().toString().equals("")){bandera = true;}
        //if (apemat.getText().toString().equals(""))bandera = true;
        if (nombre.getText().toString().equals(""))bandera = true;
        if (fecnac.getText().toString().equals(""))bandera = true;
        if (sexo.getText().toString().equals(""))bandera = true;
        if (estciv.getText().toString().equals(""))bandera = true;
        if (curp.getText().toString().equals(""))bandera = true;
        if (noine.getText().toString().equals(""))bandera = true;
        if (norfc.getText().toString().equals(""))bandera = true;
        if (tipviv.getText().toString().equals(""))bandera = true;
        if (calle.getText().toString().equals(""))bandera = true;
        if (no_ext.getText().toString().equals(""))bandera = true;
        //if (no_int.getText().toString().equals(""))bandera = true;
        if (cp.getText().toString().equals(""))bandera = true;
        if (colonia.getText().toString().equals(""))bandera = true;
        if (colonia.getText().toString().equals("No se encontro")&& colonia_ine.getText().toString().equals(""))bandera = true;
        //if (localidad.getText().toString().equals(""))bandera = true;
        if (estado.getText().toString().equals(""))bandera = true;
        if (municipio.getText().toString().equals(""))bandera = true;
        if (entrecalle.getText().toString().equals(""))bandera = true;
        if (tel_cel.getText().toString().equals("") && tel_part.getText().toString().equals(""))bandera = true;
        if (nodepen.getText().toString().equals(""))bandera = true;
        if (nolab.getText().toString().equals(""))bandera = true;
        if (!tododiadomsi.isChecked() && hora1a_dom.getText().toString().equals("") && hora1b_dom.getText().toString().equals("") && hora2a_dom.getText().toString().equals("") && hora2b_dom.getText().toString().equals("") )bandera = true;
        if (!trabajasi.isChecked()&& !trabajano.isChecked()){bandera = true;}
        if (trabajasi.isChecked()&& nombre_trab.getText().toString().equals("") ){bandera = true;}
        //if (trabajasi.isChecked()&& tel_trab.getText().toString().equals("") ){bandera = true;}
        if (trabajasi.isChecked()&& tel_part.getText().toString().equals("")&& tel_trab.getText().toString().equals("") ){bandera = true;}
        if (trabajasi.isChecked()&& ingresos.getText().toString().equals("") ){bandera = true;}
        if (trabajasi.isChecked()&& periodo.getText().toString().equals("") ){bandera = true;}
        if (trabajasi.isChecked()&& ocupacion.getText().toString().equals("") ){bandera = true;}
        if (ocupacion.getText().toString().equals("OTRO...")&& otraocupa.getText().toString().equals("")){bandera = true;}
        if(bandera == true){return false;}else{
            return true;
        }

    }
    public boolean validar_etp2(CheckBox unapesiaval,EditText parentesco_aval, EditText apelli_pataval, EditText apelli_mataval, EditText nombre_aval, EditText tel_partaval, EditText tel_celaval,EditText calle_aval, EditText no_extaval,
                                EditText cp_aval, EditText colonia_aval,EditText coline_aval, EditText localidad_aval, EditText estado_aval, EditText municipio_aval){
        if (parentesco_aval.getText().toString().equals(""))bandera = true;
        if (apelli_pataval.getText().toString().equals(""))bandera = true;
        if (!unapesiaval.isChecked() && apelli_mataval.getText().toString().equals("")){bandera = true;}
        //if (apelli_mataval.getText().toString().equals(""))bandera = true;
        if (nombre_aval.getText().toString().equals(""))bandera = true;
        //if (tel_partaval.getText().toString()==null)return false;
        if (tel_celaval.getText().toString().equals("")&& tel_partaval.getText().toString().equals(""))bandera = true;
        if (calle_aval.getText().toString().equals(""))bandera = true;
        if (no_extaval.getText().toString().equals(""))bandera = true;
        if (cp_aval.getText().toString().equals(""))bandera = true;
        if (colonia_aval.getText().toString().equals(""))bandera = true;
        //if (coline_aval.getText().toString().equals(""))bandera = true;
        if (colonia_aval.getText().toString().equals("No se encontro")&& coline_aval.getText().toString().equals(""))bandera = true;
        //if (localidad_aval.getText().toString()==null)return false;
        if (estado_aval.getText().toString().equals(""))bandera = true;
        if (municipio_aval.getText().toString().equals(""))bandera = true;
        if(bandera == true){return false;}else{
            return true;
        }
    }
    public boolean validar_etp3(CheckBox unapellisi_fam,EditText parentesco_fam, EditText apellipat_fam, EditText apellimat_fam, EditText nombre_fam, EditText tel_partfam, EditText tel_celfam,EditText calle_fam, EditText no_extfam,
                                EditText cp_fam, EditText colonia_fam, EditText colinerefam, EditText localidad_fam, EditText estado_fam,EditText municipio_fam){
        if (parentesco_fam.getText().toString().equals(""))bandera = true;
        if (apellipat_fam.getText().toString().equals(""))bandera = true;
        if (!unapellisi_fam.isChecked() && apellimat_fam.getText().toString().equals("")){bandera = true;}
        //if (apellimat_fam.getText().toString().equals(""))bandera = true;
        if (nombre_fam.getText().toString().equals(""))bandera = true;
        //if (tel_partfam.getText().toString()==null)return false;
        if (tel_celfam.getText().toString().equals("") && tel_partfam.getText().toString().equals(""))bandera = true;
        if (calle_fam.getText().toString().equals(""))bandera = true;
        if (no_extfam.getText().toString().equals(""))bandera = true;
        if (cp_fam.getText().toString().equals(""))bandera = true;
        if (colonia_fam.getText().toString().equals(""))bandera = true;
        //if (colinerefam.getText().toString().equals(""))bandera = true;
        if (colonia_fam.getText().toString().equals("No se encontro")&& colinerefam.getText().toString().equals(""))bandera = true;
        //if (colonia_fam.getText().toString().equals("No se encontro")&& colinerefam.getText().toString().equals(""))bandera = true;
        if (estado_fam.getText().toString().equals(""))bandera = true;
        if (municipio_fam.getText().toString().equals(""))bandera = true;
        if(bandera == true){return false;}else{
            return true;
        }
    }
    public boolean validar_etp4(CheckBox cbsoli,CheckBox cbine,CheckBox cbcomdom,CheckBox cbformbur,CheckBox coming, CheckBox coincideine,TextView categoria){
        if (!cbsoli.isChecked()){bandera = true;}
        if(!coincideine.isChecked()&&!cbcomdom.isChecked()){return true;}
        //if (!cbine.isChecked()){bandera = true;}
        if (!cbine.isChecked()){bandera = true;}
        if (!cbformbur.isChecked()){bandera = true;}
        //if (!coming.isChecked()){bandera = true;}
        //if(categoria.getText().toString().equals("")){bandera = true;}
        if(bandera == true){return false;}else{
            return true;
        }
    }
    public void AumentarContador(int dato){
        String data = String.valueOf(dato);
        String query;
        query = "UPDATE Contador SET Contador = "  + data +  " WHERE _id = "+ 1;
        db.execSQL(query);
    }


}
