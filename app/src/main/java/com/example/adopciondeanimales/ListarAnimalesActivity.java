package com.example.adopciondeanimales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.adopciondeanimales.Adaptadores.AdaptadorAnimales;
import com.example.adopciondeanimales.DAO.DAOAnimales;
import com.example.adopciondeanimales.Models.Animal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarAnimalesActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    AdaptadorAnimales adaptadorAnimales;
    DAOAnimales dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_animales);
        swipeRefreshLayout  = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.recv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adaptadorAnimales = new AdaptadorAnimales(this);
        recyclerView.setAdapter(adaptadorAnimales);
        dao = new DAOAnimales();
        cargarDatos();

    }

    private void cargarDatos() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Animal> lista = new ArrayList<>();
                for (DataSnapshot datos: snapshot.getChildren()){
                    Animal e = datos.getValue(Animal.class);
                    lista.add(e);
                }
                adaptadorAnimales.setItems(lista);
                adaptadorAnimales.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}