package com.example.adopciondeanimales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adopciondeanimales.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrarActivity extends AppCompatActivity {
    private EditText correo;
    private EditText contra;
    private EditText contraVerificacion;

    //Declaracion de variables para firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        correo = (EditText) findViewById(R.id.id_correoRecuperar);
        contra = (EditText) findViewById(R.id.id_contraRecuperar);
        contraVerificacion = (EditText) findViewById(R.id.id_repetirContraRecuperar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void registrarse(View view) {
        String correoElectronico = correo.getText().toString();
        String contrasenia = contra.getText().toString();
        String vContra = contraVerificacion.getText().toString();

        if (!correoElectronico.isEmpty() && !contrasenia.isEmpty() && !vContra.isEmpty()) {
            if (contrasenia.equals(vContra)) {
                //Registro con firebases
                firebaseAuth.createUserWithEmailAndPassword(correoElectronico, contrasenia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userID = firebaseAuth.getCurrentUser().getUid();
                            user = new User();
                            user.setUserID(userID);
                            user.setToken("");
                            user.setEmail(correoElectronico);
                            user.setPassword(contrasenia);
                            user.setActive(false);
                            addToDataBase(userID);
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                                        //Toast.makeText(Registro_Activity.this, "Accede a tu correo electronico para verificar la cuenta de google", Toast.LENGTH_LONG).show();
                                        finish();
                                        startActivity(intent);
                                        //Toast.makeText(Registro_Activity.this, "Accede a tu correo electronico para verificar la cuenta de google", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RegistrarActivity.this, "Mensaje de verificación por correo electronico de Google, No enviado", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Toast.makeText(RegistrarActivity.this, "Mensaje de verificación por correo electronico de Google, No enviado", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegistrarActivity.this, "La dirección de correo electronico ya esta registrada", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //Toast.makeText(RegistrarActivity.this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(RegistrarActivity.this,MainActivity.class));
            } else {
                Toast.makeText(RegistrarActivity.this, "No son iguales los campos de la contraseña", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(RegistrarActivity.this, "Los campos estan vacios", Toast.LENGTH_SHORT).show();
        }
    }

    public void addToDataBase(String userID) {
        Toast.makeText(RegistrarActivity.this, "MaddToDataBase", Toast.LENGTH_LONG).show();
        if (user != null) {
            firebaseFirestore.collection("users").document(userID).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(RegistrarActivity.this, "Errar" + userID, Toast.LENGTH_LONG).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegistrarActivity.this, "Error" + e, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}