package com.example.renerubio.solicituddecredito2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ManejoBBDD extends SQLiteOpenHelper {
    ArrayList<String>ListaColonias;
    ArrayList<String>ColoniasList;
    public ManejoBBDD(Context context){
        super(context,"BD_Solicitud_Credito",null,1);

        RutaAlmacenamiento = context.getFilesDir().getParentFile().getPath() + "BD_Solicitud_Credito";
    }
    public void AperturaBBDD(Context context){
        try{

            bbdd=SQLiteDatabase.openDatabase(RutaAlmacenamiento,null,SQLiteDatabase.OPEN_READONLY);

        }catch (Exception e){

            CopiarBBDD( context);
            bbdd=SQLiteDatabase.openDatabase(RutaAlmacenamiento,null,SQLiteDatabase.OPEN_READONLY);
        }
    }
    private void CopiarBBDD(Context context){
        try {
            InputStream datosEntrada = context.getAssets().open("BD_Solicitud_Credito");
            OutputStream datoSalida=new FileOutputStream(RutaAlmacenamiento);
            byte []bufferBBDD =new byte[1024];
            int Longitud;

            while ((Longitud=datosEntrada.read(bufferBBDD))>0){
                datoSalida.flush();
                datoSalida.close();
                datosEntrada.close();
            }
        }catch (Exception e){

        }
    }
    public void ObtenerColonia(int cp){

        String Colonia;
        Cursor micursor;

        micursor= bbdd.rawQuery("SELECT * FROM ocupaciones WHERE cp=" + cp,null);
        //micursor.moveToFirst();
        //Colonia = new String[Integer.parseInt(micursor.getString(2))];
        while (micursor.moveToNext()){
            Colonia = new String(micursor.getString(2));

            ListaColonias.add(Colonia);
        }
        CargarColonias();
        micursor.close();
    }
    private void CargarColonias() {
        ColoniasList= new ArrayList<String>();
        ColoniasList.add("Colonias");

        for (int i=0;i<ListaColonias.size();i++){
            //ColoniasList.add(ListaColonias.get(i).getcp());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int Viejo, int nuevo) {

    }
    String RutaAlmacenamiento;
    SQLiteDatabase bbdd;
}
