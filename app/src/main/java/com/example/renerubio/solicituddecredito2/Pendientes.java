package com.example.renerubio.solicituddecredito2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import com.example.renerubio.solicituddecredito2.Entity.LoginData;
import com.example.renerubio.solicituddecredito2.Utils.Alert;
import com.example.renerubio.solicituddecredito2.Utils.RestClient;
import com.example.renerubio.solicituddecredito2.Utils.RestResponse;
import com.example.renerubio.solicituddecredito2.Utils.SessionManager;

import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class Pendientes extends AppCompatActivity {

    Dialog customDialog = null;
    private ACProgressFlower dialog;
    ListView lista;
    helper help1;
    SeguimientoHelper help2;
    CargaridHelper help3;
    List<String> item = null;
    List<String> dato2 = null;
    String[] dato;
    List<String> solicitud = null;
    List<String> dom_soli = null;
    List<String> datos_lab = null;
    List<String> aval = null;
    List<String> dom_aval = null;
    List<String> reffam = null;
    List<String> dom_refam = null;
    List<String> refper = null;
    List<String> categoriaart = null;
    List<List<String>> datosImagen = null;

    String XMLPersonas;
    String XMLDomicilios;
    String XMLCategorias;
    String XMLDocumentos;
    String xmlGuardarRequest;
    int res;
    String telefono;
    int folio,respuesta;
    String descripcionRechazo,folioEncontrado;
    int eseconomista;
    int periodopag;
    int comping;
    int coincine;
    int tododiadom;
    TextView categorias;
    String categori;
    int sol_id;
    String solicitud_id;
    private SubirSolicitud subirsol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendientes);
        lista = (ListView)findViewById(R.id.lvpendientes);
        //help5 = new CargaridHelper(this);
        //res = help5.getSolicitud_id();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                MostrarDialogoOpciones(posicion);
               // Toast.makeText(getApplicationContext(), "Solicitud enviada", Toast.LENGTH_LONG).show();
            }
        });
        MostrarPendientes();
    }
    public  void MostrarPendientes(){
        help1 = new helper(this);/*
        Cursor c = help1.CargarPendientes();
        item = new ArrayList<String>();
        String title ="";
        if(c.moveToFirst()) {
            do {
                title = c.getString(2)+" "+c.getString(3)+" "+c.getString(4)+" "+c.getString(0);
                item.add("Pendiente de: "+title);

            } while (c.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,item);
        lista.setAdapter(adapter);*/
        datosImagen = help1.CargarPendientes2();
        dato2 = new ArrayList<>();
        item = new ArrayList<String>();
        String title ="";
        for(List <String> dato2: datosImagen) {
            title = dato2.get(1)+" "+dato2.get(2)+" "+dato2.get(3)+" "+dato2.get(4);
            item.add("Pendiente de: "+title);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,item);
        lista.setAdapter(adapter);
    }
    public void MostrarDialogoOpciones(int posicion){
        //final CharSequence [] options = {"Enviar Solicitud","Eliminar","Cancelar"};
        final CharSequence [] options = {"Enviar Solicitud","Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Elige una opción");
        help1 = new helper(this);
        help2 = new SeguimientoHelper(this);
        help3 = new CargaridHelper(this);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                Cursor c = help1.CargarPendientes();
                List<String> lista = new ArrayList<>();
                if(c.moveToFirst()) {
                    do {
                        lista.add(c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3));
                    } while (c.moveToNext());

                }
                if(options[seleccion].equals("Enviar Solicitud")){
                    //EnviarSolicitud();
                    //dato = item.get(posicion).split("(?=\\s)");
                    dato = item.get(posicion).split("\\s+");

                    solicitud_id = dato[2].toString();
                    String locid = SessionManager.INSTANCE.getPreferences(Pendientes.this, "_locid");
                    String sucid = SessionManager.INSTANCE.getPreferences(Pendientes.this, "_sucid");
                    String userid = SessionManager.INSTANCE.getPreferences(Pendientes.this, "_userid");

                    subirsol = new SubirSolicitud(solicitud_id,locid,sucid,userid);
                    subirsol.execute((Void) null);

                /*} else if(options[seleccion].equals("Eliminar")){
                    //EliminarSolicitud();
                    Toast.makeText(getApplicationContext(), "Solicitud eliminada", Toast.LENGTH_LONG).show();*/
                }else if(options[seleccion].equals("Cancelar")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public class SubirSolicitud extends AsyncTask<Void, Void, Boolean> {
        String solicitud_id,sucursal_id,loc_id,user_id;
        String colonia_id,col_idaval,col_idrefam;
        String[] arra;
        int[] arra2;

        SubirSolicitud(String solicitud_id,String loc_id,String sucursal_id,String user_id) {
            this.solicitud_id = solicitud_id;
            this.loc_id = loc_id;
            this.sucursal_id = sucursal_id;
            this.user_id = user_id;
        }
        //int UserId = Integer.parseInt(user_id);


        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
           Carga();
        }


        @SuppressLint("DefaultLocale")
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean resp = false;
            RestResponse response = null;
            boolean resul = true;
            int UserId = Integer.parseInt(user_id);

            try {
                solicitud = help2.ObtenerDatos(solicitud_id,1);
                dom_soli = help2.ObtenerDatos(solicitud_id,2);
                datos_lab = help2.ObtenerDatos(solicitud_id,3);
                aval = help2.ObtenerDatos(solicitud_id,4);
                dom_aval = help2.ObtenerDatos(solicitud_id,5);
                reffam = help2.ObtenerDatos(solicitud_id,6);
                dom_refam = help2.ObtenerDatos(solicitud_id,7);
                refper = help2.ObtenerDatos(solicitud_id,8);
                categoriaart = help1.ObtenerCat(solicitud_id);
                if(dom_soli.get(9).equals("No se encontró")){colonia_id = "0";}else{colonia_id = help3.ObtenerDatosid(dom_soli.get(9),4);}
                //colonia_id = help3.ObtenerDatosid(dom_soli.get(9),4);
                if(dom_refam.get(6).equals("No se encontró")){col_idrefam = "0";}else{col_idrefam = help3.ObtenerDatosid(dom_refam.get(6),4);}
                //col_idrefam = help3.ObtenerDatosid(dom_refam.get(6),4);
                if(dom_aval.get(6).equals("No se encontró")){col_idaval = "0";}else{col_idaval = help3.ObtenerDatosid(dom_aval.get(6),4);}
                //col_idaval = help3.ObtenerDatosid(dom_aval.get(6),4);
                //sol_id = help3.getSolicitud_id();
                sol_id = Integer.parseInt(solicitud_id);

                //int parenaval = Integer.parseInt(aval.get(3));

                //region Personas

                //Persona solicitante*************************************************************+*
                    XMLPersonas = "<Personas>";
                    XMLPersonas += "<Persona>";
                    XMLPersonas += "<Rol_id>1</Rol_id>";
                    XMLPersonas += "<ApPaterno>" +solicitud.get(3)+ "</ApPaterno>";
                    XMLPersonas += "<ApMaterno>" +solicitud.get(4)+ "</ApMaterno>";
                    XMLPersonas += "<Nombre>" +solicitud.get(5)+ "</Nombre>";
                    XMLPersonas += "<Parentesco_id>0</Parentesco_id>";
                    XMLPersonas += "<TelParticular>" +dom_soli.get(15)+ "</TelParticular>";
                    XMLPersonas += "<TelCelular>" +dom_soli.get(16)+ "</TelCelular>";
                    XMLPersonas += "<VBCredito>0</VBCredito>";
                    XMLPersonas += "<Trabaja>" +datos_lab.get(2)+ "</Trabaja>";
                    XMLPersonas += "<NomEmpresa>" +datos_lab.get(5)+ "</NomEmpresa>";
                    XMLPersonas += "<EMail>" +dom_soli.get(17)+ "</EMail>";
                    XMLPersonas += "<InfComp></InfComp>";
                    XMLPersonas += "</Persona>";
                //Persona solicitante **************************************************************
                //Persona aval  ********************************************************************
                    XMLPersonas += "<Persona>";
                    XMLPersonas += "<Rol_id>2</Rol_id>";
                    XMLPersonas += "<ApPaterno>" +aval.get(4)+ "</ApPaterno>";
                    XMLPersonas += "<ApMaterno>" +aval.get(5)+ "</ApMaterno>";
                    XMLPersonas += "<Nombre>" +aval.get(6)+ "</Nombre>";
                    XMLPersonas += "<Parentesco_id>" +aval.get(3)+ "</Parentesco_id>";
                    XMLPersonas += "<TelParticular>" +aval.get(7)+ "</TelParticular>";
                    XMLPersonas += "<TelCelular>" +aval.get(8)+ "</TelCelular>";
                    XMLPersonas += "<VBCredito>0</VBCredito>";
                    XMLPersonas += "<Trabaja>0</Trabaja>";
                    XMLPersonas += "<NomEmpresa></NomEmpresa>";
                    XMLPersonas += "<EMail></EMail>";
                    XMLPersonas += "<InfComp></InfComp>";
                    XMLPersonas += "</Persona>";
                //Persona aval  ********************************************************************
                //Referencia Familiar
                    XMLPersonas += "<Persona>";
                    XMLPersonas += "<Rol_id>3</Rol_id>";
                    XMLPersonas += "<ApPaterno>" +reffam.get(4)+ "</ApPaterno>";
                    XMLPersonas += "<ApMaterno>" +reffam.get(5)+ "</ApMaterno>";
                    XMLPersonas += "<Nombre>" +reffam.get(6)+"</Nombre>";
                    XMLPersonas += "<Parentesco_id>" +reffam.get(3)+ "</Parentesco_id>";
                    XMLPersonas += "<TelParticular>" +reffam.get(7)+ "</TelParticular>";
                    XMLPersonas += "<TelCelular>" +reffam.get(8)+ "</TelCelular>";
                    XMLPersonas += "<VBCredito>0</VBCredito>";
                    XMLPersonas += "<Trabaja>0</Trabaja>";
                    XMLPersonas += "<NomEmpresa></NomEmpresa>";
                    XMLPersonas += "<EMail></EMail>";
                    XMLPersonas += "<InfComp></InfComp>";
                    XMLPersonas += "</Persona>";

                //Referencia Personal
                    XMLPersonas += "<Persona>";
                    XMLPersonas += "<Rol_id>4</Rol_id>";
                    XMLPersonas += "<ApPaterno>" +refper.get(4)+ "</ApPaterno>";
                    XMLPersonas += "<ApMaterno>" +refper.get(5)+ "</ApMaterno>";
                    XMLPersonas += "<Nombre>" +refper.get(6)+ "</Nombre>";
                    XMLPersonas += "<Parentesco_id>" +refper.get(3)+ "</Parentesco_id>";
                    XMLPersonas += "<TelParticular>" +refper.get(7)+ "</TelParticular>";
                    XMLPersonas += "<TelCelular>" +refper.get(8)+ "</TelCelular>";
                    XMLPersonas += "<VBCredito>0</VBCredito>";
                    XMLPersonas += "<Trabaja>0</Trabaja>";
                    XMLPersonas += "<NomEmpresa></NomEmpresa>";
                    XMLPersonas += "<EMail></EMail>";
                    XMLPersonas += "<InfComp></InfComp>";
                    XMLPersonas += "</Persona>";
                //endregion

                int estidsol = Integer.parseInt(dom_soli.get(12));
                int munidsol = Integer.parseInt(dom_soli.get(13));
                int colidsol = Integer.parseInt(colonia_id);
                //Domicilio Cliente
                //region Description
                XMLDomicilios = "<Domicilios>";
                XMLDomicilios += "<Domicilio>";
                XMLDomicilios += "<Rol_id>1</Rol_id>";
                XMLDomicilios += "<TipoDom_id>1</TipoDom_id>";
                XMLDomicilios += "<Estado_id>" + estidsol + "</Estado_id>";
                XMLDomicilios += "<Municipio_id>" + munidsol + "</Municipio_id>";
                XMLDomicilios += "<Localidad_id>0</Localidad_id>";
                XMLDomicilios += "<Colonia_id>" + colidsol + "</Colonia_id>";
                XMLDomicilios += "<Calle>" + dom_soli.get(5) + "</Calle>";
                XMLDomicilios += "<NumCasa>" + dom_soli.get(6) + "</NumCasa>";
                XMLDomicilios += "<NumInt>" + dom_soli.get(7) + "</NumInt>";
                XMLDomicilios += "<CP>" + dom_soli.get(8) + "</CP>";
                XMLDomicilios += "<EntreCalles>" + dom_soli.get(14) + "</EntreCalles>";
                XMLDomicilios += "<Coordenadas>0</Coordenadas>";
                XMLDomicilios += "<Localidad>" + dom_soli.get(11) + "</Localidad>";
                XMLDomicilios += "<Colonia>" + dom_soli.get(9) + "</Colonia>";
                XMLDomicilios += "</Domicilio>";

                int estidav = Integer.parseInt(dom_aval.get(9));
                int munidav = Integer.parseInt(dom_aval.get(10));
                int colidav = Integer.parseInt(col_idaval);
                //Domicilio Aval
                //XMLDomicilios += "<Domicilios>";
                XMLDomicilios += "<Domicilio>";
                XMLDomicilios += "<Rol_id>2</Rol_id>";
                XMLDomicilios += "<TipoDom_id>1</TipoDom_id>";
                XMLDomicilios += "<Estado_id>" +estidav+ "</Estado_id>";
                XMLDomicilios += "<Municipio_id>" +munidav+"</Municipio_id>";
                XMLDomicilios += "<Localidad_id>0</Localidad_id>";
                XMLDomicilios += "<Colonia_id>" + colidav + "</Colonia_id>";
                XMLDomicilios += "<Calle>" + dom_aval.get(2) + "</Calle>";
                XMLDomicilios += "<NumCasa>" + dom_aval.get(3) + "</NumCasa>";
                XMLDomicilios += "<NumInt>" + dom_aval.get(4) + "</NumInt>";
                XMLDomicilios += "<CP>" + dom_aval.get(5) + "</CP>";
                XMLDomicilios += "<EntreCalles></EntreCalles>";
                XMLDomicilios += "<Coordenadas>0</Coordenadas>";
                XMLDomicilios += "<Localidad>" + dom_aval.get(8) + "</Localidad>";
                XMLDomicilios += "<Colonia>" + dom_aval.get(6) + "</Colonia>";
                XMLDomicilios += "</Domicilio>";

                int estidref = Integer.parseInt(dom_refam.get(9));
                int munidref = Integer.parseInt(dom_refam.get(10));
                int colidref = Integer.parseInt(col_idrefam);
                //Domicilio Refam
                //XMLDomicilios += "<Domicilios>";
                XMLDomicilios += "<Domicilio>";
                XMLDomicilios += "<Rol_id>3</Rol_id>";
                XMLDomicilios += "<TipoDom_id>1</TipoDom_id>";
                XMLDomicilios += "<Estado_id>" +estidref+ "</Estado_id>";
                XMLDomicilios += "<Municipio_id>" +munidref+"</Municipio_id>";
                XMLDomicilios += "<Localidad_id>0</Localidad_id>";
                XMLDomicilios += "<Colonia_id>" + colidref + "</Colonia_id>";
                XMLDomicilios += "<Calle>" + dom_refam.get(2) + "</Calle>";
                XMLDomicilios += "<NumCasa>" + dom_refam.get(3) + "</NumCasa>";
                XMLDomicilios += "<NumInt>" + dom_refam.get(4) + "</NumInt>";
                XMLDomicilios += "<CP>" + dom_refam.get(5) + "</CP>";
                XMLDomicilios += "<EntreCalles></EntreCalles>";
                XMLDomicilios += "<Coordenadas>0</Coordenadas>";
                XMLDomicilios += "<Localidad>" + dom_refam.get(8) + "</Localidad>";
                XMLDomicilios += "<Colonia>" + dom_refam.get(6) + "</Colonia>";
                XMLDomicilios += "</Domicilio>";

                XMLCategorias = "<Categorias>";
                for(String dato3: categoriaart) {
                        int y = Integer.parseInt(dato3);
                    XMLCategorias += "<Categoria>";
                    XMLCategorias += "<Categoria_id>" + y + "</Categoria_id>";
                    XMLCategorias += "</Categoria>";
                }


                dato2 = new ArrayList<>();
                datosImagen = help3.getImagenesurl(sol_id);
                XMLDocumentos = "";
                XMLDocumentos = "<Documentos>";
                for(List <String> dato2: datosImagen) {
                    for(int i=0;i<dato2.size()-1;i++) {
                        int x = Integer.parseInt(dato2.get(0));

                        XMLDocumentos += "<Documento>";
                        XMLDocumentos += "<Rol_id>1</Rol_id>";
                        XMLDocumentos += "<Id>" + x + "</Id>";
                        XMLDocumentos += "<Url>" + dato2.get(1) + "</Url>";
                        XMLDocumentos += "<Estatus>0</Estatus>";
                        XMLDocumentos += "</Documento>";
                    }
                }
                XMLDocumentos += "</Documentos>";
                XMLPersonas += "</Personas>";
                XMLDomicilios += "</Domicilios>";
                XMLCategorias += "</Categorias>";
                //Obtener fecha acctual
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR, 00);
                calendar.set(Calendar.MINUTE, 00);
                calendar.set(Calendar.SECOND, 00);
                String fechain = simpleDateFormat.format(calendar.getTime());
                fechain = fechain + "T00:00:00";

                //año y mes
                //int year = calendar.get(Calendar.YEAR);
                //int mont = calendar.get(Calendar.MONTH);

                String fec = solicitud.get(6).toString();
                String fecnac = fec.replace('/','-');
                fecnac = fecnac + "T00:00:00";

                periodopag = Integer.parseInt(datos_lab.get(8));
                coincine = Integer.parseInt(dom_soli.get(24));
                tododiadom = Integer.parseInt(dom_soli.get(25));
                int banco = Integer.parseInt(dom_soli.get(20));
                int casacom = Integer.parseInt(dom_soli.get(21));
                int tarjban = Integer.parseInt(dom_soli.get(22));
                int tododiatel = Integer.parseInt(dom_soli.get(30));

                JSONObject data = new JSONObject();
                int Solic_id = 0;
                //endregion
                //region Description

                JSONArray jsonDatosSoli = new JSONArray();
                JSONObject jsonDatos = new JSONObject();

                jsonDatos.put("Solicitud_id",Solic_id);
                jsonDatos.put("SucursalId",sucursal_id);
                jsonDatos.put("PromotorId",user_id);
                jsonDatos.put("FechaNac",fecnac);
                jsonDatos.put("Sexo",solicitud.get(7));
                jsonDatos.put("EstadoCivil",solicitud.get(8));
                jsonDatos.put("TipoVivienda",dom_soli.get(2));
                jsonDatos.put("TiempoVivirAnios",dom_soli.get(3));
                jsonDatos.put("TiempoVivirMeses",dom_soli.get(4));
                jsonDatos.put("FlgOtrosIngresos",0);
                jsonDatos.put("OtrosIngresos","0");
                jsonDatos.put("NumPerLaboran",dom_soli.get(19));
                jsonDatos.put("NumPerDependen",dom_soli.get(18));
                jsonDatos.put("CreditoBanco",banco);
                jsonDatos.put("CreditoCasaComer",casacom);
                jsonDatos.put("CreditoTarjeta",tarjban);
                jsonDatos.put("Hora1aVerif",dom_soli.get(26));
                jsonDatos.put("Hora1bVerif",dom_soli.get(27));
                jsonDatos.put("Hora2aVerif",dom_soli.get(28));
                jsonDatos.put("Hora2bVerif",dom_soli.get(29));
                jsonDatos.put("OtrosIngresosPeriodo",0);
                jsonDatos.put("Ocupacion",datos_lab.get(3));
                jsonDatos.put("EsComisionista",0);
                jsonDatos.put("Puesto","");
                jsonDatos.put("Sueldo",datos_lab.get(7));
                jsonDatos.put("PeriodoPago",datos_lab.get(8));
                jsonDatos.put("XMLCategorias",XMLCategorias);
                jsonDatos.put("XMLDomicilios",XMLDomicilios);
                jsonDatos.put("XMLPersonas",XMLPersonas);
                jsonDatos.put("XMLDocumentos",XMLDocumentos);
                jsonDatos.put("Usuario_id",UserId);
                jsonDatos.put("OtraOcupacion",datos_lab.get(4));
                jsonDatos.put("TiempoTrabAnios","0");
                jsonDatos.put("TiempoTrabMeses","0");
                jsonDatos.put("TododiaDom",tododiadom);
                jsonDatos.put("Curp",solicitud.get(9));
                jsonDatos.put("Aval",0);
                jsonDatos.put("TelTrabajo",datos_lab.get(6));
                jsonDatos.put("CompIngresos",0);
                jsonDatos.put("FechaInicio",fechain);
                jsonDatos.put("CoincideDomIfe",coincine);
                jsonDatos.put("TipoOrigen",1);
                jsonDatos.put("rfc",solicitud.get(11));
                jsonDatos.put("ine",solicitud.get(10));
                jsonDatos.put("TododiaTel",tododiatel);
                jsonDatos.put("Hora1aTel",dom_soli.get(31));
                jsonDatos.put("Hora1bTel",dom_soli.get(32));
                jsonDatos.put("Hora2aTel",dom_soli.get(33));
                jsonDatos.put("Hora2bTel",dom_soli.get(34));

                jsonDatosSoli.put(jsonDatos);
                data.put("nuevaSolicitud", jsonDatosSoli);
                Log.e("REST", data.toString());
                Log.d("REST", data.toString());

                JSONArray jsonfotos = new JSONArray();
                    String ruta_fotos = String.format("%s/solicituddecredito2/%d/", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), sol_id);
                    File dirFoto = new File(ruta_fotos);
                    if (dirFoto.exists()) {
                        for (File file : dirFoto.listFiles()) {
                            if (file.getPath().endsWith(".jpg")||file.getPath().endsWith(".pdf")) {
                                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                                byte[] buffer = new byte[(int) file.length()];
                                in.read(buffer, 0, (int) file.length());
                                in.close();
                                JSONObject jsonfoto = new JSONObject();
                                jsonfoto.put("nombre", file.getName());
                                jsonfoto.put("data", new String(Base64.encode(buffer, Base64.DEFAULT)));
                                jsonfotos.put(jsonfoto);
                            }
                        }
                    }
                data.put("fotos", jsonfotos);

                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");

                response = RestClient.INSTANCE.consumeService(getApplicationContext(), getString(R.string.ip)+ "subirsolicitud", headers, "POST", data.toString());

                if (response != null) {
                    try {
                        if (response.getHttpStatus() == 202 || response.getHttpStatus() == 200) {
                            String jsonn = new String(response.getContent());
                            //JSONObject jsonResponse = new JSONObject(new String(response.getContent()));
                            JSONObject jsonResponse = new JSONObject(jsonn);
                            JSONArray jsonRespuesta = jsonResponse.getJSONArray("Solicitud");
                            JSONObject json = jsonRespuesta.getJSONObject(0);
                            folio = json.getInt("folio");
                            respuesta = json.getInt("respuesta");
                            resp = true;

                        } else {
                            resp = false;
                            //Alert.INSTANCE.AlertMessage(getApplicationContext(), "No se pudo subir la informacion algun dato esta mal");
                            //Toast.makeText(Pendientes.this, "No se pudo subir la información " + response.getHttpStatus(),Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        int ddd = 0;
                        mostrar(5,ddd);
                        //Alert.INSTANCE.AlertMessage(getApplicationContext(), "No se pudo subir la informacion");
                        //Toast.makeText(Pendientes.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

                //Log.e("Fin", "se acabo");
            } catch (Exception e) {
                Log.e("Excepcion", e.getMessage());
                //Alert.INSTANCE.AlertMessage(Pendientes.this, "No se pudo obtener respuesta");
                //Toast.makeText(Pendientes.this, "No se pudo obtener respuesta",Toast.LENGTH_LONG).show();
            }
            //return response;
            return resp;
        }
        @Override
        protected void onPostExecute(final Boolean resp) {
            subirsol = null;
            //Carga();
            if (resp) {
                if(respuesta == 5){
                    mostrar(1,folio);
                }else if(respuesta == 4){
                    mostrar(2,folio);
                }else if (respuesta == 7){
                    mostrar(3,folio);
                }else{
                    mostrar(4,folio);
                }
                /*
                switch (respuesta){
                    case 5:
                        //Toast.makeText(Pendientes.this, "Solicitud se guardó con ocupación de riesgo: ",Toast.LENGTH_LONG).show();
                        mostrar(1);
                        break;
                    case 4:
                        //Toast.makeText(Pendientes.this, "Solicitud se ha rechazado por duplicidad: ",Toast.LENGTH_LONG).show();
                        mostrar(2);
                        break;
                    case 7:
                        //Toast.makeText(Pendientes.this, "Solicitud se ha rechazado (!cónyuge cuenta con crédito¡): ",Toast.LENGTH_LONG).show();
                        mostrar(3);
                        break;
                        default:
                            Toast.makeText(Pendientes.this, "Se guardé la solicitud con folio: "+ folio,Toast.LENGTH_SHORT).show();
                            break;
                }
                help3.EliminarSolicitud(solicitud_id);
                int solicitudid = Integer.parseInt(solicitud_id);
                deletePicturesFiles(solicitudid);

                Intent refresh = new Intent(getApplicationContext(),Pendientes.class);
                startActivity(refresh);//Start the same Activity
                finish();*/
            } else {
                dialog.dismiss();
                Toast.makeText(Pendientes.this, "No se pudo obtener respuesta",Toast.LENGTH_SHORT).show();
                //Intent refresh = new Intent(getApplicationContext(),Pendientes.class);
                //startActivity(refresh);//Start the same Activity
                //finish();
            }
        }
        @Override
        protected void onCancelled() {
            subirsol = null;
        }
        /*
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Carga();
        }*/
    }
    public void Carga(){
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Un momento")
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
    }
    private void deleteFileRecursively(String path) {
        File dirBase = new File(path);
        for (File file : dirBase.listFiles()) {
            if (file.isDirectory()) {
                deleteFileRecursively(file.getAbsolutePath());
            } else {
                file.delete();
            }
        }
        dirBase.delete();
    }
    private void deletePicturesFiles(int id_seguimiento) {
        final String ruta = String.format("%s/solicituddecredito2/%d/", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), id_seguimiento);
        deleteFileRecursively(ruta);
    }
    public void mostrar(int dato,int folio)
    {
        // con este tema personalizado evitamos los bordes por defecto
        customDialog = new Dialog(this,R.style.Theme_Dialog_Translucent);
        //deshabilitamos el título por defecto
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //obligamos al usuario a pulsar los botones para cerrarlo
        customDialog.setCancelable(false);
        //establecemos el contenido de nuestro dialog
        customDialog.setContentView(R.layout.dialog);

        TextView titulo = (TextView) customDialog.findViewById(R.id.titulo);
        titulo.setText("Valdez Baluarte");

        TextView contenido = (TextView) customDialog.findViewById(R.id.contenido);
        if(dato == 1){
            contenido.setText(R.string.Mensajeriesgo);
        }else if(dato == 2){
            contenido.setText(R.string.Mensajeduplicidad);
        }else if(dato == 3){
            contenido.setText(R.string.Mensajeconyuge);
        }else if (dato == 5){
            contenido.setText("Error al guardar la solicitud");
        }else{
            contenido.setText("Se envió solicitud "+folio+" con éxito");
        }


        ((Button) customDialog.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                customDialog.dismiss();
                help3.EliminarSolicitud(solicitud_id);
                int solicitudid = Integer.parseInt(solicitud_id);
                deletePicturesFiles(solicitudid);
                Intent refresh1 = new Intent(getApplicationContext(),Pendientes.class);
                startActivity(refresh1);//Start the same Activity
                finish();

                Intent refresh = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(refresh);//Start the same Activity
                finish();


            }
        });

        customDialog.show();
    }


}
