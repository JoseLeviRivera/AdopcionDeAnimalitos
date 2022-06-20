package com.example.adopciondeanimales.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DAOAnimal {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    public DAOAnimal(){

    }

    public void CrearBD(String userID){
        DocumentReference docRef = firebaseFirestore.collection("users").document(userID);
    }

}
