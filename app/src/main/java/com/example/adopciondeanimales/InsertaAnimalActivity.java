package com.example.adopciondeanimales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.adopciondeanimales.Models.Animal;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class InsertaAnimalActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText tipoAnimal;
    private EditText raza;
    private EditText sexo;
    private EditText descripcion;
    private Button btnRegistro;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserta_animal);
        //Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Insertar datos animalito");
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
        //Intancia de FireStore
        db = FirebaseFirestore.getInstance();
        //Al hacer un click ejecuta las siguientes ordenes
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animal a = new Animal(nombre.getText().toString(),tipoAnimal.getText().toString(),raza.getText().toString(), sexo.getText().toString(), descripcion.getText().toString());
                if(!a.getNombre().equals("") && !a.getTipo().equals("") && !a.getRaza().equals("") && !a.getSexo().equals("") && !a.getDescripcion().equals("")){
                    Toast.makeText(InsertaAnimalActivity.this, "Nombre:" + a.getNombre() +" " + "Tipo: " + a.getTipo()+ " " + "Raza: " + a.getRaza() + " " + "Sexo: " + a.getSexo() + " " + "Descripción: " + a.getDescripcion(),Toast.LENGTH_SHORT).show();
                    Map<String, Object> animal = new HashMap<>();
                    animal.put("Nombre",a.getNombre());
                    animal.put("Tipo",a.getTipo());
                    animal.put("Raza",a.getRaza());
                    animal.put("Sexo",a.getSexo());
                    animal.put("Descripcion",a.getDescripcion());
                    db.collection("Animales")
                            .add(animal)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(InsertaAnimalActivity.this, "Se inserto correctamente los datos", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(InsertaAnimalActivity.this,MenuOpcionActivity.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(InsertaAnimalActivity.this, "No se inserto los datos", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(InsertaAnimalActivity.this, "Los campos estan vacios, por favor llenalos!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}