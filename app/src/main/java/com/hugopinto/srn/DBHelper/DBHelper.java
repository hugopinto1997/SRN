package com.hugopinto.srn.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.hugopinto.srn.Datos.Persona;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="bd_usuarios";
    public static final String TABLA_USUARIO="Persona";
    public static final String CAMPO_CARNET="carnet";
    public static final String CAMPO_NOTA="nota";
    public static final String CAMPO_MATERIA="materia";
    public static final String CAMPO_CATEDRATICO="catedratico";
    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO + "("+CAMPO_CARNET+" TEXT,"+ CAMPO_NOTA +" TEXT,"+ CAMPO_MATERIA +" TEXT,"+ CAMPO_CATEDRATICO +" TEXT)";

    public static  DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context ctx){
        if(myDB == null){
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DBHelper(Context context){
        super(context,DB_NAME,null,1);
        this.context= context;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_NOTA);
        onCreate(db);
    }
    public boolean add(Persona p){
        ContentValues values = new ContentValues();
        values.put(CAMPO_CARNET,p.getCarnet());
        values.put(CAMPO_NOTA,p.getNota());
        values.put(CAMPO_MATERIA,p.getMateria());
        values.put(CAMPO_CATEDRATICO,p.getCatedratico());

        db.insert(TABLA_USUARIO,null,values);
        Toast.makeText(context,"Insertado con exito",Toast.LENGTH_SHORT).show();
        return true;
    }
    public Persona findUser(String carnet){
        Persona p;
        String [] parametros = {carnet};
        String [] campos2 = {CAMPO_NOTA, CAMPO_MATERIA, CAMPO_CATEDRATICO};


        try {
            Cursor cursor = db.query(TABLA_USUARIO,campos2,CAMPO_CARNET+"=?",parametros,null,null,null);
            cursor.moveToFirst();


            p= new Persona(carnet,cursor.getString(0), cursor.getString(1), cursor.getString(2));

        }
        catch (Exception e){
            p=null;
        }
        return p;
    }

    public boolean editUser(Persona p){
        String [] parametros = {p.getCarnet()};
        //String [] campos = {CAMPO_CARNET, CAMPO_NOTA, CAMPO_MATERIA, CAMPO_CATEDRATICO};
        ContentValues values = new ContentValues();
        values.put(CAMPO_CARNET,p.getCarnet());
        values.put(CAMPO_NOTA,p.getNota());
        values.put(CAMPO_MATERIA, p.getMateria());
        values.put(CAMPO_CATEDRATICO, p.getCatedratico());
        db.update(TABLA_USUARIO,values,CAMPO_CARNET+"=?",parametros);
        Toast.makeText(context,"Usuario Actualizado con exito",Toast.LENGTH_SHORT).show();
        return true;
    }
    public ArrayList<Persona> fillarray(){

        ArrayList<Persona> list = new ArrayList<Persona>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLA_USUARIO;


        Cursor info = db.rawQuery(selectQuery, null);
        try {

            // looping through all rows and adding to list
            if (info.moveToFirst()) {
                do {
                    Persona obj = new Persona();
                    //only one column
                    obj.setCarnet(info.getString(0));
                    obj.setNota(info.getString(1));
                    obj.setMateria(info.getString(2));
                    obj.setCatedratico(info.getString(3));

                    list.add(obj);
                } while (info.moveToNext());
            }

        } finally {
            try {
                info.close();
            } catch (Exception ignore) {
            }
        }



        return list;
    }

    public boolean deleteUser(String carnet){
        String [] parametros = {carnet};
        db.delete(TABLA_USUARIO,CAMPO_CARNET+"=?",parametros);
        Toast.makeText(context,"Usuario Eliminado con exito",Toast.LENGTH_SHORT).show();
        return true;
    }
}