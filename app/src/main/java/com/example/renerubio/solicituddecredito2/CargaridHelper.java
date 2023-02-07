package com.example.renerubio.solicituddecredito2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class CargaridHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "solicitudcredito.sqlite";
    //private static final int DATABASE_VERSION = 1;
    private static final int DATABASE_VERSION = 2;

    public SQLiteDatabase db;
    Context ctx;
    List<String> datos;
    String columna;
    String Tabla;
    int i;
    int bandera = -1;
    String columnsurl[] = new String[]{"_id","solicitud_id","rol_id","url"};

    String Tabla1 ="Asentamientos",Tabla2 = "Parentescos",Tabla3 = "Ocupaciones",Tabla4 = "Estados",Tabla5 = "Municipios",Tabla6 = "Categorias",
            Tabla7 = "Solicitante",Tabla8 = "CatColonias",Tabla9 = "CatCategoria",Tabla10 = "UrlImagen";
    Cursor c;
    String colum = "Parentesco_id";
    String colum10[] = new String[]{"Parentesco_id"};
    String data1 [];
    String colum20 [] = new String[]{"Estado_id"};
    String colum22 [] = new String[]{"Ocupacion_id"};
    String colum23 [] = new String[]{"colonia_id"};
    String colum30 [] = new String[]{"_id"};
    String colum31 [] = new String[]{"categoria"};
    String colum32 [] = new String[]{"Categoria"};
    String colum2 = "_id";
    String colum3 = "Estado_id";
    String colum4 = "colonia_id";
    String data;
    String argu;

    public CargaridHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

        if(DATABASE_VERSION < 2 ){
            setForcedUpgrade(2);
        }

        db = getWritableDatabase();
        //onUpgrade(db,1,2);
        ctx = context;

    }
    /*
    @Override
    public void onUpgrade(SQLiteDatabase db, int DATABASE_VERSION, int NewDATABASE_VERSION) {
        super.setForcedUpgrade(NewDATABASE_VERSION);
        super.onUpgrade(db, DATABASE_VERSION, 3);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int DATABASE_VERSION, int NewDATABASE_VERSION) {
        if (DATABASE_VERSION != NewDATABASE_VERSION) {
            ctx.deleteDatabase(DATABASE_NAME);
            new CargaridHelper(ctx);
        }else
            super.onUpgrade(db, DATABASE_VERSION, NewDATABASE_VERSION);
    }*/
    public String ObtenerMuniid(String arg){
        String colName = "Municipio";
        String dato;
        String[] vals = { arg };
        String query = "SELECT _id" + " FROM " + Tabla5 + " WHERE " + colName + " = ?";
        c = db.rawQuery(query, vals);
        c.moveToFirst();
        dato = c.getString(0);
        c.close();
        return dato;
    }
    public String Obtenerid(String arg){
        String colName = "Municipio";
        String dato;
        String[] vals = { arg };
        String query = "SELECT _id" + " FROM " + Tabla5 + " WHERE " + colName + " = ?";
        c = db.rawQuery(query, vals);
        c.moveToFirst();
        dato = c.getString(0);
        c.close();
        return dato;
    }

    public String ObtenerDatosid(String arg,int identifi){
        datos = new ArrayList<>();
        switch (identifi){
            case 1://parentescos
                Tabla = Tabla2;
                data1 = colum10;
                argu = "Parentesco";
                break;
            case 2://municipios
                Tabla = Tabla5;
                data1 = colum30;
                argu = "Municipio";
                break;
            case 3://Estados
                Tabla = Tabla4;
                data1 = colum20;
                argu = "Estado";
                break;
            case 4://Colonias
                Tabla = Tabla8;
                data1 = colum23;
                argu = "colonia";
                break;
            case 5://Ocupaciones
                Tabla = Tabla3;
                data1 = colum22;
                argu = "Ocupacion";
                break;
            case 6://Categorias
                Tabla = Tabla9;
                data1 = colum31;
                argu = "solicitud_id";
                break;
            case 7://Categorias
                Tabla = Tabla6;
                data1 = colum32;
                argu = "Nombre";
                break;


        }
        /*
        String dato;
        String[] vals = { arg };
        String query = "SELECT "+data + " FROM " + Tabla + " WHERE " + argu + " = ?";
        c = db.rawQuery(query, vals);
        c.moveToFirst();
        dato = c.getString(0);
        c.close();
        //db.close();
        return dato;
        */

        String dato="";
        String tabla1= "Solicitante";
        //filtrar resultados
        try {
            c =db.query(
                    Tabla,
                    data1,
                    argu+"=?",
                    new String[]{arg},
                    null,
                    null,
                    null
            );
            c.moveToFirst();
            dato = c.getString(0);
        } catch(Exception e){
            e.printStackTrace();
            //Toast.makeText(ctx, "NO se cargaron los pendientes", Toast.LENGTH_LONG).show();
        }
        c.close();
        return dato;

    }
    public void EliminarSolicitud(String solicitud_id){
        String query;
        //Eliminar tabla solicitante
        query = "DELETE FROM Solicitante WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar tabla Dom_cliente
        query = "DELETE FROM Dom_Cliente WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar tabla Datos_Lab
        query = "DELETE FROM Datos_lab WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar tabla Aval
        query = "DELETE FROM Aval WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar tabla Dom_Aval
        query = "DELETE FROM Dom_Aval WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar tabla Ref_Fam
        query = "DELETE FROM Ref_Fam WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar tabla Dom_RefFam
        query = "DELETE FROM Dom_RefFam WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar tabla Ref_Per
        query = "DELETE FROM Ref_Per WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar categorias
        query = "DELETE FROM CatCategoria WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);
        //Eliminar UrlImagen
        query = "DELETE FROM UrlImagen WHERE solicitud_id = " + solicitud_id;
        db.execSQL(query);

    }
    public int getSolicitud_id(){
        int rows = 0;
        try {
            String query = "SELECT _id  FROM Solicitante WHERE _id =(SELECT MAX(_id) FROM Solicitante)";
            c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                rows = c.getInt(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        c.close();
        return  rows;
    }
    public int getContador(){
        int rows = 0;
        try {
            String query = "SELECT Contador  FROM Contador WHERE Contador =(SELECT MAX(Contador) FROM Contador)";
            c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                rows = c.getInt(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        c.close();
        return  rows;
    }
    public String GuardarUrl(String url,int rol_id,int sol_id){

        String path="";
        int dato = sol_id + 1;
        String rolid = String.valueOf(rol_id);
        String data = String.valueOf(dato);
        if (ExisteIma(dato,Tabla10,rol_id)){
            path =ActualizarImagen(rolid,url,data);
        }else {
            ContentValues values = new ContentValues();
            values.put(columnsurl[1].toString(), data);
            values.put(columnsurl[2].toString(), rolid);
            values.put(columnsurl[3].toString(), url);
            try {
                long newRowid = db.insert(Tabla10, null, values);
                Log.i("insertado: ", String.valueOf(newRowid));
                //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //db.close();
        return path;
    }
    private boolean ExisteIma(int datoid,String Tabla,int rolid) {
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla
                        + " WHERE  solicitud_id  = " + datoid + " AND rol_id = " + rolid;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();
        return existe;
    }
    private String ActualizarImagen(String rolid,String url,String solid){
        ContentValues values = new ContentValues();
        String sol = "solicitud_id";
        String rol = "rol_id";
        //para traerse la url de la imagen cuando se quiera eliminar//
        String selectQuery =
                " SELECT url"
                        + " FROM " + Tabla10
                        + " WHERE  solicitud_id  = " + solid + " AND rol_id = " + rolid;
        c = db.rawQuery(selectQuery, null);
        c.moveToFirst();
        String nada = c.getString(0);
        //para traerse la url de la imagen cuando se quiera eliminar//
        //deleteFileRecursively(nada);

        values.put(columnsurl[3].toString(),url);
        //Actualizamos el registro en la base de datos
        db.update(Tabla10, values,  "solicitud_id = " + solid + " AND rol_id = " + rolid, null);


        return nada;

    }
    public List<List<String>> getImagenesurl(int datoid){
        List <List<String>> datos = new ArrayList<>();
        List <String> data ;
        String selectQuery =
                " SELECT *"
                        + " FROM " + Tabla10
                        + " WHERE  solicitud_id  = " + datoid;
        c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()) {
            do {
                data = new ArrayList<>();
                data.add(c.getString(2));
                data.add(c.getString(3));
                datos.add(data);
            } while (c.moveToNext());
            c.close();
        }
        return  datos;
    }

}
