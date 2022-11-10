package com.example.rukaffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rukaffe.Database.DatabaseQueryClass;
import com.example.rukaffe.Models.Inventory;

public class IngresoProductos extends AppCompatActivity {

    private EditText name;
    private EditText cantidad;
    private EditText date;
    private Button agregarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_productos);
        name = this.findViewById(R.id.editTextNombre);
        cantidad= this.findViewById(R.id.editTextCantidad);
        date= this.findViewById(R.id.editTextDate);
        agregarBtn=this.findViewById(R.id.button10);

        agregarBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Inventory productoNuevo= new Inventory();
                productoNuevo.setName(name.getText().toString());
                productoNuevo.setCantidad(Integer.valueOf(cantidad.getText().toString()));
                productoNuevo.setFecha(date.getText().toString());

                DatabaseQueryClass dbQueryClass = new DatabaseQueryClass(getBaseContext());

                dbQueryClass.insertarProducto(productoNuevo);
            }
        });
    }

    public void onClickRegresoaMain(View view) {
        Intent i = new Intent(IngresoProductos.this, Inventario.class);
        startActivity(i);
    }
}