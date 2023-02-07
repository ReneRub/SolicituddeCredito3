package com.example.renerubio.solicituddecredito2;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.renerubio.solicituddecredito2.Entity.Datos;
import com.example.renerubio.solicituddecredito2.Model.Estado;
import com.example.renerubio.solicituddecredito2.Utils.Alert;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Dialogos extends AppCompatDialogFragment{
    public Dialogos(){}
    private static final int UNSELECTED = -1;
    private int selected;
    String dato,titulo;
    String datoocu = "Ocupaciones";
    String datopar = "Parentescos";
    String datoase = "Asentamientos";
    String datocol = "CatColonias";
    String datoest = "Estados";
    String estaid;
    boolean resp = true;
    AlertDialog.Builder builder;
    //Datos datos;

    int dato1;
    List <Estado> estadoprueba = new ArrayList<>();
    List <String> datoPar = new ArrayList<>();
    List <String> datoAse = new ArrayList<>();
    List <String> datoCol = new ArrayList<>();
    List <String> datoEst = new ArrayList<>();
    List <String> datoMun = new ArrayList<>();
    List <String> datoColFil = new ArrayList<>();
    List <String> datoOcu = new ArrayList<>();
    String []i,datoEstadociv,datosex,datoTra,datoParentesco,datoTipoVivi,datoPeriodo,datodomadi,datopaises,datolocalidades,datocrecon;
    EditText sexo1,etdato,ettrabaja,etciv,etparentesco,etparentesco2,etparentesco3,etocupacion,ettipovivienda,etperiodo,etpaises,ettipoloctra,etpertraotroing,etTrabRefPer,etCredcon,etcolonia,etcoltra,etcolaval,etcolrefam,etCp,etCptra,etcpaval,etcprefam,
            etEntidad,etEntidadtra,etEntidadaval,etEntidadrefam,etMunicipio,etmuntra,etmunaval,etmunrefam,etcpdato,etotraocup;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] Titulos = {"¿Cuál es tu sexo?", "¿Trabaja?", "Parentesco", "Ocupación", "¿Cuál es tu estado civil?", "Tipo de Vivienda"
                , "Periodo", "Domicilio Adicional...", "Nacionalidad", "Localidad", "TIENE CREDITOS CON", "Colonias", "Estados", "Municipios"};
        Bundle mibun = this.getArguments();
        String gg = mibun.getString("dd");
        helper help = new helper(getActivity());
        //datos = new Datos();
        etCp = (EditText) getActivity().findViewById(R.id.etCp);
        //datoColFil = help.ColoniasFiltradas(etCp.getText().toString());
        String[] arra;
        etotraocup = (EditText) getActivity().findViewById(R.id.etExtra);
        etocupacion = (EditText) getActivity().findViewById(R.id.etOcupacion);
        etcolaval = (EditText) getActivity().findViewById(R.id.etColoniaAvalAdi);
        etcolrefam = (EditText) getActivity().findViewById(R.id.etColoniaRefadi);
        etcprefam = (EditText) getActivity().findViewById(R.id.etCpRefAdi);
        etcpaval = (EditText) getActivity().findViewById(R.id.etCpAvalAdi);
        etMunicipio = (EditText) getActivity().findViewById(R.id.etMunicipio);
        etmunaval = (EditText) getActivity().findViewById(R.id.etMunAvalAdi);
        etmunrefam = (EditText) getActivity().findViewById(R.id.etMunicipioAdi);
        etEntidad = (EditText) getActivity().findViewById(R.id.etEntidad);
        etEntidadaval = (EditText) getActivity().findViewById(R.id.etEntAvalAdi);
        etEntidadrefam = (EditText) getActivity().findViewById(R.id.etEntidadAdi);
        datoTra = getResources().getStringArray(R.array.Trabaja);
        datocrecon = getResources().getStringArray(R.array.Creditos);
        datosex = getResources().getStringArray(R.array.SexoHM);
        datoEstadociv = getResources().getStringArray(R.array.EstadoCivil);
        datopaises = getResources().getStringArray(R.array.Paises);
        datoParentesco = getResources().getStringArray(R.array.Parentesco);
        datoTipoVivi = getResources().getStringArray(R.array.TipoVivi);
        datoPeriodo = getResources().getStringArray(R.array.Periodo);
        datolocalidades = getResources().getStringArray(R.array.Localidades);
        sexo1 = (EditText) getActivity().findViewById(R.id.etSexo);
        etciv = (EditText) getActivity().findViewById(R.id.etEstado);
        etparentesco = (EditText) getActivity().findViewById(R.id.etParentesco);
        etparentesco2 = (EditText) getActivity().findViewById(R.id.etParentescoreffam);
        etparentesco3 = (EditText) getActivity().findViewById(R.id.etParentescorefper);
        ettipovivienda = (EditText) getActivity().findViewById(R.id.etTipoVivienda);
        etperiodo = (EditText) getActivity().findViewById(R.id.etPeriodo);
        etcolonia = (EditText) getActivity().findViewById(R.id.etColonia);
        if (gg == "sex") {
            titulo = Titulos[0];
            i = datosex;
            // e = datoSex;
            etdato = sexo1;
        } else if (gg == "tra") {
            titulo = Titulos[1];
            i = datoTra;
            etdato = ettrabaja;
        } else if (gg == "etciv") {
            titulo = Titulos[4];
            i = datoEstadociv;
            etdato = etciv;
        } else if (gg == "tipvi") {
            titulo = Titulos[5];
            i = datoTipoVivi;
            etdato = ettipovivienda;
        } else if (gg == "peri") {
            titulo = Titulos[6];
            i = datoPeriodo;
            etdato = etperiodo;
        }else if (gg == "trp") {
            titulo = Titulos[1];
            i = datoTra;
            etdato = etTrabRefPer;
        }else if (gg == "ocupacion") {
            datoOcu = help.CargarCatalagos(datoocu);
            dato1 = datoOcu.size();
            arra = new String[dato1];

            for (int k = 0; k < dato1; k++) {
                arra[k] = datoOcu.get(k);
            }
            titulo = Titulos[3];
            i = arra;
            etdato = etocupacion;
        }

        //Colonias ***********************************************************************************************
        else if (gg == "col" || gg == "coltra" || gg == "colaval" || gg == "colrefam") {
            switch (gg) {
                case "col":
                    etcpdato = etCp;
                    etdato = etcolonia;
                    break;
                case "coltra":
                    etcpdato = etCptra;
                    etdato = etcoltra;
                    break;
                case "colaval":
                    etcpdato = etcpaval;
                    etdato = etcolaval;
                    break;
                case "colrefam":
                    etcpdato = etcprefam;
                    etdato = etcolrefam;
                    break;
            }
            if (etcpdato.getText().toString().equals("")) {
                datoCol = help.CargarCatalagos(datocol);
                datoCol.add(0,"No se encontró");
                dato1 = datoCol.size();
                arra = new String[dato1];
                for (int k = 0; k < dato1; k++) {
                    arra[k] = datoCol.get(k);
                }
                titulo = Titulos[11];
                i = arra;
            } else {
                datoColFil = help.ColoniasFiltradas(etcpdato.getText().toString());
                datoColFil.add(0,"No se encontró");
                dato1 = datoColFil.size();
                arra = new String[dato1];
                for (int k = 0; k < dato1; k++) {
                    arra[k] = datoColFil.get(k);
                }
                titulo = Titulos[11];
                i = arra;
            }
            //Colonias ****************************************************************************************************************************
            //Estados ******************************************************************************************************************************
        } else if (gg == "estado" || gg == "estadotra" || gg == "estadoaval" || gg == "estadorefam") {
            datoEst = help.CargarCatalagos(datoest);
            dato1 = datoEst.size();
            arra = new String[dato1];
            for (int k = 0; k < dato1; k++) {
                arra[k] = datoEst.get(k);
            }
            titulo = Titulos[12];
            i = arra;
            if (gg == "estado") {
                etdato = etEntidad;
            } else if (gg == "estadotra") {
                etdato = etEntidadtra;
            } else if (gg == "estadoaval") {
                etdato = etEntidadaval;
            } else if (gg == "estadorefam") {
                etdato = etEntidadrefam;
            }
            //etdato = etEntidad;
            //Estados ****************************************************************************************************************************
            //Municipios ****************************************************************************************************************************
        } else if (gg == "municipio" || gg == "muntra" || gg == "munaval" || gg == "munfam") {
            switch (gg) {
                case "municipio":
                    estaid = help.Obteneridestado(etEntidad.getText().toString());
                    etdato = etMunicipio;
                    break;
                case "muntra":
                    estaid = help.Obteneridestado(etEntidadtra.getText().toString());
                    etdato = etmuntra;
                    break;
                case "munaval":
                    estaid = help.Obteneridestado(etEntidadaval.getText().toString());
                    etdato = etmunaval;
                    break;
                case "munfam":
                    estaid = help.Obteneridestado(etEntidadrefam.getText().toString());
                    etdato = etmunrefam;
                    break;
            }
            datoMun = help.MunicipiosFiltrados(estaid);
            dato1 = datoMun.size();
            arra = new String[dato1];
            for (int k = 0; k < dato1; k++) {
                arra[k] = datoMun.get(k);
            }
            titulo = Titulos[13];
            i = arra;
            //Parentescos *********************************************************************************************************************
        } else if (gg == "paren" || gg == "paren2" || gg == "paren3") {
            datoPar = help.CargarCatalagos(datopar);
            dato1 = datoPar.size();
            arra = new String[dato1];
            for (int k = 0; k < dato1; k++) {
                arra[k] = datoPar.get(k);
            }
            titulo = Titulos[2];
            i = arra;
            if (gg == "paren") {
                etdato = etparentesco;
            } else if (gg == "paren2") {
                etdato = etparentesco2;
            } else if (gg == "paren3") {
                etdato = etparentesco3;
            }}

            // Parentescos **********************************************************************************************************************
            //Municipios ************************************************************************************************************************
                builder = new AlertDialog.Builder(getContext());
                builder.setTitle(titulo)
                        .setSingleChoiceItems(i, UNSELECTED, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selected = which;
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (selected != UNSELECTED) {
                                    dato = i[selected];
                                    Log.i("i: ", String.valueOf(i));

                                    etdato.setText(dato);
                                    switch (gg) {
                                        case "ocupacion":
                                            if (etdato.getText().toString().equals("OTRO...")) {
                                                etotraocup.setVisibility(View.VISIBLE);
                                            } else {
                                                etotraocup.setVisibility(View.GONE);
                                            }
                                            break;
                                    }

                                }
                            }
                        });

                return builder.create();

        }

}