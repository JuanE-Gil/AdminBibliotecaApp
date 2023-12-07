package com.juangil.adminbibliotecaapp.view.usuariosview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.juangil.adminbibliotecaapp.R;
import com.juangil.adminbibliotecaapp.response.DataUsuario;

import java.util.List;

public class AdaptadorUsuario extends RecyclerView.Adapter<AdaptadorUsuario.ViewHolder> {
    Context context;
    List<DataUsuario> listaUsuarios;
    OnItemClicked onClick;

    public AdaptadorUsuario(Context context, List<DataUsuario> listaUsuarios, OnItemClicked onClick) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public AdaptadorUsuario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_usuarios, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorUsuario.ViewHolder holder, int position) {
        DataUsuario usuario = listaUsuarios.get(position);

        if (usuario.getEstadoUsuario() == null) {
            holder.cvUsuario.setBackgroundColor(context.getResources().getColor(R.color.verde_oscuro));
        } else {
            holder.cvUsuario.setBackgroundColor(context.getResources().getColor(R.color.rojo_oscuro));
        }

        holder.tvNomUsuario.setText(usuario.getNomUsuario());

        holder.ibtnEditar.setOnClickListener(view -> {
            onClick.editarUsuario(usuario);
        });

        holder.ibtnEliminar.setOnClickListener(view -> {
            onClick.borrarUsuario(usuario);
        });
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvUsuario;
        TextView tvNomUsuario;
        ImageButton ibtnEditar, ibtnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvUsuario = itemView.findViewById(R.id.cvUsuario);
            tvNomUsuario = itemView.findViewById(R.id.tvNomUsuario);
            ibtnEditar = itemView.findViewById(R.id.ibtnEditar);
            ibtnEliminar = itemView.findViewById(R.id.ibtnEliminar);
        }
    }

    public interface OnItemClicked {
        void editarUsuario(DataUsuario usuario);

        void borrarUsuario(DataUsuario usuario);

    }
}
