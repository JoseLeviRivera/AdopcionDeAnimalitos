package com.example.adopciondeanimales.DAO;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.adopciondeanimales.Models.Animal;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DAOAnimales {
    private DatabaseReference databaseReference;

    public DAOAnimales(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Animal.class.getSimpleName());
    }

    public Task<Void> add(Animal a){
             return databaseReference.push().setValue(a);
    }

    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get(){
        return databaseReference.orderByKey();
    }

/*
    public ArrayList<Animal>  cargarDatos() {
        ArrayList<Animal> lista = new ArrayList<>();
        databaseReference.child("Animal")
                .child(getRef(position).getKey)
                .updateChildren(map)
                addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datos: snapshot.getChildren()) {
                    Animal e = datos.getValue(Animal.class);
                    lista.add(e);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        return lista;
    }
*/
    public String getItem(){
        return databaseReference.getKey();
    }

    public ArrayList<Animal> query(){
        ArrayList<Animal> ListaDeanimales = new ArrayList<>();
        databaseReference.child("Animal").child("uid").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot s : snapshot.getChildren()) {
                        String n = s.getKey().toString();
                        Log.d("E", n);

                        String nombre = s.child("nombre").getValue().toString();
                        String tipo = s.child("tipo").getValue().toString();
                        String raza = s.child("raza").getValue().toString();
                        String sexo = s.child("sexo").getValue().toString();
                        String descripcion = s.child("descripcion").getValue().toString();
                        int tokenAnimal = Integer.parseInt(s.child("tokenAnimal").getValue().toString());
                        Animal animal = new Animal(tokenAnimal, nombre, tipo, raza, sexo, descripcion);
                        ListaDeanimales.add(animal);
                    }
                } else {
                    Log.d("MENSAJES", "No existen datos en La base de datos Animal");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        return  ListaDeanimales;
    }
}

