package com.example.adopciondeanimales.Adaptadores;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adopciondeanimales.R;

public class AnimalVH extends RecyclerView.ViewHolder {

    public TextView txt_nombre, txt_tipo, txt_raza,txt_sexo,txt_descripcion;
    public AnimalVH(@NonNull View itemView) {
        super(itemView);
        txt_nombre = itemView.findViewById(R.id.txt_nombre);
        txt_tipo = itemView.findViewById(R.id.txt_tipo);
        txt_raza = itemView.findViewById(R.id.txt_raza);
        txt_sexo = itemView.findViewById(R.id.txt_sexo);
        txt_descripcion = itemView.findViewById(R.id.txt_descripcion);
    }
}
