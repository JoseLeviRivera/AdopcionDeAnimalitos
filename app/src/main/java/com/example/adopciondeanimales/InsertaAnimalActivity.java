package com.example.adopciondeanimales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adopciondeanimales.DAO.DAOAnimales;
import com.example.adopciondeanimales.Models.Animal;

public class InsertaAnimalActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText tipoAnimal;
    private EditText raza;
    private EditText sexo;
    private EditText descripcion;
    private Button btnRegistro;
    private DAOAnimales dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserta_animal);
        //Crea la instancia de nombre
        nombre = (EditText) findViewById(R.id.id_NombreAnimal);
        //Crea la instacia de tipo de animal
        tipoAnimal = (EditText) findViewById(R.id.id_tipoAnimal);
        //Crea la instancia del tipo de raza
        raza = (EditText) findViewById(R.id.id_tipoAnimal);
        //Crea la instancia de sexo
        sexo = (EditText) findViewById(R.id.id_sexoAnimal);
        //Crea la instancia de la descripcion del animal
        descripcion = (EditText) findViewById(R.id.id_descripcionAnimal);
        //Crea la instancia del boton de registro
        btnRegistro = (Button) findViewById(R.id.id_btnRegistrarAnimal);
       // String text = tiposAnimales.getSelectedItem().toString();
         dao = new DAOAnimales();
    }

    public void registraAnimal(View view){
        String _nombre = nombre.getText().toString();
        String _tipo = tipoAnimal.getText().toString();
        String _raza = raza.getText().toString();
        String _sexo = sexo.getText().toString();
        String _decripcion = descripcion.getText().toString();
        Animal animal = new Animal(0,_nombre,_tipo,_raza,_sexo,_decripcion);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!animal.getNombre().equals("") && !animal.getTipo().equals("")
                        && !animal.getRaza().equals("") && !animal.getSexo().equals("")
                        && !animal.getDescripcion().equals("")){
                        Toast.makeText(InsertaAnimalActivity.this,
                                "Nombre:" + animal.getNombre() +" " + "Tipo: " + animal.getTipo()+
                                        " " + "Raza: " + animal.getRaza() + " " + "Sexo: " + animal.getSexo()
                                +" " + "Descripción: " + animal.getDescripcion()
                                ,Toast.LENGTH_SHORT).show();
                        dao.add(animal).addOnSuccessListener(suc ->{
                            Toast.makeText(InsertaAnimalActivity.this,"Se agregó correctamente los datos",Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er -> {
                            Toast.makeText(InsertaAnimalActivity.this,"No se agregó correctamente los datos",Toast.LENGTH_SHORT).show();
                        });
                    startActivity(new Intent(InsertaAnimalActivity.this,MenuOpcionActivity.class));
                }else{
                    Toast.makeText(InsertaAnimalActivity.this,
                            "Los campos estan vacios, por favor llenalos!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}