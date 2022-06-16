package com.example.adopciondeanimales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adopciondeanimales.DAO.DAOAnimales;
import com.example.adopciondeanimales.Models.Animal;

import java.util.HashMap;

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
        raza = (EditText) findViewById(R.id.id_razaAnimal);
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

    public void actualizar(View view){
        String _nombre = nombre.getText().toString();
        String _tipo = tipoAnimal.getText().toString();
        String _raza = raza.getText().toString();
        String _sexo = sexo.getText().toString();
        String _decripcion = descripcion.getText().toString();
        Animal animal = new Animal(0,_nombre,_tipo,_raza,_sexo,_decripcion);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("nombre",animal.getNombre());
        hashMap.put("tipo",animal.getTipo());
        hashMap.put("raza",animal.getRaza());
        hashMap.put("sexo",animal.getSexo());
        hashMap.put("descripcion",animal.getDescripcion());
        hashMap.put("tokenAnimal",animal.getTokenAnimal());

        if(!animal.getNombre().equals("") && !animal.getTipo().equals("")
                && !animal.getRaza().equals("") && !animal.getSexo().equals("")
                && !animal.getDescripcion().equals("")){
            Toast.makeText(InsertaAnimalActivity.this,
                    "Nombre:" + animal.getNombre() +" " + "Tipo: " + animal.getTipo()+
                            " " + "Raza: " + animal.getRaza() + " " + "Sexo: " + animal.getSexo()
                            +" " + "Descripción: " + animal.getDescripcion()
                    ,Toast.LENGTH_SHORT).show();
            dao.update("-N4hC1lhJy-qeIGlBk69",hashMap).addOnSuccessListener(suc  ->{
                Toast.makeText(InsertaAnimalActivity.this,"Se Actualizo correctamente los datos",Toast.LENGTH_SHORT).show();

            }).addOnFailureListener( er -> {
                Toast.makeText(InsertaAnimalActivity.this,"No se Actualizo correctamente los datos",Toast.LENGTH_SHORT).show();
            });
            startActivity(new Intent(InsertaAnimalActivity.this,MenuOpcionActivity.class));
        }else{
            Toast.makeText(InsertaAnimalActivity.this,
                    "Los campos estan vacios, por favor llenalos!",Toast.LENGTH_LONG).show();
        }
    }

    public void eliminar(View view){
        dao.remove("-N4gyDu0ZUoxA09Ipj-t").addOnSuccessListener( s -> {
            Toast.makeText(InsertaAnimalActivity.this,"Se Elimino correctamente los datos",Toast.LENGTH_SHORT).show();
        }).addOnFailureListener( er -> {
            Toast.makeText(InsertaAnimalActivity.this,"No se Elimino correctamente los datos",Toast.LENGTH_SHORT).show();
        });
    }
}