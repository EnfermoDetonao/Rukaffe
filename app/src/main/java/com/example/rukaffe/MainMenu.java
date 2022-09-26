package com.example.rukaffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClickIPedido(View view) {
        Intent i = new Intent(MainMenu.this,IngresoPedido.class);
        startActivity(i);
    }

    public void onClickRecepcionPedido(View view) {
        Intent i = new Intent(MainMenu.this,RecepcionPedido.class);
        startActivity(i);
    }

    public void onClickInventario(View view) {
        Intent i = new Intent(MainMenu.this, Inventario.class);
        startActivity(i);
    }
}