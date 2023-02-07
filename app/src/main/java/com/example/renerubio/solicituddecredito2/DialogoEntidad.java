package com.example.renerubio.solicituddecredito2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.EditText;

public class DialogoEntidad extends AppCompatDialogFragment {

    private static final int UNSELECTED = -1;
    private int selected;
    String dato;
    String []datoentidad,i;
    EditText EntidadFe,DelMun,EntidadFe2,etdato,EntidadFeTra,EntidadAvalAdi;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle mibun = this.getArguments();
        String gg= mibun.getString("dd");
        datoentidad = getResources().getStringArray(R.array.Entidad);
        EntidadFe =(EditText)getActivity().findViewById(R.id.etEntidad);
        EntidadFe2 =(EditText)getActivity().findViewById(R.id.etEntidadAdi);
        EntidadAvalAdi =(EditText)getActivity().findViewById(R.id.etEntAvalAdi);
        //DelMun=(EditText)getActivity().findViewById(R.id.etMunicipio);
        //DelMun.setText("");

        if(gg == "ent1") {
            i = datoentidad;
            etdato = EntidadFe;
        }else if(gg == "ent2"){
            i = datoentidad;
            etdato = EntidadFe2;
        }else if(gg == "entavaadi"){
        i = datoentidad;
        etdato = EntidadAvalAdi;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("ENTIDAD FEDERATIVA")
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
                                etdato.setText(dato);

                        }
                    }
                });

        return builder.create();
    }
}
