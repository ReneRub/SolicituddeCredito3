package com.example.renerubio.solicituddecredito2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class DialogoAyuda extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater imagenAyuda = LayoutInflater.from(getContext());
        final View vista = imagenAyuda.inflate(R.layout.imagenayuda,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        //builder.setIcon(R.drawable.agre);
        //builder.setTitle("AYUDA");
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        AlertDialog alert=builder.create();
        alert.setView(vista);
        alert.show();

        return  alert;
    }
}
