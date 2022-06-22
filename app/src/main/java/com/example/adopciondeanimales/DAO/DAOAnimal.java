package com.example.adopciondeanimales.DAO;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.adopciondeanimales.Models.Animal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOAnimal {
    private FirebaseFirestore db;

    public DAOAnimal(){ db = FirebaseFirestore.getInstance();  }
    public void insertToDataBase(Animal a){
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
                        Log.d("EXITOS","Se inserto correctamente los dato en la base de datos");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("FALLO","No se insertó correctamente los datos en la base de datos");
            }
        });
    }

    public  List<String> queryData(){
        List<String> lista = new ArrayList();
        db.collection("Animales").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null) Log.d("ERRORS", "Errores");
                for (QueryDocumentSnapshot doc: value)
                    lista.add(doc.getString("Animales"));
            }
        });
        return lista;
    }
    public void queryToDataBase(){
        List<String> lista = new ArrayList();
        db.collection("Animales").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null) Log.d("ERRORS", "Errores");
                for (QueryDocumentSnapshot doc: value)
                    lista.add(doc.getString("Animales"));
            }
        });
    }

    public boolean updateToDataBase(HashMap<String,Object> data){
        final boolean[] bandera = {false};
        db.collection("Animales").document("1").update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                       bandera[0] = true;
                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                bandera[0] = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                bandera[0] = false;
            }
        });
        return bandera[0];
    }

    public boolean deleteToDataBase(String idDoc){
        final boolean[] bandera = {false};
        db.collection("Animales").document(idDoc).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                bandera[0] = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                bandera[0] = false;
            }
        });
        return bandera[0];
    }
}
