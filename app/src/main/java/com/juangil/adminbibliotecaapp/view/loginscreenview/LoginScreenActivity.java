package com.juangil.adminbibliotecaapp.view.loginscreenview;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.juangil.adminbibliotecaapp.databinding.ActivityLoginScreenBinding;
import com.juangil.adminbibliotecaapp.response.AdminUsuarioResponse;
import com.juangil.adminbibliotecaapp.response.DataAdminUsuario;
import com.juangil.adminbibliotecaapp.retrofit.RetrofitClient;
import com.juangil.adminbibliotecaapp.utils.Utils;
import com.juangil.adminbibliotecaapp.view.navegacionview.NavegacionScreenActivity;
import com.juangil.adminbibliotecaapp.webservice.WebService;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginScreenActivity extends AppCompatActivity {

    private ActivityLoginScreenBinding binding;
    Utils utils = new Utils();
    DataAdminUsuario usuario = new DataAdminUsuario();
    Retrofit retrofit = new RetrofitClient().getRetrofit();
    WebService webService = retrofit.create(WebService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.btnLogin.setOnClickListener(view -> {
            boolean isValido = utils.validarCamposLogin(
                    binding.etUsuario.getText().toString().trim(),
                    binding.etContrasena.getText().toString().trim()
            );

            if (!isValido) {
                Toasty.error(LoginScreenActivity.this,
                        "Verifica que existe campos vacios",
                        Toasty.LENGTH_SHORT, true).show();
                return;
            }

            login();
        });
    }

    private void login() {
        usuario.setIdUsuario(binding.etUsuario.getText().toString().trim());
        usuario.setContrasena(binding.etContrasena.getText().toString().trim());

        Call<AdminUsuarioResponse> call = webService.Login(usuario);
        call.enqueue(new Callback<AdminUsuarioResponse>() {
            @Override
            public void onResponse(Call<AdminUsuarioResponse> call, Response<AdminUsuarioResponse> response) {
                if (response.body().getCode().equals("200")) {
                    utils.guardarSharedPreferences(LoginScreenActivity.this, response.body().getData().get(0));
                    Intent intent = new Intent(LoginScreenActivity.this, NavegacionScreenActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toasty.error(LoginScreenActivity.this,
                            response.body().getMensaje(),
                            Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AdminUsuarioResponse> call, Throwable t) {

            }
        });
    }
}