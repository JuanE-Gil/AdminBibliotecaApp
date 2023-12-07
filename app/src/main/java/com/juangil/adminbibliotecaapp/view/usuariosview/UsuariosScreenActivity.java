package com.juangil.adminbibliotecaapp.view.usuariosview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.juangil.adminbibliotecaapp.R;
import com.juangil.adminbibliotecaapp.databinding.ActivityUsuariosScreenBinding;
import com.juangil.adminbibliotecaapp.response.DataUsuario;
import com.juangil.adminbibliotecaapp.response.UsuarioResponse;
import com.juangil.adminbibliotecaapp.retrofit.RetrofitClient;
import com.juangil.adminbibliotecaapp.utils.Utils;
import com.juangil.adminbibliotecaapp.webservice.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsuariosScreenActivity extends AppCompatActivity implements AdaptadorUsuario.OnItemClicked {

    //region Variables
    private ActivityUsuariosScreenBinding binding;
    AdaptadorUsuario adaptadorUsuario;
    List<DataUsuario> listaUsuarios = new ArrayList<>();
    Retrofit retrofit = new RetrofitClient().getRetrofit();
    WebService webService = retrofit.create(WebService.class);
    Utils utils = new Utils();
    DataUsuario usuario = new DataUsuario();
    Boolean isEditando = false;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuariosScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        obtenerUsuarios();

        binding.ibtnUsuarioAdd.setOnClickListener(view -> {
            alertDialogAddUpdate();
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvUsuarios.setLayoutManager(layoutManager);
        adaptadorUsuario = new AdaptadorUsuario(this, listaUsuarios, this);
        binding.rvUsuarios.setAdapter(adaptadorUsuario);
    }

    private void alertDialogAddUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View vista = layoutInflater.inflate(R.layout.alert_dialog_add_update_usuario, null);

        TextView tvTituloAlert = vista.findViewById(R.id.tvTituloAlert);
        EditText etIdUsuario = vista.findViewById(R.id.etIdUsuario);
        EditText etNomUsuario = vista.findViewById(R.id.etNomUsuario);
        EditText etContrasena = vista.findViewById(R.id.etContrasena);

        if (isEditando) {
            tvTituloAlert.setText(R.string.actualizar_usuario);

            etIdUsuario.setText(usuario.getIdUsuario());
            etIdUsuario.setEnabled(false);

            etNomUsuario.setText(usuario.getNomUsuario());
            etContrasena.setText(usuario.getContrasena());
        }

        builder.setView(vista);
        builder.create();

        builder.setPositiveButton("ACEPTAR", (dialog, which) -> {
            boolean isValido = utils.validarCamposAddUpdateUsuario(
                    etIdUsuario.getText().toString().trim(),
                    etNomUsuario.getText().toString().trim(),
                    etContrasena.getText().toString().trim()
            );

            if (isValido) {
                usuario.setIdUsuario(etIdUsuario.getText().toString().trim());
                usuario.setNomUsuario(etNomUsuario.getText().toString().trim());
                usuario.setContrasena(etContrasena.getText().toString().trim());

                if (isEditando) {
                    actualizarUsuario(usuario);
                } else {
                    agregarUsuario(usuario);
                    usuario.setEstadoUsuario(null);
                }
                isEditando = false;

                obtenerUsuarios();
                usuario.resetData();
            } else {
                Toasty.error(UsuariosScreenActivity.this,
                        "Se deben llenar los campos obligatorios",
                        Toasty.LENGTH_SHORT, true
                ).show();
            }
        });

        builder.setNegativeButton("CANCELAR", (dialog, which) -> {
            usuario.resetData();
        });

        builder.setCancelable(false);
        builder.show();
    }

    private void obtenerUsuarios() {
        Call<UsuarioResponse> call = webService.obtenerUsuarios();
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(@NonNull Call<UsuarioResponse> call, @NonNull Response<UsuarioResponse> response) {
                if (Objects.requireNonNull(response.body()).getCode().equals("200")) {
                    listaUsuarios = response.body().getData();
                    setupRecyclerView();
                } else {
                    Toasty.error(UsuariosScreenActivity.this, response.body().getMensaje(), Toast.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UsuarioResponse> call, @NonNull Throwable t) {
                usuario.resetData();
            }
        });
    }

    private void agregarUsuario(DataUsuario usuario) {
        Call<UsuarioResponse> call = webService.agregarUsuario(usuario);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(@NonNull Call<UsuarioResponse> call, @NonNull Response<UsuarioResponse> response) {
                if (Objects.requireNonNull(response.body()).getCode().equals("200")) {
                    Toasty.success(UsuariosScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(UsuariosScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UsuarioResponse> call, @NonNull Throwable t) {
            }
        });
    }

    private void actualizarUsuario(DataUsuario usuario) {
        Call<UsuarioResponse> call = webService.actualizaUsuario(usuario);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(@NonNull Call<UsuarioResponse> call, @NonNull Response<UsuarioResponse> response) {
                if (Objects.requireNonNull(response.body()).getCode().equals("200")) {
                    Toasty.success(UsuariosScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(UsuariosScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UsuarioResponse> call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    public void editarUsuario(DataUsuario usuario) {
        isEditando = true;
        this.usuario = usuario;
        alertDialogAddUpdate();
    }

    @Override
    public void borrarUsuario(DataUsuario usuario) {
        Call<UsuarioResponse> call = webService.borrarUsuario(usuario);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(@NonNull Call<UsuarioResponse> call, @NonNull Response<UsuarioResponse> response) {
                if (Objects.requireNonNull(response.body()).getCode().equals("200")) {
                    Toasty.success(UsuariosScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                    obtenerUsuarios();
                } else {
                    Toasty.error(UsuariosScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UsuarioResponse> call, @NonNull Throwable t) {
            }
        });
    }
}