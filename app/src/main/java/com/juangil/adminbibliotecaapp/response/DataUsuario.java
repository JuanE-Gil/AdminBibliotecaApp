package com.juangil.adminbibliotecaapp.response;

import com.google.gson.annotations.SerializedName;

public class DataUsuario {

    @SerializedName("id_usuario")
    private String IdUsuario;

    @SerializedName("nom_usuario")
    private String nomUsuario;

    @SerializedName("estado_usuario")
    private String estadoUsuario;

    @SerializedName("contrasena")
    private String contrasena;

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void resetData() {
        this.IdUsuario = "";
        this.nomUsuario = "";
        this.estadoUsuario = "";
        this.contrasena = "";
    }


    @Override
    public String toString() {
        return this.IdUsuario + ", " + this.nomUsuario;
    }
}
