package com.example.rukaffe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rukaffe.Database.DatabaseQueryClass;
import com.example.rukaffe.Models.Inventory;

import java.util.ArrayList;
import java.util.List;


public class IngresoProductos extends AppCompatActivity {

    private EditText name;
    private EditText cantidad;
    private EditText date;
    private Button agregarBtn;
    String listaDatos;//datos con los que se construirá el reciclador
    RecyclerView reciclador;
    ArrayList<String> listaDatos2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_productos);
        name = this.findViewById(R.id.editTextNombre);
        cantidad= this.findViewById(R.id.editTextCantidad);
        date= this.findViewById(R.id.editTextDate);
        agregarBtn=this.findViewById(R.id.button10);
        this.obtenerListaInventario();


        agregarBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Inventory productoNuevo= new Inventory();
                productoNuevo.setName(name.getText().toString());
                productoNuevo.setCantidad(Integer.valueOf(cantidad.getText().toString()));
                productoNuevo.setFecha(date.getText().toString());

                DatabaseQueryClass dbQueryClass = new DatabaseQueryClass(getBaseContext());

                dbQueryClass.insertarProducto();
            }
        });
    }

    public void obtenerListaInventario(){
        DatabaseQueryClass dbQeryInventario1 = new DatabaseQueryClass(getBaseContext());

        //listaDatos= new ArrayList<Usuario>();
        listaDatos=dbQeryInventario1.insertarProducto();
        Adaptador adapter = new Adaptador(listaDatos);


        //obtener el reciclador
        reciclador= findViewById(R.id.recyclerInventario);
        //reciclador.setLayoutManager(new GridLayoutManager(this,1));
        reciclador.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        reciclador.setAdapter(adapter);
    }


    public void onClickRegresoaMain(View view) {
        Intent i = new Intent(IngresoProductos.this, Inventario.class);
        startActivity(i);
    }
}