package com.juangil.adminbibliotecaapp.response;

import com.google.gson.annotations.SerializedName;

public class DataAdminUsuario {
    @SerializedName("id_usuario")
    private String idUsuario;

    @SerializedName("nom_usuario")
    private String nomUsuario;

    @SerializedName("contrasena")
    private String contrasena;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
