package com.example.adopciondeanimales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adopciondeanimales.Adapter.AnimalesAdapter;
import com.example.adopciondeanimales.DAO.DAOAnimal;
import com.example.adopciondeanimales.Models.Animal;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MenuOpcionActivity extends AppCompatActivity {

    private TextView insertar;
    private TextView listaAnimales;
    private Button button;

    RecyclerView mrecycle;
    AnimalesAdapter animalesAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        insertar = (TextView) findViewById(R.id.id_darAdopcion);
        listaAnimales = (TextView) findViewById(R.id.id_lista);
        button = (Button) findViewById(R.id.id_btnPrueba);

        db = FirebaseFirestore.getInstance();
        mrecycle = findViewById(R.id.id_recycle);
        mrecycle.setLayoutManager(new LinearLayoutManager(this));
        CollectionReference query = db.collection("Animales");
        FirestoreRecyclerOptions<Animal>   firestoreRecyclerOptions  = new FirestoreRecyclerOptions
                .Builder<Animal>()
                .setQuery(query, Animal.class).build();

        animalesAdapter = new AnimalesAdapter(firestoreRecyclerOptions);
        animalesAdapter.notifyDataSetChanged();
        mrecycle.setAdapter(animalesAdapter);
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuOpcionActivity.this,InsertaAnimalActivity.class));
            }
        });
        listaAnimales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MenuOpcionActivity.this,.class));
                DAOAnimal daoAnimal = new DAOAnimal();
                daoAnimal.queryToDataBase();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Animales").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId().toString() + " => " + document.getData());
                                Toast.makeText(MenuOpcionActivity.this, "Id" + document.getId().toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        animalesAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        animalesAdapter.startListening();
    }
}