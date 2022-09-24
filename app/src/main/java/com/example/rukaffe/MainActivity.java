package com.example.rukaffe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//Log in a Registro
    public void onClickRegister(View view) {
        Intent i = new Intent(MainActivity.this,RegisterScreen.class);
        startActivity(i);
    }
//Log in a pantalla principal
    public void onClickMainMenu(View view) {
        Intent i = new Intent(MainActivity.this,MainMenu.class);
        startActivity(i);
    }
}