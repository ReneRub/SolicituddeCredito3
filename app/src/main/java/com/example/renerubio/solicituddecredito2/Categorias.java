package com.example.renerubio.solicituddecredito2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Categorias extends AppCompatDialogFragment {

    String [] listItems;
    boolean [] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    TextView tvCategoria;
    helper helpcat;
    String datosest = "Categorias";
    List<String> datoCat = new ArrayList<>();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        helpcat = new helper(getActivity());
        tvCategoria = (TextView)getActivity().findViewById(R.id.tvCategorias);
        datoCat = helpcat.CargarCatalagos(datosest);
        listItems = new String[datoCat.size()];
        listItems = datoCat.toArray(listItems);
        //listItems = getResources().getStringArray(R.array.Categorias1);

        checkedItems = new boolean[listItems.length];
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(getContext());
        mbuilder.setTitle("Categorias Disponibles");
        mbuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                if(isChecked){
                    if(!mUserItems.contains(which)){
                        mUserItems.add(which);
                    }else{
                        mUserItems.remove(which);
                    }
                }else if(mUserItems.contains(which)){
                    mUserItems.remove((Integer) which);
                }
            }
        });
        mbuilder.setCancelable(false);
        mbuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String item = "";
                for(int i=0; i<mUserItems.size(); i++){
                    item = item + listItems [mUserItems.get(i)];
                    if(i != mUserItems.size() -1){
                        //item = item + ",\n";
                        item = item + ",";
                    }
                }
                tvCategoria.setText(item);
            }
        });
        mbuilder.setNeutralButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        mbuilder.setNegativeButton("ELIMINAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
                for (int i =0; i<checkedItems.length;i++){
                    checkedItems[i]=false;
                    mUserItems.clear();
                    tvCategoria.setText("");
                }
            }
        });
        AlertDialog mDialog = mbuilder.create();
        mDialog.show();
        return mDialog;

    }

}