package com.example.adopciondeanimales.Providers;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class TokenProvider {
    DocumentReference docRef;
    DatabaseReference _databaseReference;
    FirebaseFirestore _firebaseFirestore;
    String token;

    public TokenProvider(String userID) {
        docRef = _firebaseFirestore.collection("users").document(userID);
        token = userID;
    }

    public TokenProvider() { }

    public void create(String userID){
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        //Update on field, creating the document if it does not already exists.
                        Map<String, Object> data = new HashMap<>();
                        data.put("token",token);
                        _firebaseFirestore.collection("users").document(userID).update(data);
                        Log.d("TOKEN","The field update was succefully");
                    }else {

                    }
                }else {

                }
            }
        });
    }
    private void setToken(String userID, String token){
        DocumentReference docRef = _firebaseFirestore.collection("users").document(userID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Update one field, creating the document if it does not already exist.
                        Map<String, Object> data = new HashMap<>();
                        data.put("TOKEN", token);

                        _firebaseFirestore.collection("users").document(userID).update(data);
                        Log.d("TOKEN", "token: [" + token + "]");
                        Log.d("TOKEN", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("TOKEN", "No such document");
                    }
                } else {
                    Log.d("TOKEN", "get failed with: " + task.getException());
                }
            }
        });
    }

    public String getToken(String userID){
        final String[] token = new String[1];
        DocumentReference docRef = _firebaseFirestore.collection("users").document(userID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        token[0] = document.getString("token");
                        Log.d("TOKEN", "token: [" + token[0] + "]");
                    } else {
                        Log.d("TOKEN", "No such document");
                    }
                } else {
                    Log.d("TOKEN", "get failed with: " + task.getException());
                }
            }
        });
        return token[0];
    }

}
