package com.example.adopciondeanimales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuOpcionActivity extends AppCompatActivity {

    private TextView insertar;
    private TextView listaAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        insertar = (TextView) findViewById(R.id.id_darAdopcion);
        listaAnimales = (TextView) findViewById(R.id.id_lista);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuOpcionActivity.this,InsertaAnimalActivity.class));
            }
        });
        listaAnimales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuOpcionActivity.this,ContenidoListaAnimalesActivity.class));
            }
        });
    }
}