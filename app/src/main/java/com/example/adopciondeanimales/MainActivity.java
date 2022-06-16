package com.example.adopciondeanimales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //Creacion de variables para la manipulacion de componentes en android
    private EditText correo;
    private EditText contra;
    private Button btnIniciarSesion;
    private TextView registrarse;
    private TextView olvideMicontra;
    //Declaracion de variables para firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private  boolean bandera = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instancias de los componentes del layout
        correo = (EditText) findViewById(R.id.id_correo);
        contra = (EditText) findViewById(R.id.id_contra);
        btnIniciarSesion = (Button) findViewById(R.id.id_btn_iniciarRegistrar);
        olvideMicontra = (TextView) findViewById(R.id.id_recuperacionContra);
        registrarse = (TextView) findViewById(R.id.id_registrarse);
        //Instancia de firebases
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrarActivity.class));
            }
        });

        olvideMicontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RecuperarContraActivity.class));
            }
        });
    }

    public void iniciarSesion(View view){
        String correoElectronico = correo.getText().toString().trim();
        String contrasenia = contra.getText().toString().trim();
         if(!correoElectronico.isEmpty() && !contrasenia.isEmpty()){
             emailVerification(firebaseAuth.getCurrentUser().getUid());
             firebaseAuth.signInWithEmailAndPassword(correoElectronico,contrasenia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){
                         Toast.makeText(MainActivity.this,"Logeo con exito!",Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(MainActivity.this,MenuOpcionActivity.class));
                     }else{
                         String e = task.getException().toString();
                         Toast.makeText(MainActivity.this, e,Toast.LENGTH_LONG).show();
                     }
                 }
             });
             //Toast.makeText(MainActivity.this,"No estan vacio los campos. El correo es: " + correoElectronico + " " + "La contraseña es: " + contrasenia ,Toast.LENGTH_LONG).show();
         }else {
             Toast.makeText(MainActivity.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
         }
    }

    private void emailVerification(String userID){
        DocumentReference docRef = firebaseFirestore.collection("users").document(userID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                            // Update one field, creating the document if it does not already exist.
                            Map<String, Object> data = new HashMap<>();
                            data.put("active", true);
                            firebaseFirestore.collection("users").document(userID).update(data);
                            Toast.makeText(MainActivity.this, "active: [true]", Toast.LENGTH_LONG).show();
                            bandera = true;
                        }
                        Toast.makeText(MainActivity.this, "DocumentSnapshot data: " + document.getData(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No such document", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "get failed with: " + task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}



























