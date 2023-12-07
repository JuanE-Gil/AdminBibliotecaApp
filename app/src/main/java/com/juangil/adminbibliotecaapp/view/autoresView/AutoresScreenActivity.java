package com.juangil.adminbibliotecaapp.view.autoresView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.juangil.adminbibliotecaapp.R;
import com.juangil.adminbibliotecaapp.databinding.ActivityAutoresScreenBinding;
import com.juangil.adminbibliotecaapp.response.AutorResponse;
import com.juangil.adminbibliotecaapp.response.DataAutor;
import com.juangil.adminbibliotecaapp.retrofit.RetrofitClient;
import com.juangil.adminbibliotecaapp.utils.Utils;
import com.juangil.adminbibliotecaapp.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AutoresScreenActivity extends AppCompatActivity implements AdaptadorAutor.OnItemClicked {

    private ActivityAutoresScreenBinding binding;
    AdaptadorAutor adaptadorAutor;
    List<DataAutor> listaAutores = new ArrayList<>();
    Retrofit retrofit = new RetrofitClient().getRetrofit();
    WebService webService = retrofit.create(WebService.class);
    Utils utils = new Utils();
    DataAutor autor = new DataAutor();
    boolean isEditando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAutoresScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        obtenerAutores();

        binding.ibtnAutorAdd.setOnClickListener(view -> {
            alertDialogAddUpdate();
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvAutores.setLayoutManager(layoutManager);
        adaptadorAutor = new AdaptadorAutor(this, listaAutores, this);
        binding.rvAutores.setAdapter(adaptadorAutor);
    }

    private void alertDialogAddUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View vista = layoutInflater.inflate(R.layout.alert_dialog_add_update_autor, null);

        TextView tvTituloAlert = vista.findViewById(R.id.tvTituloAlert);
        EditText etIdAutor = vista.findViewById(R.id.etIdAutor);
        EditText etNomAutor = vista.findViewById(R.id.etNomAutor);

        if (isEditando) {
            tvTituloAlert.setText("ACTUALIZAR AUTOR");
            etIdAutor.setText(autor.getIdAutor());
            etIdAutor.setEnabled(false);
            etNomAutor.setText(autor.getNomAutor());
        }

        builder.setView(vista);
        builder.create();

        builder.setPositiveButton("ACEPTAR", (dialog, which) -> {
            boolean isValido = utils.validarCamposAddUpdateAutor(
                    etIdAutor.getText().toString().trim(),
                    etNomAutor.getText().toString().trim()
            );

            if (isValido) {
                autor.setIdAutor(etIdAutor.getText().toString().trim());
                autor.setNomAutor(etNomAutor.getText().toString().trim());

                if (isEditando) {
                    actualizarAutor(autor);
                } else {
                    agregarAutor(autor);
                }

                isEditando = false;
                obtenerAutores();
                autor.resetData();

            } else {
                isEditando = false;
                autor.resetData();
            }
        });

        builder.setNegativeButton("CANCELAR", (dialog, which) -> {
            Toast.makeText(this, "CANCELAR", Toast.LENGTH_SHORT).show();

        });

        builder.setCancelable(false);
        builder.show();
    }

    private void obtenerAutores() {
        Call<AutorResponse> call = webService.obtenerAutores();
        call.enqueue(new Callback<AutorResponse>() {
            @Override
            public void onResponse(Call<AutorResponse> call, Response<AutorResponse> response) {
                if (response.body().getCode().equals("200")) {
                    listaAutores = response.body().getData();
                    setupRecyclerView();
                } else {
                    Toasty.error(AutoresScreenActivity.this, response.body().getMensaje(), Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AutorResponse> call, Throwable t) {

            }
        });
    }

    private void agregarAutor(DataAutor autor) {
        Call<AutorResponse> call = webService.agregarAutor(autor);
        call.enqueue(new Callback<AutorResponse>() {
            @Override
            public void onResponse(Call<AutorResponse> call, Response<AutorResponse> response) {
                if (response.body().getCode().equals("200")) {
                    Toasty.success(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AutorResponse> call, Throwable t) {

            }
        });
    }

    private void actualizarAutor(DataAutor autor) {
        Call<AutorResponse> call = webService.actualizarAutor(autor);
        call.enqueue(new Callback<AutorResponse>() {
            @Override
            public void onResponse(Call<AutorResponse> call, Response<AutorResponse> response) {
                if (response.body().getCode().equals("200")) {
                    Toasty.success(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AutorResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void editarAutor(DataAutor autor) {
        isEditando = true;
        this.autor = autor;
        alertDialogAddUpdate();
    }

    @Override
    public void borrarAutor(DataAutor autor) {
        Call<AutorResponse> call = webService.borrarAutor(autor);
        call.enqueue(new Callback<AutorResponse>() {
            @Override
            public void onResponse(Call<AutorResponse> call, Response<AutorResponse> response) {
                if (response.body().getCode().equals("200")) {
                    Toasty.success(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                    obtenerAutores();
                } else {
                    Toasty.error(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AutorResponse> call, Throwable t) {

            }
        });

    }
}