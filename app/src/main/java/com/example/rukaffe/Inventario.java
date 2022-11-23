package com.example.rukaffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.rukaffe.Database.DatabaseQueryClass;
import com.example.rukaffe.Models.Inventory;

import java.util.ArrayList;

public class Inventario extends AppCompatActivity {

    ArrayList<Inventory> listaProductos;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        DatabaseQueryClass dbQeryUsuario1 = new DatabaseQueryClass(getBaseContext());


        listaProductos=dbQeryUsuario1.obtenerProducto();
        Adaptador adapter = new Adaptador(listaProductos);

        lista= findViewById(R.id.listProducto);
        ArrayList<Inventory> pr = dbQeryUsuario1.obtenerProducto();
        ArrayList<String> list =new ArrayList<String>();

        for(Inventory p:pr) {
            list.add("Nombre producto: " + p.getName() + "   Cantidad: "+ p.getCantidad() + "                Fecha: " + p.getFecha());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        lista.setAdapter(a);
    }

    public void onClickIngresoProd(View view) {
        Intent i = new Intent(Inventario.this, IngresoProductos.class);
        startActivity(i);
    }

    public void onClickBackToMain(View view){
        Intent i = new Intent(Inventario.this, MainMenu.class);
        startActivity(i);
    }



}
