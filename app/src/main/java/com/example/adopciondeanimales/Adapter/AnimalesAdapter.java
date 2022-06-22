package com.example.adopciondeanimales.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adopciondeanimales.Models.Animal;
import com.example.adopciondeanimales.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AnimalesAdapter extends FirestoreRecyclerAdapter<Animal,AnimalesAdapter.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AnimalesAdapter(@NonNull FirestoreRecyclerOptions<Animal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Animal model) {
        holder.nombre.setText(model.getNombre());
        holder.tipo.setText(model.getTipo());
        holder.raza.setText(model.getRaza());
        holder.sexo.setText(model.getSexo());
        holder.descripcion.setText(model.getDescripcion());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, tipo, raza, sexo, descripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txt_nombre);
            tipo = itemView.findViewById(R.id.txt_tipo);
            raza = itemView.findViewById(R.id.txt_raza);
            sexo = itemView.findViewById(R.id.txt_sexo);
            descripcion = itemView.findViewById(R.id.txt_descripcion);
        }
    }
}
