package com.example.adopciondeanimales.Adaptadores;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adopciondeanimales.Models.Animal;
import com.example.adopciondeanimales.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdaptadorAnimales extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Animal> list = new ArrayList<>();

    public AdaptadorAnimales(Context _context){
        this.context = _context;
    }

    public void setItems(ArrayList<Animal> a){
        list.addAll(a);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  v = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new AnimalVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AnimalVH animalVH = (AnimalVH) holder;
        Animal animal = list.get(position);
        animalVH.txt_nombre.setText(animal.getNombre());
        animalVH.txt_raza.setText(animal.getRaza());
        animalVH.txt_tipo.setText(animal.getTipo());
        animalVH.txt_sexo.setText(animal.getSexo());
        animalVH.txt_descripcion.setText(animal.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
