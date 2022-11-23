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

    public DatabaseQueryClass(Context context) { this.context = context; }

    public String insertarProducto(Inventory p) {
        String nombreProducto= p.getName();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        //valores de contenido
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.INVENTORY_NAME, p.getName());
        contentValues.put(Constants.INVENTORY_CANTIDAD, p.getCantidad());
        contentValues.put(Constants.INVENTORY_FECHA, p.getFecha());

        try {
            //decirle a la BD que le añada los datos
            sqLiteDatabase.insertOrThrow(Constants.TABLE_INVENTORY, null, contentValues);
        }
        catch (SQLiteException e) {
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return nombreProducto;
    }

    //listado
    public ArrayList<Inventory> obtenerProducto() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.query(Constants.TABLE_INVENTORY, null, null, null, null, null, null, null);
            if(cursor!=null)
                if (cursor.moveToFirst()){
                    ArrayList<Inventory> ListaObtenida =new ArrayList<>();
                    do{
                        Inventory productoObtenido = new Inventory();

                        String name=cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_NAME));
                        String cantidad=cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_CANTIDAD));
                        String fecha=cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_FECHA));
                        //se asigna a al producto
                        productoObtenido.setName(name);
                        productoObtenido.setCantidad(cantidad);
                        productoObtenido.setFecha(fecha);
                        //agrego producto a la lista
                        ListaObtenida.add(productoObtenido);

                    }while(cursor.moveToNext());
                    return ListaObtenida;
                }
        }
        catch (SQLiteException e){
            Toast.makeText(context, "error al listar:" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            sqLiteDatabase.close();
        }
        return new ArrayList<Inventory>();

    }


    //-----------------------------------------------------------


    public Inventory obtenerProducto(String nombre) {
        Inventory productoObtenido = new Inventory();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        int id = 25;
        Cursor cursor = null;

        try {
            cursor = sqLiteDatabase.query(Constants.TABLE_INVENTORY,
                    null, Constants.INVENTORY_NAME + " = ? ",
                    new String[]{nombre}, null, null, null, null);

            if (cursor.moveToFirst()) {

                String name=cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_NAME));
                String cantidad=cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_CANTIDAD));
                String fecha=cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_FECHA));
                productoObtenido.setName(name);
                productoObtenido.setCantidad(cantidad);
                productoObtenido.setFecha(fecha);


            }
            return productoObtenido;


        } catch (SQLiteException e) {
            Toast.makeText(context, "error al listar:" + e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            sqLiteDatabase.close();
        }
        return productoObtenido;

    }

}
