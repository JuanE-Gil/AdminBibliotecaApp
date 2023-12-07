package com.juangil.adminbibliotecaapp.view.splashscreenview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.juangil.adminbibliotecaapp.R;
import com.juangil.adminbibliotecaapp.databinding.ActivitySplashScreenBinding;
import com.juangil.adminbibliotecaapp.utils.Utils;
import com.juangil.adminbibliotecaapp.view.loginscreenview.LoginScreenActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Glide
                .with(this)
                .load(R.drawable.gif_libro)
                .centerInside()
                .into(binding.ivSplashScreen);

        cambiarPantalla();
    }

    private void cambiarPantalla() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, LoginScreenActivity.class);
            startActivity(intent);
            finish();
        }, new Utils().getDuracionSplashScreen());
    }
}