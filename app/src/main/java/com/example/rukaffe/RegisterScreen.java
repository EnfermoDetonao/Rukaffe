package com.example.rukaffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
    }

// del Registro al Log in
    public void onClickRegistrado(View view) {
        Intent i = new Intent(RegisterScreen.this,MainActivity.class);
        startActivity(i);
    }
}