package com.example.renerubio.solicituddecredito2;
import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.renerubio.solicituddecredito2.Entity.Datos;
import com.example.renerubio.solicituddecredito2.Model.Categoria;
import com.example.renerubio.solicituddecredito2.Model.Colonia;
import com.example.renerubio.solicituddecredito2.Model.Estado;
import com.example.renerubio.solicituddecredito2.Model.Localidad;
import com.example.renerubio.solicituddecredito2.Model.Municipio;
import com.example.renerubio.solicituddecredito2.Model.Ocupacion;
import com.example.renerubio.solicituddecredito2.Model.Parentesco;
import com.example.renerubio.solicituddecredito2.Utils.Moneda;
import com.example.renerubio.solicituddecredito2.Utils.SessionManager;
import com.example.renerubio.solicituddecredito2.Utils.SingletonVolley;
import com.example.renerubio.solicituddecredito2.Utils.ValidaCampos;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.lang.String;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private static final String TAG = "MainActivity";
    //**********************************************************************************************************************************************************+++++
    private static final int REQUEST_CODE_GALLERY = 1;
    private static final int WRITE_STORAGE_PERMISSION = 2;
    public static final String BARRA = "/";
    String mRutaImagenSeleccionada;
    int ultimoano = 0;
    int ultimomes = 0;
    int ultimodia =0;

    Uri mUri;
    //**********************************************************************************************************************************************************+++++

    ArrayList<Colonia> ListaColonias;
    ArrayList<Localidad> ListaLocalidades;
    ArrayList<Categoria> ListaCategorias;
    ArrayList<Estado> ListaEstados;
    ArrayList<Municipio> ListaMunicipios;
    ArrayList<Parentesco> ListaParentescos;
    ArrayList<Ocupacion> ListaOcupaciones;
    private ACProgressFlower dialog;
    private SincronizarDatos Sincronizacion;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private int dia, mes, ano,mes2,ano2;
    private String APP_DIRECTORY = "myPictureApp/", MEDIA_DIRECTORY = APP_DIRECTORY + "media", TEMPORAL_PICTURE_NAME = "temporal.jpg";
    private final int PHOTO_CODE = 100, SELECT_PICTURE = 200;
    TextView tvCategoria, tvEmail, tvEntrecall, tvCalle, tvNoExt, tvHortamb, tvTelTamb, tvRfc, tvCurp;
    Button bn, bnGuardar;
    Spinner spmeses, spaños;
    ImageButton ImbAyuda;
    Bundle miblundle;
    MenuItem itam;
    EditText nombre, apellidopa, apellidoma, apellidomapca, apellidomareffa, apellidomarefper, etColoniaIne, etColIneAval, etColIneRefam;
    EditText Calendario1, etHorariotel1, etHorariotel2, etHorariotel3, etHorariotel4;
    EditText sexo, etentavaladi, etmunavaladi;
    EditText EstadoC, etIngresosT, etCurp, etRfc;
    EditText TipoVi, etusuario, etcolonia, etCalle, etNumExt, etNumInt, etLocalidad, etEntCalles, emailValidate, etDepeneco, etperslab;
    EditText etHora1, etTelTra, etapepatpca, etnombreaval, ettelpartaval;
    EditText etHora2, ettelcelaval, etcalleaval, etnumextaval, etnumintaval, cpaval, etcoloniaaval, etlocaval, etapepatrefam, etapematrefam;
    EditText etHora3, etnombrereffa, ettelpartrefam, ettelcelrefam, callerefam, etnumextrefam, etnumintrefam, etcprefam, etcolrefam, etlocrefam;
    EditText etHora4, etapepatrefper, etapematrefper, etnombrerefper, tel_partrefper, tel_celrefper, nomtrab_refper;
    EditText entidadfed, etife, otraocupa;
    EditText delmun, etocupacion;
    //EditText etcoloniaTra;
    //EditText etCalleTrab;
    //EditText etNumExtTra;
    //EditText etNumIntTra;
    //EditText etCpTra;
    //EditText etLocalidTra;
    //CheckBox cbOtroIngresoSi;
    //CheckBox cbOtroIngNo;
    //CheckBox cbComisionistaSi;
    //CheckBox cbComisionistaNo;
    //CheckBox cbCompIngSi;
    //CheckBox cbCompIngNo;
    //EditText delmuntra;
    //EditText etotroing;
    //EditText etpertraotroing;
    //EditText etloctra;
    //EditText etentidadtra;
    //Spinner spmeses2;
    //Spinner spaños2;
    //EditText etnacionalidad;
    EditText etperiodo;
    EditText ettrabaja;
    EditText etparentesco;
    EditText entidadfedadi;
    EditText delmunadi;
    EditText etparentescoreffa;
    EditText etparentescorefper;
    EditText etsolicitud;
    EditText etifeine;
    EditText etcomdom;
    EditText etcoming;
    EditText etformbur, ettelcel, ettelpart, etcp, etcenttrab;
    CheckBox cbHorarioo, cbTrabajasi, cbTrabajano, cbBanco, cbCasaCom, cbTarjBan, cbNinguno,
            cbunapesi, cbHorarioT, cbavalunapesi,
            cbRefFamUnApeSi, cbRefFamUnApeNo, cbRefPerUnApeSi,
            cbSolicitud, cbIfeIne, cbComDom, cbComIng, cbFormBur, cbHorNo, cbHorarioTno, cbCoincideine;
    //AutoCompleteTextView textViewac;
    //agregado
    String file;
    String file2;
    int opcion, contador1;
    Cursor c;
    NavigationView navigationView;
    CargaridHelper help1;
    boolean bandera = false;

    //agregado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ListaColonias = new ArrayList<Colonia>();
        ListaCategorias = new ArrayList<Categoria>();
        ListaEstados = new ArrayList<Estado>();
        ListaLocalidades = new ArrayList<Localidad>();
        ListaMunicipios = new ArrayList<Municipio>();
        ListaParentescos = new ArrayList<Parentesco>();
        ListaOcupaciones = new ArrayList<Ocupacion>();
        //help1 = new CargaridHelper(this); --comente rene 06-02-2023
        //contar pendientes*******************************************************************************************************************************
        //helper helpendientes = new helper(this); --comente rene 06-02-2023
        //c = helpendientes.CargarPendientes();
        //contador1 = c.getCount();
        //contador1 = helpendientes.NumeroPendientes(); --comente rene 06-02-2023
        contador1 = 2;
        Menu nv = navigationView.getMenu();
        itam = (MenuItem) nv.findItem(R.id.solPen);
        itam.setTitle("Solicitudes Pendientes" + "  " + contador1);
        //contar pendientes *****************************************************************************************************************************
        tvCurp = (TextView) findViewById(R.id.tvCurp);
        etocupacion = (EditText) findViewById(R.id.etOcupacion);
        etColIneRefam = (EditText) findViewById(R.id.etColinerefam);
        etColIneAval = (EditText) findViewById(R.id.etColineaval);
        etColoniaIne = (EditText) findViewById(R.id.etColoniaIne);
        cbCoincideine = (CheckBox) findViewById(R.id.cbCoincide);
        etife = (EditText) findViewById(R.id.etNoIfeIne);
        otraocupa = (EditText) findViewById(R.id.etExtra);
        etusuario = (EditText) findViewById(R.id.etUsuario);
        etcolonia = (EditText) findViewById(R.id.etColonia);
        etCalle = (EditText) findViewById(R.id.etCalle);
        etNumExt = (EditText) findViewById(R.id.etNumeroExt);
        etNumInt = (EditText) findViewById(R.id.etNumeroInt);
        etLocalidad = (EditText) findViewById(R.id.etLocSinEji);
        etEntCalles = (EditText) findViewById(R.id.etEntreCalles);
        etDepeneco = (EditText) findViewById(R.id.etDependientes);
        etperslab = (EditText) findViewById(R.id.etPersonasLaboran);
        //etCalleTrab = (EditText)findViewById(R.id.etCalleTra);
        //etNumExtTra = (EditText)findViewById(R.id.etNumeroExtTra);
        //etNumIntTra = (EditText)findViewById(R.id.etNumeroIntTra);
        //etCpTra = (EditText)findViewById(R.id.etCpTra);
        //etcoloniaTra = (EditText)findViewById(R.id.etColoniaTra);
        //etLocalidTra = (EditText)findViewById(R.id.etLocSinEjiTra);
        etapepatpca = (EditText) findViewById(R.id.etApellidoPaternopca);
        etnombreaval = (EditText) findViewById(R.id.etNombrepca);
        ettelpartaval = (EditText) findViewById(R.id.etTelPartpca);
        ettelcelaval = (EditText) findViewById(R.id.etCelularpca);
        etnumextaval = (EditText) findViewById(R.id.etNumExtAval);
        etnumintaval = (EditText) findViewById(R.id.etNumIntAvalAdi);
        etcalleaval = (EditText) findViewById(R.id.etCalleAvalAdi);
        etapepatrefam = (EditText) findViewById(R.id.etApellidoPaternoreffa);
        etapematrefam = (EditText) findViewById(R.id.etApellidomaternoreffa);
        cpaval = (EditText) findViewById(R.id.etCpAvalAdi);
        etcoloniaaval = (EditText) findViewById(R.id.etColoniaAvalAdi);
        etTelTra = (EditText) findViewById(R.id.etTelTrab);
        etlocaval = (EditText) findViewById(R.id.etLocSinEjiAvalAdi);
        etnombrereffa = (EditText) findViewById(R.id.etNombrereffa);
        ettelpartrefam = (EditText) findViewById(R.id.etTelPartreffa);
        ettelcelrefam = (EditText) findViewById(R.id.etCelularreffa);
        callerefam = (EditText) findViewById(R.id.etCalleRefAdi);
        etnumextrefam = (EditText) findViewById(R.id.etNumExtRefAdi);
        etnumintrefam = (EditText) findViewById(R.id.etNumIntRefAdi);
        etcprefam = (EditText) findViewById(R.id.etCpRefAdi);
        etcolrefam = (EditText) findViewById(R.id.etColoniaRefadi);
        etlocrefam = (EditText) findViewById(R.id.etLocSinEjirefadi);
        etapepatrefper = (EditText) findViewById(R.id.etApellidoPaternoRefPer);
        etapematrefper = (EditText) findViewById(R.id.etApellidomaternoRefPer);
        tel_partrefper = (EditText) findViewById(R.id.etTelPartRefPer);
        tel_celrefper = (EditText) findViewById(R.id.etCelularRefPer);
        sexo = (EditText) findViewById(R.id.etSexo);
        EstadoC = (EditText) findViewById(R.id.etEstado);
        TipoVi = (EditText) findViewById(R.id.etTipoVivienda);
        etHora1 = (EditText) findViewById(R.id.etHorario1);
        etHora2 = (EditText) findViewById(R.id.etHorario2);
        etHora3 = (EditText) findViewById(R.id.etHorario3);
        etHora4 = (EditText) findViewById(R.id.etHorario4);
        etHorariotel1 = (EditText) findViewById(R.id.etHorarioTel1);
        etHorariotel2 = (EditText) findViewById(R.id.etHorarioTel2);
        etHorariotel3 = (EditText) findViewById(R.id.etHorarioTel3);
        etHorariotel4 = (EditText) findViewById(R.id.etHorarioTel4);
        cbHorarioo = (CheckBox) findViewById(R.id.cbHorario);
        cbHorarioT = (CheckBox) findViewById(R.id.cbHorariot);
        //cbOtroIngresoSi=(CheckBox) findViewById(R.id.cbOtroIngSi);
        //cbOtroIngNo=(CheckBox) findViewById(R.id.cbOtroIngNo);
        //cbComisionistaSi=(CheckBox) findViewById(R.id.cbComicionistasi);
        //cbComisionistaNo=(CheckBox) findViewById(R.id.cbComicionistano);
        //cbCompIngSi=(CheckBox) findViewById(R.id.cbCompIngSi);
        //cbCompIngNo=(CheckBox) findViewById(R.id.cbCompIngNo);
        cbHorNo = (CheckBox) findViewById(R.id.cbHorarioNo);
        cbunapesi = (CheckBox) findViewById(R.id.cbUnApeSi);
        cbavalunapesi = (CheckBox) findViewById(R.id.cbAvalUnApeSi);
        cbRefFamUnApeSi = (CheckBox) findViewById(R.id.cbRefFamUnApeSi);
        cbRefPerUnApeSi = (CheckBox) findViewById(R.id.cbRefPerUnApeSi);
        cbHorarioTno = (CheckBox) findViewById(R.id.cbHorariotno);
        cbSolicitud = (CheckBox) findViewById(R.id.cbSolicitud);
        cbIfeIne = (CheckBox) findViewById(R.id.cbIfeIne);
        cbComDom = (CheckBox) findViewById(R.id.cbCompDom);
        cbComIng = (CheckBox) findViewById(R.id.cbCompIng);
        cbFormBur = (CheckBox) findViewById(R.id.cbFormBuro);
        cbTrabajasi = (CheckBox) findViewById(R.id.cbTrabajasi);
        cbTrabajano = (CheckBox) findViewById(R.id.cbTrabajano);
        cbBanco = (CheckBox) findViewById(R.id.cbBanco);
        cbCasaCom = (CheckBox) findViewById(R.id.cbCasaCom);
        cbTarjBan = (CheckBox) findViewById(R.id.cbTarjBanc);
        cbNinguno = (CheckBox) findViewById(R.id.cbNinguno);
        tvCategoria = (TextView) findViewById(R.id.tvCategorias);
        tvHortamb = (TextView) findViewById(R.id.tvHorTambien);
        tvTelTamb = (TextView) findViewById(R.id.tvTelTamb);
        entidadfed = (EditText) findViewById(R.id.etEntidad);
        etentavaladi = (EditText) findViewById(R.id.etEntAvalAdi);
        delmun = (EditText) findViewById(R.id.etMunicipio);
        etmunavaladi = (EditText) findViewById(R.id.etMunAvalAdi);
        etperiodo = (EditText) findViewById(R.id.etPeriodo);
        etIngresosT = (EditText) findViewById(R.id.etIngresosTot);
        etparentesco = (EditText) findViewById(R.id.etParentesco);
        //etotroing=(EditText)findViewById(R.id.etOtroIng);
        //etpertraotroing=(EditText)findViewById(R.id.etPeriodoTraOtroing);
        entidadfedadi = (EditText) findViewById(R.id.etEntidadAdi);
        etparentescorefper = (EditText) findViewById(R.id.etParentescorefper);
        etparentescoreffa = (EditText) findViewById(R.id.etParentescoreffam);
        etsolicitud = (EditText) findViewById(R.id.etSoli);
        etifeine = (EditText) findViewById(R.id.etIfeIne);
        etcomdom = (EditText) findViewById(R.id.etComDom);
        etcoming = (EditText) findViewById(R.id.etComIng);
        etformbur = (EditText) findViewById(R.id.etForBur);
        //etnacionalidad=(EditText)findViewById(R.id.etNacionalidad);
        //etloctra=(EditText)findViewById(R.id.etTipoLocTra);
        nombre = (EditText) findViewById(R.id.etNombre);
        apellidopa = (EditText) findViewById(R.id.etApellidoPaterno);
        apellidoma = (EditText) findViewById(R.id.etApellidoMaterno);
        apellidomapca = (EditText) findViewById(R.id.etApellidomaternopca);
        apellidomareffa = (EditText) findViewById(R.id.etApellidomaternoreffa);
        apellidomarefper = (EditText) findViewById(R.id.etApellidomaternoRefPer);
        etnombrerefper = (EditText) findViewById(R.id.etNombreRefPer);
        //etentidadtra=(EditText)findViewById(R.id.etEntidadTra);
        delmunadi = (EditText) findViewById(R.id.etMunicipioAdi);
        //delmuntra=(EditText)findViewById(R.id.etMunicipioTra);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEntrecall = (TextView) findViewById(R.id.tvEntrecall);
        //tvNoExt=(TextView)findViewById(R.id.tvNoExt);
        tvCalle = (TextView) findViewById(R.id.tvCalle);
        bn = (Button) findViewById(R.id.btnAgregar);
        bnGuardar = (Button) findViewById(R.id.btnGuardar);
        ImbAyuda = (ImageButton) findViewById(R.id.btnAyuda);
        spmeses = (Spinner) findViewById(R.id.spmeses);
        //spmeses2=(Spinner)findViewById(R.id.spmeses2);
        spaños = (Spinner) findViewById(R.id.spaños);
        //spaños2=(Spinner)findViewById(R.id.spaños2);
        ettelcel = (EditText) findViewById(R.id.etTelCel);
        ettelpart = (EditText) findViewById(R.id.etTelPart);
        etcp = (EditText) findViewById(R.id.etCp);
        etcenttrab = (EditText) findViewById(R.id.etCentroTrab);
        etCurp = (EditText) findViewById(R.id.etCurp);
        emailValidate = (EditText) findViewById(R.id.etEmail);
        etRfc = (EditText) findViewById(R.id.etNoRfc);
        tvRfc = (TextView) findViewById(R.id.tvRfc);
        //textViewac = (AutoCompleteTextView) findViewById(R.id.acOcupacion);


        //formato moneda
        Moneda moneda = new Moneda();
        moneda.FormatoMoneda(etIngresosT);
        //moneda.FormatoMoneda(etotroing);
        //Validaciones
        ValidaCampos numero = new ValidaCampos();
        numero.Telefono(ettelcel);
        numero.Telefono(ettelpart);
        numero.Telefono(ettelpartaval);
        numero.Telefono(ettelcelaval);
        numero.Telefono(ettelpartrefam);
        numero.Telefono(ettelcelrefam);
        numero.Telefono(tel_partrefper);
        numero.Telefono(tel_celrefper);
        numero.cp(etcp);
        numero.email(emailValidate, tvEmail);
        numero.rfc(etRfc, tvRfc);
        numero.curp(etCurp, tvCurp);

        //autocomplet ocupaciones....................................................................
        /*helper help = new helper(MainActivity.this);
        String datoocu = "Ocupaciones";
        List <String> datoOcup = new ArrayList<>();
        datoOcup = help.CargarCatalagos(datoocu);
        String[] ocupaciones = datoOcup.toArray(new String[0]);
        //String[] ocupaciones = getResources().getStringArray(R.array.Ocupacion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ocupaciones);
        textViewac.setAdapter(adapter);*/

        //agregado
        this.file = "";
        this.opcion = 0;
        //agregado


        Calendario1 = (EditText) findViewById(R.id.etCalendario);
        Calendario1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mes2 = c.get(Calendar.MONTH);
                ano2 = c.get(Calendar.YEAR);
                if(ultimoano != 0 ){
                    dia = ultimodia;
                    mes = ultimomes;
                    ano = ultimoano;
                }else{
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                ano = c.get(Calendar.YEAR);}
                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener,
                        ano, mes, dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            String date;

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;

                if ((month >= 1) && (month <= 9) && (dayOfMonth >= 1) && (dayOfMonth <= 9)) {
                    date = year + "/" + "0" + month + "/" + "0" + dayOfMonth;
                } else if ((month >= 1) && (month <= 9) && (dayOfMonth > 9)) {
                    date = year + "/" + "0" + month + "/" + dayOfMonth;
                } else if ((month > 9) && (dayOfMonth >= 1) && (dayOfMonth <= 9)) {
                    date = year + "/" + month + "/" + "0" + dayOfMonth;
                } else if ((month > 9) && (dayOfMonth > 9)) {
                    date = year + "/" + month + "/" + dayOfMonth;
                }


                // String date = dayOfMonth + "/" + month + "/" + year;
                //if (year + 18 >= ano2 && month >= mes2 + 1) {
                if(year + 18 >= ano2){
                    if(year + 18 == ano2){
                        if(month > mes2 +1){
                            Toast.makeText(getApplicationContext(), "El solicitante no puede ser menor de edad", Toast.LENGTH_SHORT).show();
                        }else{
                            ultimoano = year;
                            ultimomes = month -1;
                            ultimodia = dayOfMonth;
                            Calendario1.setText(date);
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "El solicitante no puede ser menor de edad", Toast.LENGTH_SHORT).show();
                    }


                } else if (year + 68 <= ano2) {
                    {
                        Toast.makeText(getApplicationContext(), "El solicitante no puede ser mayor a 68 años", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ultimoano = year;
                    ultimomes = month -1;
                    ultimodia = dayOfMonth;
                    Calendario1.setText(date);
                }
            }
        };
        //Spinners meses y años
        String[] Meses = getResources().getStringArray(R.array.Meses);
        String[] años = getResources().getStringArray(R.array.Años);
        spmeses.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Meses));
        //spmeses2.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Meses));
        spaños.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, años));
        //spaños2.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, años));

        // poner ala escucha a los botones
        cbCoincideine.setOnClickListener((View.OnClickListener) this);
        etocupacion.setOnClickListener((View.OnClickListener) this);
        // textViewac .setOnClickListener((View.OnClickListener) this);
        etHora1.setOnClickListener((View.OnClickListener) this);
        etHora2.setOnClickListener((View.OnClickListener) this);
        etHora3.setOnClickListener((View.OnClickListener) this);
        etHora4.setOnClickListener((View.OnClickListener) this);
        etHorariotel1.setOnClickListener((View.OnClickListener) this);
        etHorariotel2.setOnClickListener((View.OnClickListener) this);
        etHorariotel3.setOnClickListener((View.OnClickListener) this);
        etHorariotel4.setOnClickListener((View.OnClickListener) this);
        cbHorarioo.setOnClickListener((View.OnClickListener) this);
        cbHorarioT.setOnClickListener((View.OnClickListener) this);
        //cbOtroIngresoSi.setOnClickListener((View.OnClickListener) this);
        //cbOtroIngNo.setOnClickListener((View.OnClickListener) this);
        //cbComisionistaSi.setOnClickListener((View.OnClickListener) this);
        //cbComisionistaNo.setOnClickListener((View.OnClickListener) this);
        //cbCompIngSi.setOnClickListener((View.OnClickListener) this);
        //cbCompIngNo.setOnClickListener((View.OnClickListener) this);
        cbunapesi.setOnClickListener((View.OnClickListener) this);
        cbavalunapesi.setOnClickListener((View.OnClickListener) this);
        cbRefFamUnApeSi.setOnClickListener((View.OnClickListener) this);
        cbRefPerUnApeSi.setOnClickListener((View.OnClickListener) this);
        cbHorNo.setOnClickListener((View.OnClickListener) this);
        cbHorarioTno.setOnClickListener((View.OnClickListener) this);
        cbTrabajasi.setOnClickListener((View.OnClickListener) this);
        cbTrabajano.setOnClickListener((View.OnClickListener) this);
        cbBanco.setOnClickListener((View.OnClickListener) this);
        cbTarjBan.setOnClickListener((View.OnClickListener) this);
        cbCasaCom.setOnClickListener((View.OnClickListener) this);
        cbNinguno.setOnClickListener((View.OnClickListener) this);
        etsolicitud.setOnClickListener((View.OnClickListener) this);
        etcomdom.setOnClickListener((View.OnClickListener) this);
        etcoming.setOnClickListener((View.OnClickListener) this);
        etifeine.setOnClickListener((View.OnClickListener) this);
        etformbur.setOnClickListener((View.OnClickListener) this);
        delmun.setOnClickListener((View.OnClickListener) this);
        sexo.setOnClickListener((View.OnClickListener) this);
        EstadoC.setOnClickListener((View.OnClickListener) this);
        TipoVi.setOnClickListener((View.OnClickListener) this);
        entidadfed.setOnClickListener((View.OnClickListener) this);
        etperiodo.setOnClickListener((View.OnClickListener) this);
        //etpertraotroing.setOnClickListener((View.OnClickListener) this);
        etparentesco.setOnClickListener((View.OnClickListener) this);
        entidadfedadi.setOnClickListener((View.OnClickListener) this);
        delmunadi.setOnClickListener((View.OnClickListener) this);
        //delmuntra.setOnClickListener((View.OnClickListener) this);
        etparentescoreffa.setOnClickListener((View.OnClickListener) this);
        etparentescorefper.setOnClickListener((View.OnClickListener) this);
        //etnacionalidad.setOnClickListener((View.OnClickListener)this);
        //etloctra.setOnClickListener((View.OnClickListener)this);
        //etentidadtra.setOnClickListener((View.OnClickListener)this);
        etentavaladi.setOnClickListener((View.OnClickListener) this);
        etmunavaladi.setOnClickListener((View.OnClickListener) this);
        bn.setOnClickListener((View.OnClickListener) this);
        bnGuardar.setOnClickListener((View.OnClickListener) this);
        ImbAyuda.setOnClickListener((View.OnClickListener) this);
        etcolonia.setOnClickListener((View.OnClickListener) this);
        //etcoloniaTra.setOnClickListener((View.OnClickListener)this);
        etcoloniaaval.setOnClickListener((View.OnClickListener) this);
        etcolrefam.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nvaSol) {
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);//Start the same Activity
            finish();
            // Handle the camera action
        } else if (id == R.id.solPen) {
            Intent intent = new Intent(MainActivity.this, Pendientes.class);
            startActivity(intent);

        } else if (id == R.id.cerrarSes) {
            //Intent intent = new Intent(MainActivity.this,Login.class);
            //startActivity(intent);

            CerrarSesion();

        } else if (id == R.id.sincronizarcat) {
            //Sincronizacion = new SincronizarDatos();
            //Sincronizacion.execute((Void) null);
            LLamarMetodos();
        }else if (id == R.id.DescargarCs) {
            String link = "https://play.google.com/store/apps/details?id=com.intsig.camscanner";
            Intent intent = null;
            intent = new Intent(intent.ACTION_VIEW,Uri.parse(link));
            startActivity(intent);
        }else if (id == R.id.DescargarAdmin) {
            String link = "https://play.google.com/store/apps/details?id=com.alphainventor.filemanager";
            Intent intent = null;
            intent = new Intent(intent.ACTION_VIEW,Uri.parse(link));
            startActivity(intent);}

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //horas h = new horas();
    horas h;
    @Override
    public void onClick(View v) {


        helper helperG = new helper(MainActivity.this);
        SeguimientoHelper helpseg = new SeguimientoHelper(MainActivity.this);
        switch (v.getId()) {
            case R.id.btnAyuda:
                DialogoAyuda dialogFragmentAyu = new DialogoAyuda();
                dialogFragmentAyu.show(getSupportFragmentManager(), DialogoAyuda.class.getSimpleName());
                break;
            case R.id.etColonia:
                if (etcp.getText().toString().equals("")) {
                    Toast.makeText(this, "Debe poner el codigo postal primero", Toast.LENGTH_SHORT).show();
                }
                miblundle = new Bundle();
                miblundle.putString("dd", "col");
                Dialogos dialogFragmentcol = new Dialogos();
                dialogFragmentcol.setArguments(miblundle);
                dialogFragmentcol.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
                /*
            case R.id.etColoniaTra:
                miblundle= new Bundle();
                miblundle.putString("dd","coltra");
                Dialogos dialogFragmentcol11 =new  Dialogos();
                dialogFragmentcol11.setArguments(miblundle);
                dialogFragmentcol11.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;*/
            case R.id.etColoniaAvalAdi:
                miblundle = new Bundle();
                miblundle.putString("dd", "colaval");
                Dialogos dialogFragmentcol12 = new Dialogos();
                dialogFragmentcol12.setArguments(miblundle);
                dialogFragmentcol12.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etColoniaRefadi:
                miblundle = new Bundle();
                miblundle.putString("dd", "colrefam");
                Dialogos dialogFragmentcol13 = new Dialogos();
                dialogFragmentcol13.setArguments(miblundle);
                dialogFragmentcol13.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etHorario1:
                horas h1 = new horas(etHora1,etHora1,MainActivity.this);
                h1.Hora(etHora1, MainActivity.this);
                cbHorarioo.setChecked(false);
                break;
            case R.id.etHorario2:
                h = new horas(etHora2,etHora1,MainActivity.this);
                h.Hora(etHora2, MainActivity.this);
                cbHorarioo.setChecked(false);
                break;
            case R.id.etHorario3:
                h = new horas(etHora3,etHora2,MainActivity.this);
                h.Hora(etHora3, MainActivity.this);
                cbHorarioo.setChecked(false);
                break;
            case R.id.etHorario4:
                h = new horas(etHora4,etHora3,MainActivity.this);
                h.Hora(etHora4, MainActivity.this);
                cbHorarioo.setChecked(false);
                break;
            case R.id.etHorarioTel1:
                h = new horas(etHorariotel1,etHorariotel1,MainActivity.this);
                h.Hora(etHorariotel1, MainActivity.this);
                cbHorarioT.setChecked(false);
                break;
            case R.id.etHorarioTel2:
                h = new horas(etHorariotel2,etHorariotel1,MainActivity.this);
                h.Hora(etHorariotel2, MainActivity.this);
                cbHorarioT.setChecked(false);
                break;
            case R.id.etHorarioTel3:
                h = new horas(etHorariotel3,etHorariotel2,MainActivity.this);
                h.Hora(etHorariotel3, MainActivity.this);
                cbHorarioT.setChecked(false);
                break;
            case R.id.etHorarioTel4:
                h = new horas(etHorariotel4,etHorariotel3,MainActivity.this);
                h.Hora(etHorariotel4, MainActivity.this);
                cbHorarioT.setChecked(false);
                break;
            case R.id.btnAgregar:
                Categorias dialogFragmentcat = new Categorias();
                dialogFragmentcat.show(getSupportFragmentManager(), Categorias.class.getSimpleName());
                break;
            case R.id.cbUnApeSi:
                if (cbunapesi.isChecked()) {
                    apellidoma.setVisibility(View.GONE);
                    apellidoma.setText("");
                } else {
                    if (!cbunapesi.isChecked()) {
                        apellidoma.setVisibility(View.VISIBLE);

                    }
                }
                break;
            case R.id.cbAvalUnApeSi:
                if (cbavalunapesi.isChecked()) {
                    apellidomapca.setVisibility(View.GONE);
                    apellidomapca.setText("");
                } else {
                    if (!cbavalunapesi.isChecked()) {
                        apellidomapca.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.cbRefFamUnApeSi:
                if (cbRefFamUnApeSi.isChecked()) {
                    apellidomareffa.setVisibility(View.GONE);
                    apellidomareffa.setText("");
                } else {
                    if (!cbRefFamUnApeSi.isChecked()) {
                        apellidomareffa.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.cbRefPerUnApeSi:
                if (cbRefPerUnApeSi.isChecked()) {
                    apellidomarefper.setVisibility(View.GONE);
                    apellidomarefper.setText("");
                } else {
                    if (!cbRefPerUnApeSi.isChecked()) {
                        apellidomarefper.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.cbCoincide:
                if (cbCoincideine.isChecked()) {
                    etcomdom.setVisibility(View.GONE);
                    cbComDom.setVisibility(View.GONE);
                } else {
                    if (!cbCoincideine.isChecked()) {
                        etcomdom.setVisibility(View.VISIBLE);
                        cbComDom.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.cbHorario:
                if (cbHorarioo.isChecked()) {
                    etHora1.setText("");
                    etHora2.setText("");
                    etHora3.setText("");
                    etHora4.setText("");
                    etHora1.setVisibility(View.GONE);
                    etHora2.setVisibility(View.GONE);
                    etHora3.setVisibility(View.GONE);
                    etHora4.setVisibility(View.GONE);
                    tvHortamb.setVisibility(View.GONE);
                    cbHorNo.setChecked(false);
                }
                break;
            case R.id.cbHorariot:
                if (cbHorarioT.isChecked()) {
                    etHorariotel1.setText("");
                    etHorariotel2.setText("");
                    etHorariotel3.setText("");
                    etHorariotel4.setText("");
                    etHorariotel1.setVisibility(View.GONE);
                    etHorariotel2.setVisibility(View.GONE);
                    etHorariotel3.setVisibility(View.GONE);
                    etHorariotel4.setVisibility(View.GONE);
                    tvTelTamb.setVisibility(View.GONE);
                    cbHorarioTno.setChecked(false);
                }
                break;
            case R.id.cbHorariotno:
                if (cbHorarioTno.isChecked()) {
                    etHorariotel1.setVisibility(View.VISIBLE);
                    etHorariotel2.setVisibility(View.VISIBLE);
                    etHorariotel3.setVisibility(View.VISIBLE);
                    etHorariotel4.setVisibility(View.VISIBLE);
                    tvTelTamb.setVisibility(View.VISIBLE);
                    cbHorarioT.setChecked(false);
                }
                break;
            case R.id.cbHorarioNo:
                if (cbHorNo.isChecked()) {
                    etHora1.setVisibility(View.VISIBLE);
                    etHora2.setVisibility(View.VISIBLE);
                    etHora3.setVisibility(View.VISIBLE);
                    etHora4.setVisibility(View.VISIBLE);
                    tvHortamb.setVisibility(View.VISIBLE);
                    cbHorarioo.setChecked(false);
                }
                break;
            case R.id.cbTrabajasi:
                if (cbTrabajasi.isChecked()) {
                    cbTrabajano.setChecked(false);
                    etcenttrab.setVisibility(View.VISIBLE);
                    etTelTra.setVisibility(View.VISIBLE);
                } else {
                    if (!cbTrabajasi.isChecked()) {
                        etcenttrab.setVisibility(View.GONE);
                        etTelTra.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.cbTrabajano:
                if (cbTrabajano.isChecked()) {
                    cbTrabajasi.setChecked(false);
                    etcenttrab.setVisibility(View.GONE);
                    etTelTra.setVisibility(View.GONE);
                } else {
                    if (!cbTrabajano.isChecked()) {
                        etcenttrab.setVisibility(View.GONE);
                        etTelTra.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.cbBanco:
                if (cbBanco.isChecked()) {
                    cbNinguno.setChecked(false);
                }
                break;
            case R.id.cbTarjBanc:
                if (cbTarjBan.isChecked()) {
                    cbNinguno.setChecked(false);
                }
                break;
            case R.id.cbCasaCom:
                if (cbCasaCom.isChecked()) {
                    cbNinguno.setChecked(false);
                }
                break;
            case R.id.cbNinguno:
                if (cbNinguno.isChecked()) {
                    cbCasaCom.setChecked(false);
                    cbTarjBan.setChecked(false);
                    cbBanco.setChecked(false);
                }
                break;
                /*
            case R.id.cbOtroIngSi:
                if (cbOtroIngresoSi.isChecked()) {
                    cbOtroIngNo.setChecked(false);
                    etotroing.setVisibility(View.VISIBLE);
                    etpertraotroing.setVisibility(View.VISIBLE);;
                }
                break;
            case R.id.cbOtroIngNo:
                if (cbOtroIngresoSi.isChecked()) {
                    cbOtroIngresoSi.setChecked(false);
                    etotroing.setVisibility(View.GONE);
                    etpertraotroing.setVisibility(View.GONE);
                }
                break;
            case R.id.cbComicionistasi:
                if (cbComisionistaSi.isChecked()) {
                    cbComisionistaNo.setChecked(false);
                }
                break;
            case R.id.cbComicionistano:
                if (cbComisionistaNo.isChecked()) {
                    cbComisionistaSi.setChecked(false);
                }
                break;
            case R.id.cbCompIngSi:
                if (cbCompIngSi.isChecked()) {
                    cbCompIngNo.setChecked(false);
                }
                break;
            case R.id.cbCompIngNo:
                if (cbCompIngNo.isChecked()) {
                    cbCompIngSi.setChecked(false);
                }
                break;
            case R.id.etMunicipioTra:
                if(etentidadtra.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "debe seleccionar primero el estado", Toast.LENGTH_SHORT).show();
                }else {
                    miblundle = new Bundle();
                    miblundle.putString("dd", "muntra");
                    Dialogos dialogFragmentmuntra = new Dialogos();
                    dialogFragmentmuntra.setArguments(miblundle);
                    dialogFragmentmuntra.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                }
                break;
            case R.id.etPeriodoTraOtroing:
                miblundle= new Bundle();
                miblundle.putString("dd","pertraotroing");
                Dialogos dialogFragmentper2 = new Dialogos();
                dialogFragmentper2.setArguments(miblundle);
                dialogFragmentper2.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etTipoLocTra:
                miblundle= new Bundle();
                miblundle.putString("dd","tiloctra");
                Dialogos dialogFragmentloctra =new Dialogos();
                dialogFragmentloctra.setArguments(miblundle);
                dialogFragmentloctra.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etEntidadTra:
                delmuntra.setText("");
                miblundle= new Bundle();
                miblundle.putString("dd","estadotra");
                Dialogos dialogFragmententtra = new Dialogos();
                dialogFragmententtra.setArguments(miblundle);
                dialogFragmententtra.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etNacionalidad:
                miblundle= new Bundle();
                miblundle.putString("dd","nacio");
                Dialogos dialogFragmentnacio = new Dialogos();
                dialogFragmentnacio.setArguments(miblundle);
                dialogFragmentnacio.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;*/
            case R.id.etMunicipio:
                if (entidadfed.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "debe seleccionar primero el estado", Toast.LENGTH_SHORT).show();
                } else {
                    miblundle = new Bundle();
                    miblundle.putString("dd", "municipio");
                    Dialogos dialogFragmentmun = new Dialogos();
                    dialogFragmentmun.setArguments(miblundle);
                    dialogFragmentmun.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                }
                //dialogFragmentmun.show(getSupportFragmentManager(), Municipios.class.getSimpleName());
                break;
            case R.id.etMunicipioAdi:
                if (entidadfedadi.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "debe seleccionar primero el estado", Toast.LENGTH_SHORT).show();
                } else {
                    miblundle = new Bundle();
                    miblundle.putString("dd", "munfam");
                    Dialogos dialogFragmentmunadi = new Dialogos();
                    dialogFragmentmunadi.setArguments(miblundle);
                    dialogFragmentmunadi.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                }
                break;
            case R.id.etMunAvalAdi:
                if (etentavaladi.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "debe seleccionar primero el estado", Toast.LENGTH_SHORT).show();
                } else {
                    miblundle = new Bundle();
                    miblundle.putString("dd", "munaval");
                    Dialogos dialogFragmentm = new Dialogos();
                    dialogFragmentm.setArguments(miblundle);
                    dialogFragmentm.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                }
                break;
            case R.id.etSexo:
                miblundle = new Bundle();
                miblundle.putString("dd", "sex");
                Dialogos dialogFragmentsexo = new Dialogos();
                dialogFragmentsexo.setArguments(miblundle);
                dialogFragmentsexo.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etEstado:
                miblundle = new Bundle();
                miblundle.putString("dd", "etciv");
                Dialogos dialogFragmentetciv = new Dialogos();
                dialogFragmentetciv.setArguments(miblundle);
                dialogFragmentetciv.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etTipoVivienda:
                miblundle = new Bundle();
                miblundle.putString("dd", "tipvi");
                ;
                Dialogos dialogFragmenttipvi = new Dialogos();
                dialogFragmenttipvi.setArguments(miblundle);
                dialogFragmenttipvi.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etEntidad:
                delmun.setText("");
                miblundle = new Bundle();
                miblundle.putString("dd", "estado");
                Dialogos dialogFragmententfe = new Dialogos();
                dialogFragmententfe.setArguments(miblundle);
                dialogFragmententfe.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etEntAvalAdi:
                etmunavaladi.setText("");
                miblundle = new Bundle();
                miblundle.putString("dd", "estadoaval");
                Dialogos dialogFragmententavaladi = new Dialogos();
                dialogFragmententavaladi.setArguments(miblundle);
                dialogFragmententavaladi.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etPeriodo:
                miblundle = new Bundle();
                miblundle.putString("dd", "peri");
                Dialogos dialogFragmentper = new Dialogos();
                dialogFragmentper.setArguments(miblundle);
                dialogFragmentper.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etParentesco:
                miblundle = new Bundle();
                miblundle.putString("dd", "paren");
                Dialogos dialogFragmentpar = new Dialogos();
                dialogFragmentpar.setArguments(miblundle);
                dialogFragmentpar.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etEntidadAdi:
                delmunadi.setText("");
                miblundle = new Bundle();
                miblundle.putString("dd", "estadorefam");
                Dialogos dialogFragmententadi = new Dialogos();
                dialogFragmententadi.setArguments(miblundle);
                dialogFragmententadi.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etParentescoreffam:
                miblundle = new Bundle();
                miblundle.putString("dd", "paren2");
                Dialogos dialogFragmentpar2 = new Dialogos();
                dialogFragmentpar2.setArguments(miblundle);
                dialogFragmentpar2.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etParentescorefper:
                miblundle = new Bundle();
                miblundle.putString("dd", "paren3");
                Dialogos dialogFragmentpar3 = new Dialogos();
                dialogFragmentpar3.setArguments(miblundle);
                dialogFragmentpar3.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;
            case R.id.etSoli:
               /* Intent intent10 = new Intent();
                Bundle mibundle10= new Bundle();
                mibundle10.putString("dd","soli");
                intent10.putExtras(mibundle10);*/
                opcion = 1;
                MostrarDialogoOpciones();
                break;
            case R.id.etIfeIne:
                opcion = 2;
                MostrarDialogoOpciones();
                break;
            case R.id.etComDom:
                opcion = 3;
                MostrarDialogoOpciones();
                break;
            case R.id.etForBur:
                opcion = 5;
                MostrarDialogoOpciones();
                break;
            case R.id.etComIng:
                opcion = 4;
                MostrarDialogoOpciones();
                break;
            //agregado
            case R.id.btnGuardar:
                //helperG.Guardar(nombre,apellidopa,apellidoma,Calendario1,sexo,EstadoC,etCurp,TipoVi,textViewac,etIngresosT,etperiodo,cbTrabajasi,cbTrabajano);

                boolean datooo = helpseg.GuardarSolicitud(cbunapesi, apellidopa, apellidoma, nombre, Calendario1, sexo, EstadoC, etCurp, etife, etRfc, TipoVi, spaños, spmeses, etCalle, etNumExt, etNumInt, etcp, etcolonia, etColoniaIne, etLocalidad, entidadfed, delmun, etEntCalles, ettelpart, ettelcel,
                        emailValidate, etDepeneco, etperslab, cbBanco, cbCasaCom, cbTarjBan, cbNinguno, cbCoincideine, cbHorarioo, etHora1, etHora2, etHora3, etHora4, cbHorarioT, etHorariotel1, etHorariotel2, etHorariotel3, etHorariotel4, cbTrabajasi, cbTrabajano, etocupacion, otraocupa, etcenttrab,
                        etTelTra, etIngresosT, etperiodo, cbavalunapesi, etparentesco, etapepatpca, apellidomapca, etnombreaval, ettelpartaval, ettelcelaval, etcalleaval, etnumextaval, etnumintaval, cpaval, etcoloniaaval, etColIneAval, etlocaval, etentavaladi, etmunavaladi, cbRefFamUnApeSi
                        , etparentescoreffa, etapepatrefam, etapematrefam, etnombrereffa, ettelpartrefam, ettelcelrefam, callerefam, etnumextrefam, etnumintrefam, etcprefam, etcolrefam, etColIneRefam, etlocrefam,
                        entidadfedadi, delmunadi, cbRefPerUnApeSi, etparentescorefper, etapepatrefper, etapematrefper, etnombrerefper, tel_partrefper, tel_celrefper, tvCategoria, cbSolicitud, cbIfeIne, cbComDom, cbFormBur, cbComIng, tvCurp, tvRfc);
                //contar pendientes********************************************************************************
                //c = helperG.CargarPendientes();
                //contador1 = c.getCount();
                Menu nv = navigationView.getMenu();
                itam = (MenuItem) nv.findItem(R.id.solPen);
                itam.setTitle("Solicitudes Pendientes" + "                " + contador1);
                //contar pendientes **********************************************************************************
                if (datooo) {
                    Intent refresh = new Intent(this, MainActivity.class);
                    startActivity(refresh);//Start the same Activity
                    finish();
                }
                break;
            case R.id.etOcupacion:
                miblundle = new Bundle();
                miblundle.putString("dd", "ocupacion");
                Dialogos dialogFragmentocup = new Dialogos();
                dialogFragmentocup.setArguments(miblundle);
                dialogFragmentocup.show(getSupportFragmentManager(), Dialogos.class.getSimpleName());
                break;


            default:
                break;
        }
    }

    private void CerrarSesion() {
        SessionManager.INSTANCE.setPreferences(MainActivity.this, "_pwd", null);
        SessionManager.INSTANCE.setPreferences(MainActivity.this, "_user", null);
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
        //System.exit(0);
    }

    //Prueba camara **********************************************************************************
    public void camara() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //int sol_id = help1.getSolicitud_id();
        int sol_id = help1.getContador();
        String strcontador = String.valueOf(contador1 + 1);
        final String ruta_fotos = String.format("%s/solicituddecredito2/%d/", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), sol_id + 1);
        File dirFoto = new File(ruta_fotos);
        dirFoto.mkdirs();
        //String nombreFoto = nombre.getText().toString();
        String nombreFoto = "";
        if (opcion == 1) {
            nombreFoto = "SOLICITUD";
        } else if (opcion == 2) {
            nombreFoto = "IFE.INE";
        } else if (opcion == 3) {
            nombreFoto = "COMP.DOM";
        } else if (opcion == 5) {
            nombreFoto = "FORM.BURO";
        } else if (opcion == 4) {
            nombreFoto = "COMP.ING";
        }

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int millis = now.get(Calendar.MILLISECOND);
        String userid = SessionManager.INSTANCE.getPreferences(MainActivity.this, "_userid");
        String fec = String.format("%d%02d%02d%02d%02d%02d%03d", year, month, day, hour, minute, second, millis);

        //file = String.format("%s%s" + nombreFoto + ".jpg", ruta_fotos,contador +1);
        //file = String.format("%d%s" +contador1 +1+ ,+ nombreFoto + ".jpg", ruta_fotos);
        file = String.format("%s%s%s%s" + nombreFoto + ".jpg", ruta_fotos, strcontador, userid, fec);
        //file = String.format("%s%d.jpg", ruta_fotos, contador1 + nombreFoto );


        //String file = String.format("%s%d.jpg", ruta_fotos, m_lstPreguntas.get(idx_pregunta).getNum_pregunta());
        //Alert.INSTANCE.AlertMessage(EntrevistaActivity.this, file);
        File mi_foto = new File(file);
        try {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            mi_foto.createNewFile();
            Uri uri = Uri.fromFile(mi_foto);
            //sol_id = help1.getSolicitud_id();
            String Path = help1.GuardarUrl(file.toString(), opcion, sol_id);
            if (!Path.equals("")) eliminarFoto(Path);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(cameraIntent, 0);
        } catch (IOException e) {
            //e.printStackTrace();
            // Alert.INSTANCE.AlertMessage(EntrevistaActivity.this, e.getMessage());
            Toast.makeText(this, "Error al guardar imagen", Toast.LENGTH_SHORT);
        }

    }

    //Prueba camara ************************************************************************************
    public void eliminarFoto(String path) {
        File pathh = new File(path);
        if (pathh.exists()) {
            //file.getPath().endsWith(".jpg")
            pathh.delete();
        }
    }

    public void MostrarDialogoOpciones() {
         CharSequence [] options = null;
        //final CharSequence [] options = {"Tomar Foto","Galeria","Cancelar"};
        if (opcion == 1 || opcion == 5|| opcion == 2){
            options = new CharSequence[2];
            options  [0]= "Galeria";
            options [1]= "Cancelar";
            //options [2] = "";
        }else{
            options = new CharSequence[3];
            options [0]= "Tomar Foto";
            options [1]= "Galeria";
            options [2]= "Cancelar";
        }
        //final CharSequence [] options = {"Tomar Foto","Galeria","Cancelar"};
        //final CharSequence [] options = options2;



        //final CharSequence[] options = {"Tomar Foto", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        String nombreFoto = "";
        if (opcion == 1) {
            nombreFoto = "SOLICITUD";
        } else if (opcion == 2) {
            nombreFoto = "IFE.INE";
        } else if (opcion == 3) {
            nombreFoto = "COMP.DOM";
        } else if (opcion == 5) {
            nombreFoto = "FORM.BURO";
        } else if (opcion == 4) {
            nombreFoto = "COMP.ING";
        }
        builder.setTitle("Opción para" + " " +nombreFoto);
        CharSequence []options2 = options;
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                int opt = 0;
                if (options2[seleccion].equals("Tomar Foto")) {
                    if(opcion == 5 || opcion == 1 || opcion == 2 ){
                        Toast.makeText(MainActivity.this,"Esta Opcion no esta disponible",Toast.LENGTH_LONG).show();
                    }else{
                        opt = 1;
                        seleccionarImagen(opt);
                        //camara();
                    }
                } else if (options2[seleccion].equals("Galeria")) {
                    opt = 2;
                    seleccionarImagen(opt);
                    //comentado
                    // startActivityForResult(intent.createChooser(intent,"Selecciona Imagen"),SELECT_PICTURE);
                } else if (options2[seleccion].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void abrirGaleriaImg(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("*/storage");
        startActivityForResult(i, REQUEST_CODE_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case WRITE_STORAGE_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    abrirGaleriaImg();
                }else {
                    Toast.makeText(this, "¡No tienes permiso para ver, modificar las fotos o imágenes!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public void seleccionarImagen(int opt){
        /**
         * SI LA API ES IGUAL A 23 O SUPERIOR PEDIMOS PERMISOS EN TIEMPO DE EJECUCIÓN
         */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Habilitar permisos para la version de API 23 a mas

            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA);

            int verificarPermisoWriteExternalStorage = ContextCompat
                    .checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            boolean camarapermiso = shouldShowRequestPermissionRationale
                    (Manifest.permission.CAMERA);

            boolean write_external_storage = shouldShowRequestPermissionRationale
                    (Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if(verificarPermisoWriteExternalStorage != PackageManager.PERMISSION_GRANTED && permissionCheck != PackageManager.PERMISSION_GRANTED ){
                //solicitar permiso
                if(write_external_storage && camarapermiso){
                    mostrarExplicacion();
                }else{

                    String[] read_write_permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};

                    requestPermissions(read_write_permission, WRITE_STORAGE_PERMISSION);
                }

            }else{
                if(opt == 1){
                    camara();
                }else {
                abrirGaleriaImg();}
            }
        }else{
            if(opt == 1){
                camara();
            }else {
                abrirGaleriaImg();}
        }
    }


    private void mostrarExplicacion() {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setTitle("Autorización")
                .setMessage("Necesito permiso leer y manipular las fotos e imágenes de tu dispositivo.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //pedir permiso

                        try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, WRITE_STORAGE_PERMISSION);
                            }
                        } catch (Exception e) {
                            throw new IllegalArgumentException();
                        }

                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Desplegar mensaje
                Toast.makeText(MainActivity.this, "Haz rechazado la petición de permiso de lectura y escritura de archivos", Toast.LENGTH_SHORT).show();
            }
        })
                .show();
    }


    //Se Agrego**************************************************************************************************************************************************************
    public void copiarImagen(String rutaImagenSeleccionada) {
        //Obtengo el nombre de la imagen que tendra mi nuevo archivo con la extensión igual
        //al archvio seleccionado gracias a la función obtenerExtensionArchivo()
        String nombreFoto = "";
        if (opcion == 1) {
            nombreFoto = "SOLICITUD";
        } else if (opcion == 2) {
            nombreFoto = "IFE.INE";
        } else if (opcion == 3) {
            nombreFoto = "COMP.DOM";
        } else if (opcion == 5) {
            nombreFoto = "FORM.BURO";
        } else if (opcion == 4) {
            nombreFoto = "COMP.ING";
        }

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int millis = now.get(Calendar.MILLISECOND);
        String userid = SessionManager.INSTANCE.getPreferences(MainActivity.this, "_userid");
        String fec = String.format("%d%02d%02d%02d%02d%02d%03d", year, month, day, hour, minute, second, millis);
        int sol_id = help1.getContador();
        String nomproject = "solicituddecredito2";
        int nombredir = sol_id + 1;
        String nombreDirectorio = String.valueOf(nombredir);
        //Obtengo la ruta de la carpeta creada (imagenes)
        final String rutaDirectorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+ BARRA + nomproject + BARRA + nombreDirectorio;

        //Nombre que le asignare al directorio donde se guardaran las imágenes

        String extension = String.format(".%s", obtenerExtensionArchivo(rutaImagenSeleccionada));
        file = String.format("%s%s" + nombreFoto + extension, userid, fec);
        file2 =String.format("%s%s%s" + nombreFoto + extension,rutaDirectorio + BARRA, userid, fec);
        String nombreImagen = file;
        String Path = help1.GuardarUrl(file2.toString(), opcion, sol_id);
        if (!Path.equals("")) eliminarFoto(Path);

        //Declaro la variable file con la ruta del archivo antes obtenido
        File fileDirectorio = new File(rutaDirectorio);

        //Ruta donde se guardara el archivo + el nombre del archivo
        String rutaFinalImagen = rutaDirectorio + BARRA + nombreImagen;

        //Almaceno la ruta de la imagen seleccionada (donde se encuentra el archivo a copiar) y
        // ruta final (donde se copiara la imagen)
        String[] args = {rutaImagenSeleccionada, rutaFinalImagen};

        //comprobar si la carpeta "IMG_prueba" existe
        if (fileDirectorio.exists() && fileDirectorio.isDirectory()) {
            //si es nuevo elimina interno
            //Copio el archivo seleccionado
            CopiarArchivos.main(args);
            //Muestro un mensjase
            Toast.makeText(this, "Imagen copiada en: " + rutaDirectorio, Toast.LENGTH_SHORT).show();

        } else {//Se ejecuta si no existe el directorio

            //Si la carpeta aun no existe la creo
            crearDirectorioPublico(nombreDirectorio,nomproject);

            //Copio el archivo seleccionado
            CopiarArchivos.main(args);

            //Muestro un mensjase
            Toast.makeText(this, "Imagen copiada en: "+ rutaDirectorio, Toast.LENGTH_SHORT).show();
        }
    }

    //Función para obtener la uri de la imagen seleccionada
    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        int column_index;
        String ruta = null;

        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getContentResolver().query(contentUri,  proj, null, null, null);

            if (cursor != null){

                column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                ruta = cursor.getString(column_index);

            }
            return ruta;

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String nombreImagen(String rutaImagenSeleccionada){
        String prefijo = "IMG_";
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
        //Extraigo la extension de la imagen seleccionada
        String extension = String.format(".%s", obtenerExtensionArchivo(rutaImagenSeleccionada));
        return String.format("%s%s%s",prefijo,timeStamp,extension);
    }

    public static String obtenerExtensionArchivo(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    public File crearDirectorioPublico(String nombreDirectorio,String nomproject) {
        //Crear directorio público en la carpeta Pictures.
        File directorio = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+ BARRA + nomproject + BARRA + nombreDirectorio);
        //Muestro un mensaje en el logcat si no se creo la carpeta por algun motivo

        //validar nuevo
        if (!directorio.mkdirs())
            Log.e(TAG, "Error: No se creo el directorio público");

        return directorio;
    }
    //Se Agrego**************************************************************************************************************************************************************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (opcion == 1 && resultCode == RESULT_OK) {
                //resultado Ok de foto de solicitud
                Toast.makeText(this, "resultado Ok de foto de solicitud", Toast.LENGTH_SHORT).show();
                this.etsolicitud.setTextColor(Color.parseColor("#0174DF"));
                this.etsolicitud.setText("Ok");
                cbSolicitud.setChecked(true);
            } else if (opcion == 1 && resultCode == RESULT_CANCELED) {
                //resultado Cancelado de foto de solicitud
                Toast.makeText(this, "resultado Cancelado de foto de solicitud", Toast.LENGTH_SHORT).show();
                this.etsolicitud.setTextColor(Color.parseColor("#BF0811"));
                this.etsolicitud.setText("ADJUNTAR ARCHIVO");
                cbSolicitud.setChecked(false);
            } else if (opcion == 2 && resultCode == RESULT_OK) {
                //resultado Ok de foto de IFE/INE
                Toast.makeText(this, "resultado Ok de foto de IFE/INE", Toast.LENGTH_SHORT).show();
                this.etifeine.setTextColor(Color.parseColor("#0174DF"));
                this.etifeine.setText("Ok");
                cbIfeIne.setChecked(true);
            } else if (opcion == 2 && resultCode == RESULT_CANCELED){
                //resultado Cancelado de IFE/INE
                Toast.makeText(this, "resultado Cancelado de IFE/INE", Toast.LENGTH_SHORT).show();
                this.etifeine.setTextColor(Color.parseColor("#BF0811"));
                this.etifeine.setText("ADJUNTAR ARCHIVO");
                cbIfeIne.setChecked(false);
            }else if (opcion == 3 && resultCode == RESULT_OK) {
                //resultado Ok de foto de IFE/INE
                Toast.makeText(this, "resultado Ok de foto de Com.Dom", Toast.LENGTH_SHORT).show();
                this.etcomdom.setTextColor(Color.parseColor("#0174DF"));
                this.etcomdom.setText("Ok");
                cbComDom.setChecked(true);
            } else if (opcion == 3 && resultCode == RESULT_CANCELED){
                //resultado Cancelado de IFE/INE
                Toast.makeText(this, "resultado Cancelado de Com.Dom", Toast.LENGTH_SHORT).show();
                this.etcomdom.setTextColor(Color.parseColor("#BF0811"));
                this.etcomdom.setText("ADJUNTAR ARCHIVO");
                cbComDom.setChecked(false);
            }else if (opcion == 5 && resultCode == RESULT_OK) {
                //resultado Ok de foto de IFE/INE
                Toast.makeText(this, "resultado Ok de foto de ComIng", Toast.LENGTH_SHORT).show();
                this.etformbur.setTextColor(Color.parseColor("#0174DF"));
                this.etformbur.setText("Ok");
                cbFormBur.setChecked(true);
            } else if (opcion == 5 && resultCode == RESULT_CANCELED){
                //resultado Cancelado de IFE/INE
                Toast.makeText(this, "resultado Cancelado de ComIng", Toast.LENGTH_SHORT).show();
                this.etformbur.setTextColor(Color.parseColor("#BF0811"));
                this.etformbur.setText("ADJUNTAR ARCHIVO");
                cbFormBur.setChecked(false);
            }
            else if (opcion == 4 && resultCode == RESULT_OK) {
                //resultado Ok de foto de IFE/INE
                Toast.makeText(this, "resultado Ok de foto de FormBur", Toast.LENGTH_SHORT).show();
                this.etcoming.setTextColor(Color.parseColor("#0174DF"));
                this.etcoming.setText("Ok");
                cbComIng.setChecked(true);
            } else if (opcion == 4 && resultCode == RESULT_CANCELED){
                //resultado Cancelado de IFE/INE
                Toast.makeText(this, "resultado Cancelado de FormBur", Toast.LENGTH_SHORT).show();
                this.etcoming.setTextColor(Color.parseColor("#BF0811"));
                this.etcoming.setText("ADJUNTAR ARCHIVO");
                cbComIng.setChecked(false);
            }else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null){

            }
        } catch(Exception ex) {
            //Alert.INSTANCE.AlertMessage(ListadoClientesActivity.this, ex.getMessage());
        }
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            //Almaceno el valor devuelto una ves seleccionada la imagen
            mUri = data.getData();

            //Variable de tipo archivo que almacena la URi de la imagen seleccionada
            File imageFile = new File(getRealPathFromURI(mUri));

            //Guarda la ruta de la imagen seleccionada en un cadena
            mRutaImagenSeleccionada = imageFile.getPath();
            copiarImagen(mRutaImagenSeleccionada);
            //Muestro la imagen seleccionada
            //if(imageFile.exists()){
               // Bitmap myBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

           // }

        }
    }
    //Metodo para sincronizar catalagos //*********************************************************************************************
    public void SincronizaCategorias(){
        String ip = getString(R.string.ip);
        String URL1 = ip + "categoria";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    //recuperar json************************************************************************************
                    String jsonString = new String(response);
                    JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(jsonString);
                        JSONArray jsonrespuesta = jsonResponse.getJSONArray("Categoria");
                        int index = 0;
                        while (!jsonrespuesta.isNull(index)) {
                            JSONObject json = jsonrespuesta.getJSONObject(index);

                            ListaCategorias.add(new Categoria(
                                    json.getInt("categoria")
                                    , json.getString("nombre")
                            ));
                            index++;
                        }
                        helper cathelper = new helper(MainActivity.this);
                        cathelper.GuardarCategorias(ListaCategorias);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void SincronizaColonias(){
        String ip = getString(R.string.ip);
        String user = SessionManager.INSTANCE.getPreferences(MainActivity.this, "_userid");
        String URL2 =ip +"colonia?user=" + user;
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    //recuperar json************************************************************************************
                    String jsonString = new String(response);
                    JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(jsonString);
                        JSONArray jsonrespuesta = jsonResponse.getJSONArray("Colonia");
                        int index = 0;
                        while (!jsonrespuesta.isNull(index)) {
                            JSONObject json = jsonrespuesta.getJSONObject(index);

                            ListaColonias.add(new Colonia(
                                    json.getInt("Ciudad_id")
                                    , json.getInt("Colonia_id")
                                    , json.getString("Colonia")
                                    ,json.getInt("CP")
                            ));
                            index++;
                        }
                        helper colhelper = new helper(MainActivity.this);
                        //colhelper.GuardarColonias(ListaColonias);
                        if(ListaColonias.size()>0){
                           colhelper.GuardarColonias2(ListaColonias);}else{Toast.makeText(MainActivity.this,"No se Cargaron las Ocupaciones",Toast.LENGTH_SHORT).show();}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
        //return bandera;
    }
    public void SincronizaEstados(){
        String ip = getString(R.string.ip);
        String URL3 = ip + "estado";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL3, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    //recuperar json************************************************************************************
                    String jsonString = new String(response);
                    JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(jsonString);
                        JSONArray jsonrespuesta = jsonResponse.getJSONArray("Estado");
                        int index = 0;
                        while (!jsonrespuesta.isNull(index)) {
                            JSONObject json = jsonrespuesta.getJSONObject(index);

                            ListaEstados.add(new Estado(
                                    json.getInt("Estado_id")
                                    , json.getString("Estado")
                            ));
                            index++;
                        }
                        helper esthelper = new helper(MainActivity.this);
                        esthelper.GuardarEstados(ListaEstados);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void SincronizaLocalidades(){
        String ip = getString(R.string.ip);
        String URL4 = ip + "localidad";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL4, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                    //recuperar json************************************************************************************
                    String jsonString = new String(response);
                    JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(jsonString);
                        JSONArray jsonrespuesta = jsonResponse.getJSONArray("Localidad");
                        int index = 0;
                        while (!jsonrespuesta.isNull(index)) {
                            JSONObject json = jsonrespuesta.getJSONObject(index);

                            ListaLocalidades.add(new Localidad(
                                    json.getInt("TipoAsentamiento_id")
                                    , json.getString("TipoAsentamiento")
                            ));
                            index++;
                        }
                        helper lochelper = new helper(MainActivity.this);
                        lochelper.GuardarLocalidades(ListaLocalidades);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void SincronizaOcupaciones(){
        String ip = getString(R.string.ip);
        String URL5 = ip + "ocupacion";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL5, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    //recuperar json************************************************************************************
                    String jsonString = new String(response);
                    JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(jsonString);
                        JSONArray jsonrespuesta = jsonResponse.getJSONArray("Ocupacion");
                        int index = 0;
                        while (!jsonrespuesta.isNull(index)) {
                            JSONObject json = jsonrespuesta.getJSONObject(index);

                            ListaOcupaciones.add(new Ocupacion(
                                    json.getInt("Ocupacion_id")
                                    , json.getString("Ocupacion")
                            ));
                            index++;
                        }
                        helper colhelper = new helper(MainActivity.this);
                        //colhelper.GuardarOcupaciones(ListaOcupaciones);
                        if(ListaOcupaciones.size()>0){
                            colhelper.GuardarOcupaciones2(ListaOcupaciones);}else{Toast.makeText(MainActivity.this,"No se Cargaron las Ocupaciones",Toast.LENGTH_SHORT).show();}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void SincronizaParentescos(){
        String ip = getString(R.string.ip);
        String URL6 = ip + "parentesco";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL6, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                    //recuperar json************************************************************************************
                    String jsonString = new String(response);
                    JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(jsonString);
                        JSONArray jsonrespuesta = jsonResponse.getJSONArray("Parentesco");
                        int index = 0;
                        while (!jsonrespuesta.isNull(index)) {
                            JSONObject json = jsonrespuesta.getJSONObject(index);

                            ListaParentescos.add(new Parentesco(
                                    json.getString("id_parentesco")
                                    , json.getString("parentesco")
                            ));
                            index++;
                        }
                        helper colhelper = new helper(MainActivity.this);
                        //colhelper.GuardarParentescos(ListaParentescos);
                        if(ListaParentescos.size()>0){
                        colhelper.GuardarParentescos2(ListaParentescos);}else{Toast.makeText(MainActivity.this,"No se Cargaron los parentescos",Toast.LENGTH_SHORT).show();}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void SincronizaMunicipios(){
        String ip = getString(R.string.ip);
        String URL7 = ip +"municipio";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL7, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   // Toast.makeText(MainActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    //recuperar json************************************************************************************
                    String jsonString = new String(response);
                    JSONObject jsonResponse;
                    try {
                        jsonResponse = new JSONObject(jsonString);
                        JSONArray jsonrespuesta = jsonResponse.getJSONArray("Municipio");
                        int index = 0;
                        while (!jsonrespuesta.isNull(index)) {
                            JSONObject json = jsonrespuesta.getJSONObject(index);

                            ListaMunicipios.add(new Municipio(
                                    json.getInt("Estado_id")
                                    , json.getString("Municipio")
                            ));
                            index++;
                        }
                        helper colhelper = new helper(MainActivity.this);
                        colhelper.GuardarMunicipios(ListaMunicipios);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void LLamarMetodos(){
          SincronizaCategorias();
          SincronizaColonias();
       // SincronizaEstados();
        //  SincronizaLocalidades();
       // SincronizaMunicipios();
          SincronizaOcupaciones();
          SincronizaParentescos();
       // Toast.makeText(MainActivity.this,"se sincronizaron los catalagos",Toast.LENGTH_SHORT).show();
    }
    public class SincronizarDatos extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            Carga();
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean resp = false;
            try {
                SincronizaOcupaciones();
                SincronizaParentescos();
                SincronizaColonias();


            }catch (Exception e){
                e.printStackTrace();
            }

            return resp;
        }
        @Override
        protected void onPostExecute(final Boolean resp) {
            Sincronizacion = null;
            if(resp == true){
                Toast.makeText(MainActivity.this,"Se Sincronizaron los catalagos",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"Error al Sincronizar catalagos",Toast.LENGTH_LONG).show();
            }
            dialog.dismiss();
        }
    }

    public void Carga(){
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Un momento")
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
    }
}
