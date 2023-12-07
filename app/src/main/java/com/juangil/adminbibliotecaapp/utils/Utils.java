package com.juangil.adminbibliotecaapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.juangil.adminbibliotecaapp.response.DataAdminUsuario;

public class Utils {

    private static final int DURACION_SPLASH_SCREEN = 3000;

    public int getDuracionSplashScreen() {
        return DURACION_SPLASH_SCREEN;
    }


    public boolean validarCamposLogin(String idUsuario, String contrasena) {
        if (idUsuario.isEmpty() || contrasena.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validarCamposAddUpdateUsuario(String idUsuario, String nomUsuario, String contrasena) {
        if (idUsuario.isEmpty() || nomUsuario.isEmpty() || contrasena.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validarCamposAddUpdateAutor(String idAutor, String nomAutor) {
        if (idAutor.isEmpty() || nomAutor.isEmpty()) {
            return false;
        } else{
            return true;
        }
    }

    public void guardarSharedPreferences(Context context, DataAdminUsuario usuario) {
        SharedPreferences preferences = context.getSharedPreferences("data_usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idUsuario", usuario.getIdUsuario());
        editor.putString("nomUsuario", usuario.getNomUsuario());
        editor.apply();
    }

    public DataAdminUsuario leerSharedPreferences(Context context) {

        SharedPreferences preferences = context.getSharedPreferences("data_usuario", Context.MODE_PRIVATE);
        DataAdminUsuario usuario = new DataAdminUsuario();
        usuario.setIdUsuario(preferences.getString("idUsuario", ""));
        usuario.setNomUsuario(preferences.getString("nomUsuario", ""));
        return usuario;
    }

    public void borrarSharedPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("data_usuario", Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }


}
