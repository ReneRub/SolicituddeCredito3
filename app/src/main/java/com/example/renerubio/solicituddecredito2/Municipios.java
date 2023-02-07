package com.example.renerubio.solicituddecredito2;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.EditText;
import android.widget.Toast;

public class Municipios extends AppCompatDialogFragment {
    private static final int UNSELECTED = -1;
    private int selected;
    String dato;
    EditText DelMun,EntidadFed,etdato,etMunAdi,EntidadFedAdi,etdatomun,etMunAvaAdi,
    EntAvaAdi;
    String [] datomusin,datomudur,datomunay,datomujal,datomuson;
    String [] da;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle mibun = this.getArguments();
        String gg= mibun.getString("dd");
        datomusin = getResources().getStringArray(R.array.MunSin);
        datomudur = getResources().getStringArray(R.array.MunDur);
        datomunay = getResources().getStringArray(R.array.MunNar);
        datomujal = getResources().getStringArray(R.array.MunJal);
        datomuson = getResources().getStringArray(R.array.MunSon);
        DelMun =(EditText)getActivity().findViewById(R.id.etMunicipio);
        etMunAdi =(EditText)getActivity().findViewById(R.id.etMunicipioAdi);
        etMunAvaAdi =(EditText)getActivity().findViewById(R.id.etMunAvalAdi);
        EntidadFed =(EditText)getActivity().findViewById(R.id.etEntidad);
        EntidadFedAdi =(EditText)getActivity().findViewById(R.id.etEntidadAdi);
        EntAvaAdi =(EditText)getActivity().findViewById(R.id.etEntAvalAdi);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        DelMun.setText("");
        if(gg == "mun") {
            etdato = EntidadFed;
            etdatomun = DelMun;
        }else if(gg == "munadi"){
            etdato = EntidadFedAdi;
            etdatomun = etMunAdi;
        }else if(gg == "munavaadi"){
            etdato = EntAvaAdi;
            etdatomun = etMunAvaAdi;
        }
        if(etdato.getText().toString().equals("SINALOA")){
            da = datomusin;
        }else{
            if(etdato.getText().toString().equals("DURANGO")){
                da = datomudur;
            }else{
                if(etdato.getText().toString().equals("NAYARIT")) {
                    da = datomunay;
                }else{
                    if(etdato.getText().toString().equals("SONORA")){
                        da = datomuson;
                    }else{
                        if(etdato.getText().toString().equals("JALISCO")){
                            da = datomujal;
                        }
                    }
                }
            }
        }
        builder.setTitle("DELEGACION/MUNICIPIO")
                .setSingleChoiceItems(da, UNSELECTED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected = which;
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(etdato.getText().toString().equals("")) {
                            Toast.makeText(getContext(),"Primero debes de seleccionar el estado",Toast.LENGTH_SHORT).show();
                            }
                             else {
                            if (selected != UNSELECTED) {
                                //Toast.makeText(getContext(), brands[selected], Toast.LENGTH_SHORT).show();
                                dato = da[selected];
                                etdatomun.setText(dato);
                            }
                        }
                    }
                });

        return builder.create();
    }
}
