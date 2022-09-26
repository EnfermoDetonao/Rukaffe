package com.example.rukaffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inventario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
    }

    public void onClickIngresoProd(View view) {
        Intent i = new Intent(Inventario.this, IngresoProductos.class);
        startActivity(i);
    }
}