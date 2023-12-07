package com.juangil.adminbibliotecaapp.view.navegacionview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.juangil.adminbibliotecaapp.R;
import com.juangil.adminbibliotecaapp.databinding.ActivityNavegacionScreenBinding;
import com.juangil.adminbibliotecaapp.response.DataAdminUsuario;
import com.juangil.adminbibliotecaapp.utils.Utils;
import com.juangil.adminbibliotecaapp.view.loginscreenview.LoginScreenActivity;

public class NavegacionScreenActivity extends AppCompatActivity {

    private ActivityNavegacionScreenBinding binding;
    DataAdminUsuario usuario;
    Utils utils = new Utils();
    String[] listaMenu;
    AdaptadorMenu adaptadorMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavegacionScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usuario = utils.leerSharedPreferences(NavegacionScreenActivity.this);
        binding.tvUsuario.setText(usuario.getNomUsuario());

        setupRecyclerView();

        binding.ibtnCerrarSesion.setOnClickListener(view -> {
            utils.borrarSharedPreferences(this);
            Intent intent = new Intent(this, LoginScreenActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.rvmenu.setLayoutManager(layoutManager);
        listaMenu = getResources().getStringArray(R.array.lista_menu);
        adaptadorMenu = new AdaptadorMenu(this, listaMenu);
        binding.rvmenu.setAdapter(adaptadorMenu);
    }
}