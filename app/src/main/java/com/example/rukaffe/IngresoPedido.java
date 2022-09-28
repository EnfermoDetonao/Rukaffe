package com.example.rukaffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IngresoPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_pedido);
    }
    public void onClickRegresoaMain(View view) {
        Intent i = new Intent(IngresoPedido.this, MainMenu.class);
        startActivity(i);
    }
}