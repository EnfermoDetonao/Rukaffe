package com.example.rukaffe.Database;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.rukaffe.Models.Inventory;
import com.example.rukaffe.Util.Constants;

public class DatabaseQueryClass {

    private Context context;

    public DatabaseQueryClass(Context context) {
        this.context = context;
    }

    public String insertarProducto(Inventory u){
        String nombreProducto=u.getName();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contenido= new ContentValues();
        contenido.put(Constants.INVENTORY_NAME,u.getName());
        contenido.put(Constants.INVENTORY_CANTIDAD,u.getCantidad());

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
}
