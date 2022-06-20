package com.example.adopciondeanimales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.adopciondeanimales.DAO.DAOAnimales;
import com.example.adopciondeanimales.Models.Animal;

public class ContenidoListaAnimalesActivity extends AppCompatActivity {
    private TextView texto;
    private Button botonCargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_lista_animales);
        texto = (TextView) findViewById(R.id.id_contenidoBD);
        botonCargar = (Button) findViewById(R.id.btnCargarBD);
        botonCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAOAnimales dao = new DAOAnimales();
                String a = "";
                for (Animal animals : dao.cargarDatos()){
                   String datos =  animals.getNombre() + " " + animals.getTipo() +" "+ animals.getRaza() +" " + animals.getSexo() + " " + animals.getDescripcion() +"\n";
                   a = a + datos;
                }
                texto.setText(a);
            }
        });
    }
}