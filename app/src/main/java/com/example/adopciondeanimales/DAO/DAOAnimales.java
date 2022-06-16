package com.example.adopciondeanimales.DAO;

import com.example.adopciondeanimales.Models.Animal;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOAnimales {
    private DatabaseReference databaseReference;

    public DAOAnimales(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Animal.class.getSimpleName());
    }
    public Task<Void> add(Animal a){
             return databaseReference.push().setValue(a);
    }

}

