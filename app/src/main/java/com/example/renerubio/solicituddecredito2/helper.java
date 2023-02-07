package com.example.renerubio.solicituddecredito2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.renerubio.solicituddecredito2.Model.Categoria;
import com.example.renerubio.solicituddecredito2.Model.Colonia;
import com.example.renerubio.solicituddecredito2.Model.Estado;
import com.example.renerubio.solicituddecredito2.Model.Localidad;
import com.example.renerubio.solicituddecredito2.Model.Municipio;
import com.example.renerubio.solicituddecredito2.Model.Ocupacion;
import com.example.renerubio.solicituddecredito2.Model.Parentesco;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class helper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "solicitudcredito.sqlite";
    //private static final int DATABASE_VERSION = 1;
    private static final int DATABASE_VERSION = 2;



    public SQLiteDatabase db;
    private boolean resp = false;
    Context ctx;
    String columnadatos [];
    String columns[] = new String[]{"_id","solicitud_id","apellidopat","apellidomat","nombre"};
    String columnscol[] = new String[]{"ciudad_id","colonia_id","colonia","cp"};
    String columnsase[] = new String[]{"Asentamiento_id","Asentamiento"};
    String columnspar[] = new String[]{"Parentesco_id","Parentesco"};
    String columnsocu[] = new String[]{"Ocupacion_id","Ocupacion"};
    String columnsesta[] = new String[]{"Estado_id","Estado"};
    String columnsmun[] = new String[]{"Estado_id","Municipio"};
    String columnscat[] = new String[]{"Categoria","Nombre"};

    String Tabla="CatColonias",Tabla1 ="Asentamientos",Tabla2 = "Parentescos",Tabla3 = "Ocupaciones",Tabla4 = "Estados",Tabla5 = "Municipios",Tabla6 = "Categorias",
    Tabla7 = "Solicitante",Tabla8 = "CatCategoria";
    Cursor c;
    int Contador1;
    boolean bandera = false;

    public helper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        //setForcedUpgrade(2);
        if(DATABASE_VERSION < 2 ){
            setForcedUpgrade(2);
        }



        //onUpgrade(db,1,2);

        //setForcedUpgrade();
        db = getWritableDatabase();

        ctx = context;

    }

     /*
    @Override
    public void onUpgrade(SQLiteDatabase db, int DATABASE_VERSION, int NewDATABASE_VERSION) {
        if (DATABASE_VERSION != NewDATABASE_VERSION) {
            ctx.deleteDatabase(DATABASE_NAME);
            new helper(ctx);
        }else
            super.onUpgrade(db, DATABASE_VERSION, NewDATABASE_VERSION);
    }*/

    public void Guardar(EditText nombre, EditText apepat, EditText apemat, EditText fecnac, EditText sexo, EditText estciv,
                        EditText curp, EditText tipviv, AutoCompleteTextView ocupacion, EditText ingresos, EditText periodo, CheckBox cbtrabajasi,CheckBox cbtrabajano){

        String columns[] = new String[]{"_id","nombre","apellidopat","apellidomat","fechanac","sexo","estadociv","curp","tipoviv"};
        String usu = "DatosPersonales";
        String columns2[] = new String[]{"_id","ocupacion","ingresostot","periodo","trabaja"};
        String usutra = "DatosLaborales";
        String trabaja = "";
        String nombrestr= nombre.getText().toString();
        String apepatstr = apepat.getText().toString();
        String apematstr = apemat.getText().toString();
        nombrestr = nombrestr.toUpperCase();
        apepatstr = apepatstr.toUpperCase();
        apematstr = apematstr.toUpperCase();

        if(cbtrabajasi.isChecked()){
            trabaja = "SI";
        }
        else{
            if(cbtrabajano.isChecked()){
                trabaja = "NO";
            }
        }
        ContentValues values = new ContentValues();
        values.put(columns[1].toString(),nombrestr);
        values.put(columns[2].toString(),apepatstr);
        values.put(columns[3].toString(),apematstr);
        values.put(columns[4].toString(),fecnac.getText().toString());
        values.put(columns[5].toString(),sexo.getText().toString());
        values.put(columns[6].toString(),estciv.getText().toString());
        values.put(columns[7].toString(),curp.getText().toString());
        values.put(columns[8].toString(),tipviv.getText().toString());

        ContentValues values2 = new ContentValues();
        values2.put(columns2[1].toString(),ocupacion.getText().toString());
        values2.put(columns2[2].toString(),ingresos.getText().toString());
        values2.put(columns2[3].toString(),periodo.getText().toString());
        values2.put(columns2[4].toString(),trabaja);

        try {

            long newRowid = db.insert(usu, null, values);
            long newRowid2 = db.insert(usutra, null, values2);
            Toast.makeText(ctx, "Registro exitoso de " +
                    nombre.getText().toString()+" "+ apepat.getText().toString(), Toast.LENGTH_LONG).show();
            System.out.print("registro exitoso");
           // db.close();
        }catch(Exception e) {
            e.printStackTrace();

            nombre.setText("");
        }

    }
    public List<String> ObtenerCat(String arg){
        List <String> datos = new ArrayList<>();
        String columnas[] = new String[]{"categoria"};
        String data = "solicitud_id";
        Cursor c = null;
        try {
            c = db.query(
                    Tabla8,
                    columnas,
                    data+"=?",
                    new String[]{arg},
                    null,
                    null,
                    null
            );
            c.moveToFirst();
            do {
                String dato = c.getString(0);
                datos.add(dato);
            }while (c.moveToNext());
            c.close();
        } catch(Exception e){
            Toast.makeText(ctx, "NO se cargo el catalago", Toast.LENGTH_LONG).show();
        }
        return (datos);
    }
    //************************************************************************************************
    public List<String> ColoniasFiltradas(String arg){
        List <String> datos = new ArrayList<>();
        String columnas[] = new String[]{"_id","colonia"};
        String data = "cp";
        Cursor c = null;
        try {
            c = db.query(
                    Tabla,
                    columnas,
                    data+"=?",
                    new String[]{arg},
                    null,
                    null,
                    null
            );
            c.moveToFirst();
            do {
                String dato = c.getString(1);
                datos.add(dato);
            }while (c.moveToNext());
            c.close();
        } catch(Exception e){
            Toast.makeText(ctx, "NO se cargo el catalago", Toast.LENGTH_LONG).show();
        }
        return (datos);
    }

    public List<String> MunFiltrados(String arg){
        List <String> datos = new ArrayList<>();
        String columnas[] = new String[]{"_id","Municipio"};
        String data = "Estado_id";
        Cursor c = null;
        try {
            c = db.query(
                    Tabla5,
                    columnas,
                    data+"=?",
                    new String[]{arg},
                    null,
                    null,
                    null
            );
            c.moveToFirst();
            do {
                String dato = c.getString(1);
                datos.add(dato);
            }while (c.moveToNext());
            c.close();
        } catch(Exception e){
            Toast.makeText(ctx, "NO se cargo el catalago", Toast.LENGTH_LONG).show();
        }
        return (datos);
    }
    //************************************************************************************************
    //Obtener Municipios***********************************************************
    public String Obteneridestado(String arg){
        String Testado = "Estados";
        String colName = "Estado";
        String dato;
        String[] vals = { arg };
        String query = "SELECT Estado_id" + " FROM " + Testado + " WHERE " + colName + " = ?";
        c = db.rawQuery(query, vals);
        c.moveToFirst();
        dato = c.getString(0);
        c.close();
        return dato;
    }
    // Filtrar Municipios,**************************************************************************
    public List<String> MunicipiosFiltrados(String arg){
        List <String> datos = new ArrayList<>();
        String columnas[] = new String[]{"_id","Municipio"};
        String data = "Estado_id";
        Cursor c = null;
        try {
            c = db.query(
                    Tabla5,
                    columnas,
                    data+"=?",
                    new String[]{arg},
                    null,
                    null,
                    null
            );
            c.moveToFirst();
            do {
                String dato = c.getString(1);
                datos.add(dato);
            }while (c.moveToNext());
            c.close();
        } catch(Exception e){
            Toast.makeText(ctx, "NO se cargo el catalago", Toast.LENGTH_LONG).show();
        }
        db.close();
        return (datos);
    }
    //Cargar Catalagos *************************************************************************************
    public List<String> CargarCatalagos(String tabladato){
        String tabla= "";
        String campo = "";

        if(tabladato.equals("Ocupaciones")){
            tabla = "Ocupaciones";
            campo = "Ocupacion";
        }else{
            if(tabladato.equals("Asentamientos")){
                tabla = "Asentamientos";
                campo = "Asentamiento";
            }else{
                if (tabladato.equals("Parentescos")){
                    tabla = "Parentescos";
                    campo = "Parentesco";
                }else{
                    if (tabladato.equals("CatColonias")){
                        tabla = "CatColonias";
                        campo = "colonia";
                    }else{
                        if(tabladato.equals("Estados")){
                            tabla = "Estados";
                            campo = "Estado";
                        }else {
                            if(tabladato.equals("Categorias")){
                                tabla = "Categorias";
                                campo = "Nombre";
                            }
                        }
                    }
                }
            }
        }
        List <String> datos = new ArrayList<>();
        // define que se va a mostrar
        String columnas[] = new String[]{"_id",campo.toString()};
        //filtrar resultados
        Cursor c = null;
            try {
                c = db.query(
                        tabla,
                        columnas,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                c.moveToFirst();
                do {
                    String dato = c.getString(1);

                    //datos.add(new Estado(c.getInt(0),c.getString(1)));
                    datos.add(dato);
                }while (c.moveToNext());
                c.close();
            } catch(Exception e){
                Toast.makeText(ctx, "NO se cargo el catalago", Toast.LENGTH_LONG).show();
            }
            db.close();
        return (datos);
    }
    //Cargar Catalagos ******************************************************************************+
    //Cargar Pendientes ******************************************************************************
    public Cursor CargarPendientes(){
        String tabla1= "Solicitante";
        //filtrar resultados
        Cursor c = null;
        try {
            c = db.query(
                    tabla1,
                    columns,
                    null,
                    null,
                    null,
                    null,
                    null
            );

        } catch(Exception e){
            Toast.makeText(ctx, "NO se cargaron los pendientes", Toast.LENGTH_LONG).show();
        }
        return c;
    }
    public int NumeroPendientes(){

        Cursor c = db.rawQuery("select * from Solicitante",null);
        int dato = c.getCount();
        c.close();
        return dato;
    }
    //Cargar Pendientes ******************************************************************************
    //Cargar Pendientes2**************************************************************************************
    public List<List<String>> CargarPendientes2(){
        String tabla = "Solicitante";
        List <List<String>> datos = new ArrayList<>();
        List <String> data ;
        String selectQuery = "SELECT _id,solicitud_id,apellidopat,apellidomat,nombre FROM Solicitante";
        c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()) {
            do {
                data = new ArrayList<>();
                data.add(c.getString(0));
                data.add(c.getString(1));
                data.add(c.getString(2));
                data.add(c.getString(3));
                data.add(c.getString(4));
                datos.add(data);
            } while (c.moveToNext());
            c.close();
        }
        return  datos;
    }

    //Cargar Pendientes2**************************************************************************************
    //Guardar colonias******************************************************************************
    public void GuardarColonias(List<Colonia>lista) {
        try {
            ArrayList<Colonia> ColoniasNuevas = new ArrayList<>();
            //update
            for(Colonia col: lista) {
                if(ExisteCol(col.getColonia_id(),Tabla)) {
                    ActualizarColonias(col);
                } else {
                    ColoniasNuevas.add(new Colonia(
                            col.getCiudad_id()
                            ,col.getColonia_id()
                            , col.getColonia()
                            ,col.getCp()
                    ));
                }
            }
            for(Colonia colonia : ColoniasNuevas){
                ContentValues values = new ContentValues();
                values.put(columnscol[0].toString(),colonia.getCiudad_id());
                values.put(columnscol[1].toString(),colonia.getColonia_id());
                values.put(columnscol[2].toString(),colonia.getColonia());
                values.put(columnscol[3].toString(),colonia.getCp());
                try {
                    long newRowid = db.insert(Tabla, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
           // db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }
        Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
    }

    public void GuardarColonias2(List<Colonia>lista) {
        String query = "DELETE FROM CatColonias";
        db.execSQL(query);
        try {
            ArrayList<Colonia> ColoniasNuevas = new ArrayList<>();
            //update
            for(Colonia col: lista) {

                    ColoniasNuevas.add(new Colonia(
                            col.getCiudad_id()
                            ,col.getColonia_id()
                            , col.getColonia()
                            ,col.getCp()
                    ));

            }
            for(Colonia colonia : ColoniasNuevas){
                ContentValues values = new ContentValues();
                values.put(columnscol[0].toString(),colonia.getCiudad_id());
                values.put(columnscol[1].toString(),colonia.getColonia_id());
                values.put(columnscol[2].toString(),colonia.getColonia());
                values.put(columnscol[3].toString(),colonia.getCp());
                try {
                    long newRowid = db.insert(Tabla, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                    //resp = true;
                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(ctx, "Se Sincronizaron las colonias", Toast.LENGTH_LONG).show();

            // db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }
        lista.clear();
        //return resp;
    }
    //GuardarColonias*********************************************************************************************************************************
    //Guardar localidades*******************************************************************************************
    public void GuardarLocalidades(List<Localidad>lista) {
        try {
            ArrayList<Localidad> LocalidadesNuevas = new ArrayList<>();
            //update
            for(Localidad loc: lista) {
                if(ExisteLoc(loc.getAsentaiento_id(),Tabla1)) {
                    ActualizarLocalidades(loc);
                } else {
                    LocalidadesNuevas.add(new Localidad(
                            loc.getAsentaiento_id()
                            ,loc.getAsentamiento()
                    ));
                }
            }
            for(Localidad localidad : LocalidadesNuevas){
                ContentValues values = new ContentValues();
                values.put(columnsase[0].toString(),localidad.getAsentaiento_id());
                values.put(columnsase[1].toString(),localidad.getAsentamiento());
                try {
                    long newRowid = db.insert(Tabla1, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
           // db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }
    }
    //Guardar Localidades*******************************************************************************************

    //Guardar Ocupaciones*******************************************************************************************
    public void GuardarOcupaciones(List<Ocupacion>lista) {
        try {
            ArrayList<Ocupacion> OcupacionesNuevos = new ArrayList<>();
            //update
            for(Ocupacion ocupa: lista) {
                if(ExisteOcu(ocupa.getOcupacion_id(),Tabla3)) {
                    ActualizarOcupaciones(ocupa);
                } else {
                    OcupacionesNuevos.add(new Ocupacion(
                            ocupa.getOcupacion_id()
                            ,ocupa.getOcupacion()
                    ));
                }
            }
            for(Ocupacion ocupacion : OcupacionesNuevos){
                ContentValues values = new ContentValues();
                values.put(columnsocu[0],ocupacion.getOcupacion_id());
                values.put(columnsocu[1],ocupacion.getOcupacion());
                try {
                    long newRowid = db.insert(Tabla3, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
           // db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }
    }
    public void GuardarOcupaciones2(List<Ocupacion>lista) {
        String query = "DELETE FROM Ocupaciones";
        db.execSQL(query);
        try {
            ArrayList<Ocupacion> OcupacionesNuevos = new ArrayList<>();
            //update
            for(Ocupacion ocupa: lista) {

                    OcupacionesNuevos.add(new Ocupacion(
                            ocupa.getOcupacion_id()
                            ,ocupa.getOcupacion()
                    ));
            }
            for(Ocupacion ocupacion : OcupacionesNuevos){
                ContentValues values = new ContentValues();
                values.put(columnsocu[0],ocupacion.getOcupacion_id());
                values.put(columnsocu[1],ocupacion.getOcupacion());
                try {
                    long newRowid = db.insert(Tabla3, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(ctx, "Se sincronizaron Ocupaciones", Toast.LENGTH_LONG).show();
            // db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }
        lista.clear();
    }
    //Guardar Ocupaciones*******************************************************************************************
    //Guardar Parentescos*******************************************************************************************
    public void GuardarParentescos(List<Parentesco>lista) {

        try {
            ArrayList<Parentesco> ParentescosNuevos = new ArrayList<>();
            //update
            for(Parentesco paren: lista) {
                if(ExistePar(paren.getParentesco_id(),Tabla2)) {
                    ActualizarParentescos(paren);
                } else {
                    ParentescosNuevos.add(new Parentesco(
                            paren.getParentesco_id()
                            ,paren.getParentesco()
                    ));
                }
            }
            for(Parentesco parentesco : ParentescosNuevos){
                ContentValues values = new ContentValues();
                values.put(columnspar[0].toString(),parentesco.getParentesco_id());
                values.put(columnspar[1].toString(),parentesco.getParentesco());
                try {
                    long newRowid = db.insert(Tabla2, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));

                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            // db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }


    }
    public void GuardarParentescos2(List<Parentesco>lista) {
        String query = "DELETE FROM Parentescos";
        db.execSQL(query);
        /*
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla2;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();*/
            try {
                ArrayList<Parentesco> ParentescosNuevos = new ArrayList<>();
                //update
                for (Parentesco paren : lista) {

                    ParentescosNuevos.add(new Parentesco(
                            paren.getParentesco_id()
                            , paren.getParentesco()
                    ));

                }
                for (Parentesco parentesco : ParentescosNuevos) {
                    ContentValues values = new ContentValues();
                    values.put(columnspar[0].toString(), parentesco.getParentesco_id());
                    values.put(columnspar[1].toString(), parentesco.getParentesco());
                    try {
                        long newRowid = db.insert(Tabla2, null, values);
                        Log.i("insertado: ", String.valueOf(newRowid));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(ctx, "Se sincronizaron Parentescos", Toast.LENGTH_LONG).show();
                // db.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("helper: ", e.getMessage());
            }
            lista.clear();

    }

    //Guardar Parentescos*******************************************************************************************
    //Guardar Estados*******************************************************************************************
    public void GuardarEstados(List<Estado>lista) {
        try {
            ArrayList<Estado> EstadosNuevos = new ArrayList<>();
            //update
            for(Estado estado: lista) {
                if(ExisteEst(estado.getEstado_id(),Tabla4)) {
                    ActualizarEstados(estado);
                } else {
                    EstadosNuevos.add(new Estado(
                            estado.getEstado_id()
                            ,estado.getEstado()
                    ));
                }
            }
            for(Estado estado : EstadosNuevos){
                ContentValues values = new ContentValues();
                values.put(columnsesta[0].toString(),estado.getEstado_id());
                values.put(columnsesta[1].toString(),estado.getEstado());
                try {
                    long newRowid = db.insert(Tabla4, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }
    }
    //Guardar Estados****************************************************************************************
    //Guardar Municipios*******************************************************************************************
    public void GuardarMunicipios(List<Municipio>lista) {
        try {
            ArrayList<Municipio> MunicipiosNuevos = new ArrayList<>();
            //update
            for(Municipio muni: lista) {
                //if(ExisteMun(muni.getEstado_id(),Tabla5)) {
                 //   ActualizarMunicipios(muni);
                //} else {
                    MunicipiosNuevos.add(new Municipio(
                            muni.getEstado_id()
                            ,muni.getMunicipio()
                    ));
                //}
            }
            for(Municipio muni : MunicipiosNuevos){
                ContentValues values = new ContentValues();
                values.put(columnsmun[0].toString(),muni.getEstado_id());
                values.put(columnsmun[1].toString(),muni.getMunicipio());
                try {
                    long newRowid = db.insert(Tabla5, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
           // db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }
    }
    //Guardar Municipios****************************************************************************************

    //Guardar Categorias*******************************************************************************************
    public void GuardarCategorias(List<Categoria>lista) {
        /*
        try {
            ArrayList<Categoria> CategoriasNuevas = new ArrayList<>();
            //update
            for(Categoria categoria: lista) {
                if(ExisteCat(categoria.getCategoria(),Tabla6)) {
                    ActualizarCategorias(categoria);
                } else {
                    CategoriasNuevas.add(new Categoria(
                            categoria.getCategoria()
                            ,categoria.getNombre()
                    ));
                }
            }
            for(Categoria categoria : CategoriasNuevas){
                ContentValues values = new ContentValues();
                values.put(columnscat[0].toString(),categoria.getCategoria());
                values.put(columnscat[1].toString(),categoria.getNombre());
                try {
                    long newRowid = db.insert(Tabla6, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
           // db.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("helper: ",e.getMessage());
        }*/

        //**************************************************************************************************************************************************

        String query = "DELETE FROM Categorias";
        db.execSQL(query);

        try {
            ArrayList<Categoria> CategoriasNuevas = new ArrayList<>();
            //update
            for (Categoria categoria : lista) {

                CategoriasNuevas.add(new Categoria(
                        categoria.getCategoria()
                        , categoria.getNombre()
                ));

            }
            for(Categoria categoria : CategoriasNuevas){
                ContentValues values = new ContentValues();
                values.put(columnscat[0].toString(),categoria.getCategoria());
                values.put(columnscat[1].toString(),categoria.getNombre());
                try {
                    long newRowid = db.insert(Tabla6, null, values);
                    Log.i("insertado: ", String.valueOf(newRowid));
                    //Toast.makeText(ctx, "Se guardaron las colonias", Toast.LENGTH_LONG).show();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(ctx, "Se sincronizaron Categorias", Toast.LENGTH_LONG).show();
            // db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("helper: ", e.getMessage());
        }
        lista.clear();


        //*************************************************************************************************************************************************




    }
    //Guardar Categorias****************************************************************************************

    private boolean ExisteCol(int datoid,String Tabla) {
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla
                        + " WHERE  colonia_id  = " + datoid;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();
        return existe;
    }
    private boolean ExisteEst(int datoid,String Tabla) {
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla
                        + " WHERE  Estado_id  = " + datoid;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();
        return existe;
    }
    private boolean ExisteMun(int datoid,String Tabla) {
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla
                        + " WHERE  Estado_id  = " + datoid;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();
        return existe;
    }
    private boolean ExistePar(String datoid,String Tabla) {
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla
                        + " WHERE  Parentesco_id  = " + datoid;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();
        return existe;
    }
    private boolean ExisteCat(int datoid,String Tabla) {
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla
                        + " WHERE  Categoria  = " + datoid;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();
        return existe;
    }
    private boolean ExisteOcu(int datoid,String Tabla) {
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla
                        + " WHERE  Ocupacion_id  = " + datoid;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();
        return existe;
    }
    private boolean ExisteLoc(int datoid,String Tabla) {
        boolean existe = false;
        String selectQuery =
                " SELECT 1"
                        + " FROM " + Tabla
                        + " WHERE  Asentamiento_id  = " + datoid;
        c = db.rawQuery(selectQuery, null);
        if(c.getCount() > 0){
            existe = true;
        }
        c.close();
        return existe;
    }
    private void ActualizarColonias(Colonia col){
        //Establecemos los campos-valores a insertar ó actualizar
        ContentValues values = new ContentValues();
        values.put(columnscol[0].toString(),col.getCiudad_id());
        values.put(columnscol[1].toString(),col.getColonia_id());
        values.put(columnscol[2].toString(),col.getColonia());
        values.put(columnscol[3].toString(),col.getCp());
        //Actualizamos el registro en la base de datos
        db.update(Tabla, values,  "colonia_id = " + col.getColonia_id() , null);
       // db.close();
        //Toast.makeText(ctx, "Se actualizaron las colonias", Toast.LENGTH_SHORT).show();
    }
    private void ActualizarLocalidades(Localidad loc){
        ContentValues values = new ContentValues();
        values.put(columnsase[0].toString(),loc.getAsentaiento_id());
        values.put(columnsase[1].toString(),loc.getAsentamiento());
        //Actualizamos el registro en la base de datos
        db.update(Tabla2, values,  "Asentamiento_id = " + loc.getAsentaiento_id() , null);
       // db.close();
        //Toast.makeText(ctx, "Se actualizaron las colonias", Toast.LENGTH_SHORT).show();
    }
    private void ActualizarOcupaciones(Ocupacion ocu){
        ContentValues values = new ContentValues();
        values.put(columnsocu[0].toString(),ocu.getOcupacion_id());
        values.put(columnsocu[1].toString(),ocu.getOcupacion());
        db.update(Tabla3, values,  "Ocupacion_id = " + ocu.getOcupacion_id() , null);
       // db.close();
    }
    private void ActualizarParentescos(Parentesco paren){
        ContentValues values = new ContentValues();
        values.put(columnspar[0].toString(),paren.getParentesco_id());
        values.put(columnspar[1].toString(),paren.getParentesco());
        db.update(Tabla2, values,  "Parentesco_id = " + paren.getParentesco_id() , null);
       // db.close();
    }
    private void ActualizarEstados(Estado estado){
        ContentValues values = new ContentValues();
        values.put(columnsesta[0].toString(),estado.getEstado_id());
        values.put(columnsesta[1].toString(),estado.getEstado());
        db.update(Tabla4, values,  "Estado_id = " + estado.getEstado_id() , null);
       // db.close();
    }
    private void ActualizarMunicipios(Municipio municipio){
        ContentValues values = new ContentValues();
        values.put(columnsmun[0].toString(),municipio.getEstado_id());
        values.put(columnsmun[1].toString(),municipio.getMunicipio());
        db.update(Tabla5, values,  "Municipio = " + municipio.getMunicipio() , null);
       // db.close();
    }
    private void ActualizarCategorias(Categoria categoria){
        ContentValues values = new ContentValues();
        values.put(columnscat[0].toString(),categoria.getCategoria());
        values.put(columnscat[1].toString(),categoria.getNombre());
        db.update(Tabla6, values,  "Categoria = " + categoria.getCategoria() , null);
       // db.close();
    }

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




}
