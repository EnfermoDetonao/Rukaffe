package com.example.rukaffe.Database;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.rukaffe.Models.Inventory;
import com.example.rukaffe.Util.Constants;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context) {
        this.context = context;
    }

    public String insertarProducto(Inventory i){
        String nombreProducto=i.getName();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contenido= new ContentValues();
        contenido.put(Constants.INVENTORY_NAME,i.getName());
        contenido.put(Constants.INVENTORY_CANTIDAD,i.getCantidad());

        try{
            sqLiteDatabase.insertOrThrow(Constants.TABLE_INVENTORY,null,contenido);
        }
        catch(SQLiteException e){
            Toast.makeText(context, "Falló la inserción: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
        return nombreProducto;
    }

    public List<Inventory> obtenerInventario(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor=null;
        try{
            cursor=  sqLiteDatabase.query(Constants.TABLE_INVENTORY,null,null,null,null,null,null,null);
            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<Inventory> listaObtenida= new ArrayList<>();
                    do{
                        Inventory productoObtenido=new Inventory();
                        //obtengo valores de la BD
                        String name = cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_NAME));
                        int cantidad = cursor.getInt(cursor.getColumnIndex(Constants.INVENTORY_CANTIDAD));
                        String fecha = cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_FECHA));
                        //se los asigno al usuario
                        productoObtenido.setName(name);
                        productoObtenido.setCantidad(cantidad);
                        productoObtenido.setFecha(fecha);
                        //agrego el usuario a la lista
                        listaObtenida.add(productoObtenido);

                    }while(cursor.moveToNext());
                    return listaObtenida;
                }
        }
        catch(SQLiteException e){
            Toast.makeText(context, "Error al listar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
        return new ArrayList<Inventory>();
    }
    
    public Inventory obtenerProducto(String nombre){
        Inventory productoObtenido=new Inventory();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        int id=25;
        Cursor cursor=null;
        try{
            cursor=  sqLiteDatabase.query(Constants.TABLE_INVENTORY,
                    null,Constants.INVENTORY_NAME + " = ? ",
                    new String[]{nombre},null,null,null,null);

            if(cursor.moveToFirst()){

                String nombrei = cursor.getString (cursor.getColumnIndex(Constants.INVENTORY_NAME));
                String cantidad = cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_CANTIDAD));
                productoObtenido.setName(nombre);
                productoObtenido.setCantidad(Integer.valueOf(cantidad));
            }
            return productoObtenido;

        }
        catch(SQLiteException e){
            Toast.makeText(context, "Error al listar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
        return productoObtenido;
    }

    public void insertarProducto() {
    }
}
