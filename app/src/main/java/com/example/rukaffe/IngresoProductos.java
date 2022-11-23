package com.example.rukaffe;

import static com.example.rukaffe.Util.MyApp.context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rukaffe.Database.DatabaseHelper;
import com.example.rukaffe.Database.DatabaseQueryClass;
import com.example.rukaffe.Models.Inventory;
import com.example.rukaffe.Util.Constants;

import java.util.ArrayList;
import java.util.List;

public class IngresoProductos extends AppCompatActivity {

    private EditText name;
    private EditText cantidad;
    private EditText date;
    private Button agregarBtn;
    List<Inventory> listaDatos;//datos con los que se construirá el reciclador
    RecyclerView reciclador;
    ArrayList<String> listaDatos2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_productos);

        name = this.findViewById(R.id.editTextNombre);
        cantidad = this.findViewById(R.id.editTextCantidad);
        date = this.findViewById(R.id.editTextDate);
        agregarBtn = this.findViewById(R.id.button10);

        this.obtenerListaProductos();

        agregarBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Inventory productoNuevo = new Inventory();

                productoNuevo.setName(name.getText().toString());
                productoNuevo.setCantidad(cantidad.getText().toString());
                productoNuevo.setFecha(date.getText().toString());

                DatabaseQueryClass dbdbQeryProducto = new DatabaseQueryClass(getBaseContext());
                dbdbQeryProducto.insertarProducto(productoNuevo);

                Intent i = new Intent(IngresoProductos.this,Inventario.class);
                startActivity(i);

            }
        });

    }

    public void obtenerListaProductos(){
        DatabaseQueryClass dbQeryProducto1 = new DatabaseQueryClass(getBaseContext());

        listaDatos=dbQeryProducto1.obtenerProducto();
        Adaptador adapter = new Adaptador(listaDatos);

    }

}