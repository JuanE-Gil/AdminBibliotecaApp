package com.juangil.adminbibliotecaapp.view.autoresView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juangil.adminbibliotecaapp.R;
import com.juangil.adminbibliotecaapp.response.DataAutor;

import java.util.List;

public class AdaptadorAutor extends RecyclerView.Adapter<AdaptadorAutor.ViewHolder> {

    Context context;
    List<DataAutor> listaAutores;
    OnItemClicked onClick;

    public AdaptadorAutor(Context context, List<DataAutor> listaAutores, OnItemClicked onClick) {
        this.context = context;
        this.listaAutores = listaAutores;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public AdaptadorAutor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_autores, parent, false);

        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAutor.ViewHolder holder, int position) {
        DataAutor autor = listaAutores.get(position);

        holder.tvNomAutores.setText(autor.getNomAutor().toUpperCase());

        holder.ibtnEditar.setOnClickListener(view -> {
            onClick.editarAutor(autor);
        });

        holder.ibtnBorrar.setOnClickListener(view -> {
            onClick.borrarAutor(autor);
        });

    }

    @Override
    public int getItemCount() {
        return listaAutores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomAutores;
        ImageButton ibtnEditar, ibtnBorrar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomAutores = itemView.findViewById(R.id.tvAutor);
            ibtnEditar = itemView.findViewById(R.id.ibtnEditar);
            ibtnBorrar = itemView.findViewById(R.id.ibtnEliminar);
        }
    }

    public interface OnItemClicked {
        void editarAutor(DataAutor autor);

        void borrarAutor(DataAutor autor);
    }
}
